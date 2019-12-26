package cn.lvwd.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author lvwd
 * @date 2019/12/25
 */
@SpringBootApplication
@EnableBinding({MySource.class})
public class RocketMqProducer {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqProducer.class, args);
    }

}
