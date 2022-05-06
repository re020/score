package com.example.score.controller;

import com.example.score.common.Result;
import com.example.score.config.security.handler.LoginSuccessHandler;
import com.example.score.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    private final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @PostMapping("/login")
    public Result login(String username, String password, HttpServletResponse response) {
        logger.info("LoginController--login");
        return loginService.login(username, password, response);
    }

    @RequestMapping(value = "/loginPage")
    @ResponseBody
    public String loginPage() {
        return "欢迎";
    }

    @RequestMapping(value = "/student/testx")
    @ResponseBody
    public String test() {
        System.out.println("--LoginController---test()---");
        return "student testx";
    }
}
