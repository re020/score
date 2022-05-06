package com.example.score.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.utils.JwtUtils;
import com.example.score.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//登出处理
@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        response.setContentType("application/json;charset=utf-8");
        response.setHeader(jwtUtils.getHeader(), "");
        Result result = ResultUtils.success("success logout");

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
