package com.bit.blog.pojo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginRequest {
    @NotNull(message="用户名不能为空")
    @Length(max=20)
    private String userName;
    @NotNull(message = "密码不能为空")
    @Length(min=5,message = "密码长度不能小于5")
    private String password;
}
