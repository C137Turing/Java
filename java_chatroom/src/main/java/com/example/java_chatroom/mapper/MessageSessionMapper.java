package com.example.java_chatroom.mapper;

import com.example.java_chatroom.model.Friend;
import com.example.java_chatroom.model.MessageSession;
import com.example.java_chatroom.model.MessageSessionUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageSessionMapper {
    List<Integer> getSessionsByUserId(Integer userId);

    List<Friend> getFriendsBySessionId(int sessionId, int selfUserId);

    int addMessageSession(MessageSession messageSession);

    void addMessageSessionUser(MessageSessionUser messageSessionUser);
}
