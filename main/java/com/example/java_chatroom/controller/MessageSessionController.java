package com.example.java_chatroom.controller;


import com.example.java_chatroom.mapper.MessageMapper;
import com.example.java_chatroom.mapper.MessageSessionMapper;
import com.example.java_chatroom.model.MessageSession;
import com.example.java_chatroom.model.MessageSessionUser;
import com.example.java_chatroom.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("/messageSession")
@RestController
public class MessageSessionController {
    @Autowired
    MessageSessionMapper messageSessionMapper;

    @Autowired
    MessageMapper messageMapper;

    @RequestMapping("/sessionList")
    public Object getMessageSessionList(HttpSession session) {
        List<MessageSession>messageSessionList=new ArrayList<>();
        if(session==null) {
            log.info("session is null");
            return messageSessionList;
        }
        User user = (User) session.getAttribute("user");
        if(user==null) {
            log.info("user is null");
            return messageSessionList;
        }
        List<Integer>sessionIdList=messageSessionMapper.getSessionsByUserId(user.getUserId());
        for(int sessionId :sessionIdList){
            MessageSession messageSession=new MessageSession();
            messageSession.setSessionId(sessionId);
            messageSession.setFriends(messageSessionMapper.getFriendsBySessionId(sessionId, user.getUserId()));
            //TODO:每个会话最后一条消息
            String lastMessage=messageMapper.getLastMessageBySessionId(sessionId);
            if(lastMessage==null) {
                messageSession.setLastMessage("");
            }else{
                messageSession.setLastMessage(lastMessage);
            }
            messageSessionList.add(messageSession);
        }
        log.info(messageSessionList.toString());
        return messageSessionList;
    }

    @RequestMapping("/session")
    @Transactional
    public Object getMessageSession(int toUserId, @SessionAttribute("user") User user) {

        HashMap<String,Integer> map=new HashMap<>();
        //将数据添加到message_session表
        MessageSession messageSession=new MessageSession();
        messageSessionMapper.addMessageSession(messageSession);
        //添加数据到message_session_user,好友id
        MessageSessionUser messageSessionUser1=new MessageSessionUser();
        messageSessionUser1.setUserId(toUserId);
        messageSessionUser1.setSessionId(messageSession.getSessionId());
        messageSessionMapper.addMessageSessionUser(messageSessionUser1);
        //添加数据到message_session_user,自己id
        MessageSessionUser messageSessionUser2=new MessageSessionUser();
        messageSessionUser2.setUserId(user.getUserId());
        messageSessionUser2.setSessionId(messageSession.getSessionId());
        messageSessionMapper.addMessageSessionUser(messageSessionUser2);
        //返回数据格式{session:sessionId}
        map.put("sessionId",messageSession.getSessionId());

        log.info("新增会话成功 sessionId:"+messageSession.getSessionId()
                +"useId1="+user.getUserId()+"useId2="+toUserId);
        return map;
    }
}
