package com.example.java_chatroom.model;


import lombok.Data;

@Data
public class MessageResponse {
    private String type="message";
    private int fromId;
    private String fromName;
    private int sessionId;
    private String content;
}
