package com.ycit.service;

import com.ycit.bean.modal.User;
import com.ycit.mapper.UserMapper;
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

    public int updateBalance(int id, int num) {
        return userMapper.updateBalance(id, num);
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
