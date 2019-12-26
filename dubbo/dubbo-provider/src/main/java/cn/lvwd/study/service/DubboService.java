package cn.lvwd.study.service;

import cn.lvwd.study.User;
import cn.lvwd.study.UserService;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

@Service
public class DubboService implements UserService {

    InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

    @Override
    public boolean save(User user) {
        inMemoryUserRepository.add(user);
        return true;
    }

    @Override
    public boolean remove(Long userId) {
        System.out.println(1/0);
        return false;
    }

    @Override
    public List<User> findAll() {
        return inMemoryUserRepository.query();
    }
}
