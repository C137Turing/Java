package com.example.java_chatroom.controller;

import com.example.java_chatroom.mapper.FriendMapper;
import com.example.java_chatroom.model.Friend;
import com.example.java_chatroom.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/friend")
@RestController
@Slf4j
public class FriendController {
    @Autowired
    private FriendMapper friendMapper;

    @RequestMapping("/friendList")
    public Object friendList(HttpSession session) {
        if(session==null) {
            log.info("[getFriendList] session is null]");
            return new ArrayList<Friend>();
        }
        User user = (User) session.getAttribute("user");
        if(user==null) {
            log.info("[getFriendList] user is null");
            return new ArrayList<Friend>();
        }
        List<Friend>friendList=friendMapper.selectFriendList(user.getUserId());
        log.info("[getFriendList] friendList="+friendList);
        return friendList;
    }
    
}
