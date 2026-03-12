package com.bit.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bit.blog.common.exception.BlogException;
import com.bit.blog.common.util.BeanTransUtils;
import com.bit.blog.common.util.JwtUtils;
import com.bit.blog.common.util.SecurityUtil;
import com.bit.blog.mapper.UserInfoMapper;
import com.bit.blog.pojo.dataobject.BlogInfo;
import com.bit.blog.pojo.dataobject.UserInfo;
import com.bit.blog.pojo.request.UserLoginRequest;
import com.bit.blog.pojo.response.UserInfoResponse;
import com.bit.blog.pojo.response.UserLoginResponse;
import com.bit.blog.service.BlogInfoService;
import com.bit.blog.service.UserInfoService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Resource(name = "blogInfoServiceImpl")
    private BlogInfoService blogInfoService;
    @Override
    public UserLoginResponse checkPassword(UserLoginRequest userLoginRequest) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getUserName, userLoginRequest.getUserName())
                .eq(UserInfo::getDeleteFlag,0);

        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if(userInfo==null){
            throw new BlogException("用户不存在");
        }
        if(!SecurityUtil.verify(userInfo.getPassword(),userLoginRequest.getPassword())){
            throw new BlogException("用户密码错误");
        }
        Map<String,Object>map=new HashMap<>();
        map.put("name",userInfo.getUserName());
        map.put("id",userInfo.getId());
        String token= JwtUtils.genToken(map);
        return new UserLoginResponse(userInfo.getId(),token);
    }

    @Override
    public UserInfoResponse getUserInfo(Integer userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getDeleteFlag, 0)
                .eq(UserInfo::getId, userId);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        return BeanTransUtils.trans(userInfo);
    }

    @Override
    public UserInfoResponse getAuthorInfo(Integer blogId) {
        BlogInfo blogInfo=blogInfoService.getBlogInfo(blogId);
        if(blogInfo==null||blogInfo.getUserId()<0){
            throw new BlogException("博客不存在");
        }
        return getUserInfo(blogInfo.getUserId());
    }


}
