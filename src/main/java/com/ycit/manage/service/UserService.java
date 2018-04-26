package com.ycit.manage.service;

import com.ycit.manage.bean.modal.User;
import com.ycit.manage.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户 service 层
 *
 * @author xlch
 * @Date 2018-03-21 12:30
 */
@Service
public class UserService {

    private UserMapper userMapper;

    @Resource
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }


    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public List<User> finds() {
        return userMapper.finds();
    }

    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }
}
