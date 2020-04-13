package cn.lvwd.study.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class TokenFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("-------------");
        System.out.println("hey man");
        System.out.println("-------------");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
