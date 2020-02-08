package com.liuwei.demo.service;

import com.liuwei.demo.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Component
public interface IUserService {
    String getUserName(Long userId) throws Exception;

    List<User> qryUserList() throws Exception;

    User qryUserByUserId(Long userId) throws Exception;

    Map<Long, User> getUserAsMap(Long userId) throws Exception;

    Map<Long, User> getUserListAsMap() throws Exception;


    void addUser(User user) throws Exception;
}
