package cn.lvwd.study;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author lvwd
 * @date 2019/12/25
 */
public interface MySource {

    @Output("output")
    MessageChannel output();

}
