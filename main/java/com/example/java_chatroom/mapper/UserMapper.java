package com.example.java_chatroom.mapper;

import com.example.java_chatroom.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //注册--把用户插入到数据库中
    int insert(User user);

    //登录--根据用户名查询用户信息
    User selectByName(String username);

}
