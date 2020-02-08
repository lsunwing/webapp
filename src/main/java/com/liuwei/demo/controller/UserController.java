package com.liuwei.demo.controller;

import com.liuwei.demo.bean.User;
import com.liuwei.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    public UserController() {
    }

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/getUserName")
    public String getUserName(Long userId) throws Exception {
        return userService.getUserName(userId);
    }

    @ResponseBody
    @RequestMapping("/getUserList")
    public List<User> getUserList() throws Exception {
        return userService.qryUserList();
    }

    @ResponseBody
    @RequestMapping("/getUserByUserId")
    public User getUserByUserId(Long userId) throws Exception {
        return userService.qryUserByUserId(userId);
    }

    @ResponseBody
    @RequestMapping("/getUserAsMap")
    public Map<Long, User> getUserAsMap(Long userId) throws Exception {
        return userService.getUserAsMap(userId);
    }

    @ResponseBody
    @RequestMapping("/getUserListAsMap")
    public Map<Long, User> getUserListAsMap() throws Exception {
        return userService.getUserListAsMap();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public void addUser(@RequestParam("userName") String userName,
                        @RequestParam(value = "comment", defaultValue = "0") String comment) throws Exception {
        User user = new User();
        user.setUserId(8L);
        user.setUserName(userName);
        user.setComment(comment);

        userService.addUser(user);
    }
}

