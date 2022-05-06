package com.example.score.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.utils.JwtUtils;
import com.example.score.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;

    private final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("--LoginSuccessHandler--onAuthenticationSuccess()--");
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        //生成JWT，并放置到请求头中
        System.out.println("生成token，放入请求头中");
        System.out.println(authentication.getName());
        //String jwt = jwtUtils.createToken(authentication.getName(), "待修改");
        String jwt = "jwt";
        response.setHeader(jwtUtils.getHeader(), jwt);

        Result result = ResultUtils.success("success login");

        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
