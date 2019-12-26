package cn.lvwd.study.service;

import cn.lvwd.study.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author lvwd
 * @date 2019/12/26
 */
public class InMemoryUserRepository {

    private Map<Long, User> users = Maps.newHashMap();

    public void add(User user){
        users.put(user.getId(), user);
    }

    public List<User> query(){
        return Lists.newArrayList(users.values());
    }

}
