package com.liuwei.demo.service.impl;

import com.liuwei.demo.bean.User;
import com.liuwei.demo.jedis.JedisClient;
import com.liuwei.demo.mapper.UserMapper;
import com.liuwei.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    JedisClient jedisClient;

    @Override
    public String getUserName(Long userId) throws Exception {
//        String name = jedisClient.get("name");
//        return name;
        return userMapper.getUserName(userId);
    }

    @Override
    public List<User> qryUserList() throws Exception {
        return userMapper.getUserList();
    }

    @Override
    public User qryUserByUserId(Long userId) throws Exception {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public Map<Long, User> getUserAsMap(Long userId) throws Exception {
        return userMapper.getUserAsMap(userId);
    }

    @Override
    public Map<Long, User> getUserListAsMap() throws Exception {
        return userMapper.getUserListAsMap();
    }

    @Transactional
    @Override
    public void addUser(User user) throws Exception {
        userMapper.addUser(user);
    }
}
