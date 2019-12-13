package cn.lvwd.study;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lvwd
 * @date 2019/12/12
 */
@FeignClient(name = "provider")
public interface EchoService {

    @GetMapping("/echo/{string}")
    String echo(@PathVariable String string);

}
