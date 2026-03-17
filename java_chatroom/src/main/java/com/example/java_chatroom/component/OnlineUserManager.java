package com.example.java_chatroom.component;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class OnlineUserManager {
    private ConcurrentHashMap<Integer, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public WebSocketSession getSession(Integer id) {
        return sessions.get(id);
    }

    public void online(Integer userId,WebSocketSession session) {
        if(sessions.get(userId)!=null){
            log.info("用户已登录,登录失败!:{}",userId);
            return;
        }
        sessions.put(userId, session);
        log.info("上线!:{}",userId);
    }


    public void offline(Integer userId,WebSocketSession session) {
        WebSocketSession exitSession = sessions.get(userId);
        if(exitSession==session){
            sessions.remove(userId);
            log.info("下线!:{}",userId);
        }
    }

}
