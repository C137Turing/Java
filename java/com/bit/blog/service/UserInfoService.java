package com.bit.blog.service;

import com.bit.blog.pojo.request.UserLoginRequest;
import com.bit.blog.pojo.response.UserInfoResponse;
import com.bit.blog.pojo.response.UserLoginResponse;
import jakarta.validation.constraints.NotNull;

public interface UserInfoService {

    UserLoginResponse checkPassword(UserLoginRequest userLoginRequest);

    UserInfoResponse getUserInfo(@NotNull(message = "userId不能为空") Integer userId);

    UserInfoResponse getAuthorInfo(@NotNull(message = "userId不能为空") Integer blogId);
}
