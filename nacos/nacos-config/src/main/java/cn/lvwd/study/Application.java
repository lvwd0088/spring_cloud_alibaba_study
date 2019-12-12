package cn.lvwd.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lvwd
 * @date 2019/12/12
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class);
        System.out.println(applicationContext.getEnvironment().getProperty("user.name"));
        System.out.println(applicationContext.getEnvironment().getProperty("user.age"));
    }

}
