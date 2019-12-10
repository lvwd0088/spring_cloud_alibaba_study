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
    @SentinelResource("hello")
    public String hello(){
        return "hello Sentinel";
    }

}
