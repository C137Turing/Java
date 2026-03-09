package com.bite.spingbookdemo.service;

import com.bite.spingbookdemo.mapper.UserInfoMapper;
import com.bite.spingbookdemo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    public UserInfo queryUserInfoByName(String username) {
        return userInfoMapper.getUserInfoByName(username);
    }
}
