package cn.lvwd.study.gateway.filter;

import cn.lvwd.study.gateway.vo.RequestLog;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
public class PrintFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            RequestLog requestLog = new RequestLog();
            ServerHttpRequest request = exchange.getRequest();
            ServerRequest serverRequest = ServerRequest.create(exchange,
                    HandlerStrategies.withDefaults().messageReaders());
            URI requestUri = request.getURI();
            String uriQuery = requestUri.getQuery();
            requestLog.setUrl(requestUri.getPath() + (StringUtils.isNotBlank(uriQuery) ? "?" + uriQuery : ""));
            HttpHeaders headers = request.getHeaders();
            Optional.ofNullable(headers.getContentType())
                    .map(Objects::toString)
                    .ifPresent(requestLog::setMediaType);
            requestLog.setMethod(request.getMethodValue().toUpperCase());

            // 原始请求体
//            final AtomicReference<String> requestBody = new AtomicReference<>();
//            final AtomicBoolean newBody = new AtomicBoolean(false);
//            if (Objects.equals(requestLog.getMethod(), "GET")) {
//                if (StringUtils.isNotBlank(uriQuery)) {
//                    requestBody.set(uriQuery);
//                }
//            } else {
//                newBody.set(true);
//            }
//            System.out.println(requestBody);

            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().build();
            ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
            return build.getSession().flatMap(webSession -> {
                if (Objects.equals(requestLog.getMethod(), "POST") && headers.getContentLength() > 0) {
                    Mono<String> bodyToMono = serverRequest.bodyToMono(String.class);
                    return bodyToMono.flatMap(requestBody -> {
                        requestLog.setRequestBody(requestBody);
                        // 重写原始请求
                        ServerHttpRequestDecorator requestDecorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
                                DataBuffer bodyDataBuffer = nettyDataBufferFactory.wrap(requestBody.getBytes());
                                return Flux.just(bodyDataBuffer);
                            }
                        };
                        log.info(requestLog.toString());
                        return chain.filter(exchange.mutate()
                                .request(requestDecorator)
                                .build());
                    });
                } else {
                    log.info(requestLog.toString());
                    return chain.filter(exchange);
                }
            });
        } catch (Exception e) {
            log.error("请求日志打印出现异常", e);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
