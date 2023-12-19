package com.example.demo.Interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Exception.TeamException;
import com.example.demo.dto.req.SysLoginReq;
import com.example.demo.dto.resp.LoginUser;
import com.example.demo.entity.SysLoginEntity;
import com.example.demo.service.SysLoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JwtTokenInterceptor extends OncePerRequestFilter {

    private RedisTemplate<String, String> redisTemplate;

    private AuthenticationManager authenticationManager;

    private SysLoginService service;

    public JwtTokenInterceptor(RedisTemplate<String, String> redisTemplate, AuthenticationManager authenticationManager,SysLoginService service) {
        this.redisTemplate = redisTemplate;
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取请求头中的 Authorization 字段
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String jsonString = redisTemplate.opsForValue().get("login:" + token);
            if (!Objects.isNull(jsonString)){
                DecodedJWT decode = JWT.decode(token);
                Claim header = decode.getClaim("userId");
                String userIdFromJWT = header.asString();
                if (!userIdFromJWT.equals(jsonString)) {
                    throw TeamException.Login_ShiXiao;
                }
                String password = service.getUserPassword(userIdFromJWT);
                // 获取用户的权限列表
                List<String> permissions = service.getUserPermissions(userIdFromJWT);

                // 构建 GrantedAuthority 列表
                List<GrantedAuthority> authorities = permissions.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                // 创建 UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userIdFromJWT, password, authorities);
                // 使用 AuthenticationManager 进行认证
                Authentication authenticatedToken = authenticationManager.authenticate(authenticationToken);
                // 设置 Authentication 对象到 SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
                // 更新 Redis 中的缓存
                redisTemplate.opsForValue().set("login:" + token, jsonString, 600, TimeUnit.SECONDS);
            }else {
                throw new RuntimeException("用户未登录");
            }
        }else {
            chain.doFilter(request,response);
            return;
        }
//        super.doFilterInternal(request, response, chain);
        chain.doFilter(request,response);
    }


//    LoginUser loginUser = new ObjectMapper().readValue(jsonString, LoginUser.class);
//                if (!userIdFromJWT.equals(loginUser.getUsername())) {
//        throw TeamException.Login_ShiXiao;
//    }
//    // 获取 LoginUser 对象的权限信息
//    // 创建 UsernamePasswordAuthenticationToken
//    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//    // 使用 AuthenticationManager 进行认证
//    Authentication authenticatedToken = authenticationManager.authenticate(authenticationToken);
//    // 设置 Authentication 对象到 SecurityContextHolder
//                SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
//    // 更新 Redis 中的缓存
//                redisTemplate.opsForValue().set("login:" + token, jsonString, 600, TimeUnit.SECONDS);

}

