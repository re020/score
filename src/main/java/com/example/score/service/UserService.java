package com.example.score.service;

import com.example.score.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findUserByName(String username);

}
