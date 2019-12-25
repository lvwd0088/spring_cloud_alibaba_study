package cn.lvwd.study;

import java.util.List;

public interface UserService {

    boolean save(User user);

    boolean remove(Long userId);

    List<User> findAll();
}
