package com.example.score.config.security.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.example.score.entity.User;
import com.example.score.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JWT过滤器通过校验请求头token进行自动登录...");

        //拿到Authorization请求头内的信息
        String token = request.getHeader(jwtUtils.getHeader());

        //判断token是否为空
        if (StrUtil.isNotEmpty(token)) {
            //获取token中的账号
            String account = jwtUtils.getSubjectFromToken(token);

            //如果账号不为空，且上下文中不存在认证信息
            if (StrUtil.isNotEmpty(account) && SecurityContextHolder.getContext().getAuthentication() == null) {
                //在缓存中查询用户
                String str = (String) redisTemplate.opsForValue().get(account);
                User user = JSON.parseObject(str, User.class);

                //验证用户信息与token
                if (user != null && jwtUtils.validateToken(token, user)) {
                    // 组装authentication对象，构造参数是Principal Credentials 与 Authorities
                    // 后面的拦截器里面会用到 grantedAuthorities 方法
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

                    // 将authentication信息放入到上下文对象中
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    log.info("JWT过滤器通过校验请求头token自动登录成功, user : {}", user.getUsername());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
