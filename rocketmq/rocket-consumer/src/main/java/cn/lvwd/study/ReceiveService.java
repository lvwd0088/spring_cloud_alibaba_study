package cn.lvwd.study;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {

    @StreamListener("input")
    public void receiveInput(String receiveMsg) {
        System.out.println("input receive" + receiveMsg);
    }

}
