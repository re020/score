package com.example.score.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.entity.User;
import com.example.score.service.LoginService;
import com.example.score.utils.JwtUtils;
import com.example.score.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result login(String username, String password, HttpServletResponse response) {
        log.debug("进入login方法");

        //创建UsernamePasswordAuthentication
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        //认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        //生成自定义token
        String token = jwtUtils.createToken(user);

        //将token放入响应头中
        response.setHeader(jwtUtils.getHeader(), token);

        //把userDetails放入缓存
        String str = JSON.toJSONString(user);
        redisTemplate.opsForValue().set(user.getUsername(), str);

        return ResultUtils.success("登录成功");
    }

    @Override
    public Result logout() {
        SecurityContextHolder.clearContext();
        return ResultUtils.success("退出成功");
    }

    @Override
    public Result refreshToken(String token) {
        return null;
    }
}
