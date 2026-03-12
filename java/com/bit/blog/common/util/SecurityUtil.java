package com.bit.blog.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Dictionary;
import java.util.UUID;

@Slf4j
public class SecurityUtil {

    public static String encrypt(String password){
        String salt= UUID.randomUUID().toString().replace("-","");
        String securityPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes(StandardCharsets.UTF_8));
        return salt+securityPassword;
    }

    public static boolean verify(String sqlPassword, String inputPassword){
        if(!StringUtils.hasLength(inputPassword)){
            return false;
        }
        if(sqlPassword==null || sqlPassword.length()!=64){
            return false;
        }
        String salt=sqlPassword.substring(0,32);
        log.info("salt:{}",salt);
        String securityPassword = DigestUtils.md5DigestAsHex((salt + inputPassword).getBytes(StandardCharsets.UTF_8));
        log.info("securityPassword:{}",securityPassword);
        return sqlPassword.equals(salt+securityPassword);
    }
}
