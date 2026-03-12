package com.bit.blog.common.util;

import com.bit.blog.pojo.dataobject.BlogInfo;
import com.bit.blog.pojo.dataobject.UserInfo;
import com.bit.blog.pojo.response.BlogInfoResponse;
import com.bit.blog.pojo.response.UserInfoResponse;
import org.springframework.beans.BeanUtils;

public class BeanTransUtils {
    public static BlogInfoResponse trans(BlogInfo blogInfo) {
        if(blogInfo==null){
            return null;
        }
        BlogInfoResponse blogInfoResponse = new BlogInfoResponse();
        BeanUtils.copyProperties(blogInfo, blogInfoResponse);
        return blogInfoResponse;
    }
    public static UserInfoResponse trans(UserInfo userInfo) {
        if(userInfo==null){
            return null;
        }
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }
}
