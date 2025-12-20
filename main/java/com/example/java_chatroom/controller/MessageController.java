package com.example.java_chatroom.controller;


import com.example.java_chatroom.mapper.MessageMapper;
import com.example.java_chatroom.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestMapping("/message")
@RestController
@Slf4j
public class MessageController {
    @Autowired
    private MessageMapper messageMapper;

    @RequestMapping("/getMessage")
    public Object getMessgesBySessionId(int sessionId) {
        List<Message> getMessage=messageMapper.getMessageBySessionId(sessionId);
        //对查询结果逆置
        Collections.reverse(getMessage);
        return getMessage;
    }

}
