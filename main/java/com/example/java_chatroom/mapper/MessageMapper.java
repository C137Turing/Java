package com.example.java_chatroom.mapper;

import com.example.java_chatroom.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    String getLastMessageBySessionId(int sessionId);

    List<Message> getMessageBySessionId(int sessionId);

    void addMessage(Message message);
}
