package com.bit.blog.pojo.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserInfoResponse {
    private Integer id;
    private String userName;
    private String githubUrl;
}
