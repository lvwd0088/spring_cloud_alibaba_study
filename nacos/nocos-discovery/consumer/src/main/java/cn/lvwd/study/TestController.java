package cn.lvwd.study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lvwd
 * @date 2019/12/12
 */
@RestController
public class TestController {

    @Resource
    private EchoService echoService;

    @GetMapping("testEcho/{string}")
    public String testEcho(@PathVariable String string){
        return echoService.echo(string);
    }

}
