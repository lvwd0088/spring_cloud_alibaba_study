package cn.lvwd.study;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lvwd
 * @date 2019/12/25
 */
@Service
public class SenderService {

    @Resource
    private MySource mySource;

    public void send(String msg){
        Message<String> message = MessageBuilder.withPayload(msg).build();
        mySource.output().send(message);
    }

}
