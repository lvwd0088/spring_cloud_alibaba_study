package cn.lvwd.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvwd
 * @date 2019/12/12
 */
@RestController
@Slf4j
public class EchoController {

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        log.info("进入provider echo");
        return "hello " + string;
    }

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }

}
