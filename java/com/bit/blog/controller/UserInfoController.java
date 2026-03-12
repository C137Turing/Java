package com.bit.blog.controller;


import com.bit.blog.pojo.dataobject.UserInfo;
import com.bit.blog.pojo.request.UserLoginRequest;
import com.bit.blog.pojo.response.UserInfoResponse;
import com.bit.blog.pojo.response.UserLoginResponse;
import com.bit.blog.service.UserInfoService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserInfoController {
    @Resource(name="userInfoServiceImpl")
    private UserInfoService userInfoService;
    @RequestMapping("/login")
    public UserLoginResponse login(@RequestBody @Validated UserLoginRequest userLoginRequest) {
        log.info("用户登录,用户名:"+userLoginRequest.getUserName());
        return userInfoService.checkPassword(userLoginRequest);
    }

    @RequestMapping("/getUserInfo")
    public UserInfoResponse getUserInfo(@NotNull(message = "userId不能为空")Integer userId) {
        log.info("获取用户信息"+userId);
        return userInfoService.getUserInfo(userId);
    }

    @RequestMapping("/getAuthorInfo")
    public UserInfoResponse getAuthorInfo(@NotNull(message = "userId不能为空")Integer blogId) {
        log.info("获取作者信息"+blogId);
        return userInfoService.getAuthorInfo(blogId);
    }
}
