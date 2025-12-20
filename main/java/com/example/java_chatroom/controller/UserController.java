package com.example.java_chatroom.controller;


import com.example.java_chatroom.mapper.UserMapper;
import com.example.java_chatroom.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/login")
    public Object login(String username, String password, HttpSession session) {
        User user =userMapper.selectByName(username);
        if(user==null||!user.getPassword().equals(password)) {
            log.info("登录失败! 用户名: {}, 用户对象: {}", username, user);
            return new User();
        }
        session.setAttribute("user", user);

        user.setPassword("");
        return user;
    }

    @RequestMapping("/register")
    public Object register(String username, String password) {
        User user = new User();
        try {
            user.setUsername(username);
            user.setPassword(password);
            int ret = userMapper.insert(user);
            log.info("注册成功:{}", ret);
            user.setPassword("");
        } catch (DuplicateKeyException e) {
            log.error("注册失败:{}", e.getMessage());
            return new User();
        }
        return user;
    }

    @RequestMapping("/userInfo")
    public Object userInfo(HttpSession session) {
        if(session==null||session.getAttribute("user")==null) {
            log.info("获取不到session对象");
            return new User();
        }
        User user = (User)session.getAttribute("user");
        user.setPassword("");
        return user;
    }
}
