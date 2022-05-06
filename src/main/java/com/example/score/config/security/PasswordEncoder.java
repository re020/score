package com.example.score.config.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder extends BCryptPasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //接收到的前端密码
        String pwd = rawPassword.toString();

        // 进行rsa解密
        /*try {
            pwd = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, pwd);
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }*/

        if (!StringUtils.isBlank(encodedPassword)) {
            return BCrypt.checkpw(pwd, encodedPassword);
        } else {
            return false;
        }
    }
}
