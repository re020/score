package com.example.score.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.utils.ResultUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//无权限处理
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("--JwtAccessDeniedHandler--handle()--");
        response.setContentType("application/json;charset=utf-8");
        //403 权限不足，服务器拒绝访问
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ServletOutputStream outputStream = response.getOutputStream();

        Result fail = ResultUtils.fail(accessDeniedException.getMessage());

        outputStream.write(JSON.toJSONString(fail).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
