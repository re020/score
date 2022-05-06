package com.example.score.service;

import com.example.score.common.Result;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    Result login(String username, String password, HttpServletResponse response);

    Result logout();

    Result refreshToken(String token);
}
