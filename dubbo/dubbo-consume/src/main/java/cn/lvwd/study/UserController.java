package cn.lvwd.study;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lvwd
 * @date 2019/12/26
 */
@RestController
public class UserController {

    @Reference
    private UserService userService;

    @PutMapping("user")
    public String add(@RequestBody User user){
        userService.save(user);
        return "ok";
    }

    @DeleteMapping("user")
    public String remove(Long id){
        userService.remove(id);
        return "ok";
    }

    @GetMapping("user")
    public List<User> query(){
        return userService.findAll();
    }

}
