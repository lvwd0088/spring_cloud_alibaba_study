package cn.lvwd.study;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvweida
 */
@RestController
public class TestController {

    @GetMapping("hello")
    @SentinelResource(value = "hello", blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class, fallback = "helloFallback")
    public String hello(){
        System.out.println(1/0);
        return "hello Sentinel";
    }

    public String helloFallback(){
        return "helloFallback";
    }

}
