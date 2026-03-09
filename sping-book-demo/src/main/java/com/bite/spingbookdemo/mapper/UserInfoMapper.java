package com.bite.spingbookdemo.mapper;


import com.bite.spingbookdemo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info where user_name=#{name}")
    UserInfo getUserInfoByName(String name);


}
