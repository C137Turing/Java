package com.bite.spingbookdemo.cotroller;


import com.bite.spingbookdemo.constant.Constants;
import com.bite.spingbookdemo.model.ResponSeResult;
import com.bite.spingbookdemo.model.UserInfo;
import com.bite.spingbookdemo.service.UserInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("login")
    public Boolean login(String username, String password, HttpSession session) {

        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return false;
        }
//        if("admin".equals(username) && "123456".equals(password)) {
//            return true;
//        }
        //判断
        UserInfo userInfo = userInfoService.queryUserInfoByName(username);
        if (userInfo == null) {
            return false;
        }
        if (password.equals(userInfo.getPassword())) {
            userInfo.setPassword("");
            session.setAttribute(Constants.SEESION_USER_KEY, userInfo);
            return true;
        }

        return false;
    }

}
