package cn.lvwd.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lvwd
 * @date 2019/12/12
 */
@RestController
@Slf4j
public class TestController {

    @Resource
    private EchoService echoService;

    @GetMapping("testEcho/{string}")
    public String testEcho(@PathVariable String string){
        log.info("进入consumer testEcho");
        return echoService.echo(string);
    }

}
