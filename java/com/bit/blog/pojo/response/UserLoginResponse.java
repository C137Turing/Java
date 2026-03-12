package com.bit.blog.pojo.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
public class UserLoginResponse {
    private Integer userId;
    private String token;
}
