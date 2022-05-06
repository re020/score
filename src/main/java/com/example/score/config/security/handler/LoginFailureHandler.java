package com.example.score.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.utils.ResultUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("--LoginFailureHandler--onAuthenticationFailure()--");
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();

        /*String errorMessage = "用户名或密码错误";
        Result result;
        if (e instanceof CaptchaException) {
            errorMessage = "验证码错误";
            result = Result.fail(errorMessage);
        } else {
            result = Result.fail(errorMessage);
        }*/

        Result result = ResultUtils.fail("用户名或密码错误");
        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
