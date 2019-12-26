package cn.lvwd.study;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lvwd
 * @date 2019/12/25
 */
@RestController
public class TestController {

    @Resource
    private SenderService senderService;

    @RequestMapping("send")
    public String send(String msg){
        senderService.send(msg);
        return "ok";
    }

}
