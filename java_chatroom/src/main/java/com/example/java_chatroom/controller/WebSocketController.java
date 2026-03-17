package com.example.java_chatroom.controller;

import com.example.java_chatroom.component.OnlineUserManager;
import com.example.java_chatroom.mapper.FriendMapper;
import com.example.java_chatroom.mapper.MessageMapper;
import com.example.java_chatroom.mapper.MessageSessionMapper;
import com.example.java_chatroom.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;


@Component
@Slf4j
public class WebSocketController extends TextWebSocketHandler {
    @Autowired
    private OnlineUserManager onlineUserManager;

    @Autowired
    private MessageSessionMapper messageSessionMapper;

    @Autowired
    private MessageMapper messageMapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("websocketController 连接成功!");
        User user = (User)session.getAttributes().get("user");
        if (user == null) {
            return;
        }

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
       log.info("websocketController 收到消息:"+message.toString());
        User user = (User)session.getAttributes().get("user");
        if (user == null) {
            log.info("用户未登录!:{}",user.getUserId());
            return;
        }
        onlineUserManager.online(user.getUserId(), session);
        MessageRequest req=objectMapper.readValue(message.getPayload(), MessageRequest.class);
        if(req.getType().equals("message")){
            transferMessage(user,req);
        }else{
            log.error("WebSocketController 有误!:{}",message.getPayload());
        }
    }

    private void transferMessage(User formUser, MessageRequest req) throws IOException {
        MessageResponse response = new MessageResponse();
        response.setFromId(formUser.getUserId());
        response.setFromName(formUser.getUsername());
        response.setSessionId(req.getSessionId());
        response.setContent(req.getContent());
        String reqJson=objectMapper.writeValueAsString(response);
        log.info("transferMessage reqJson:{}",reqJson);
        //通过数据库查询会话中有那些用户
        List<Friend> friends=messageSessionMapper.getFriendsBySessionId(req.getSessionId(), formUser.getUserId());
        //将发送者id加入list
        Friend myself=new Friend();
        myself.setFriendId(formUser.getUserId());
        myself.setFriendName(formUser.getUsername());
        friends.add(myself);

        for(Friend friend:friends){
            WebSocketSession webSocketSession=onlineUserManager.getSession(friend.getFriendId());
            if(webSocketSession==null){
                log.info("用户未登录!:{}",friend.getFriendId());
                continue;
            }
             webSocketSession.sendMessage(new TextMessage(reqJson));
        }
        //将数据存入数据库
        Message message=new Message();
        message.setFromId(formUser.getUserId());
        message.setSessionId(req.getSessionId());
        message.setContent(req.getContent());

        messageMapper.addMessage(message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("websocketController 连接异常:"+exception.getMessage());
        User user = (User)session.getAttributes().get("user");
        if (user == null) {
            return;
        }
        onlineUserManager.offline(user.getUserId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("websocketController 连接关闭:"+status.toString());
        User user = (User)session.getAttributes().get("user");
        if (user == null) {
            return;
        }
        onlineUserManager.offline(user.getUserId(), session);
    }

}
