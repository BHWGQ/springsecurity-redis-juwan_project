package com.example.demo.controller;

import cn.hutool.core.util.ObjUtil;
import com.example.demo.dto.req.SysLoginReq;
import com.example.demo.dto.resp.Response;
import com.example.demo.dto.resp.ResponseCodeEnum;
import com.example.demo.dto.resp.ResponseUtil;
import com.example.demo.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
public class PortalController {

    @Resource
    private SysLoginService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/user/login")
    public Response<String> stringResponse (@RequestBody SysLoginReq req){
        try {
//            // 创建BCryptPasswordEncoder实例
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//            // 对密码进行加密
//            String encodedPassword = passwordEncoder.encode(req.getPassword());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
//        if (ObjUtil.isNull(authenticate)){
//            throw new RuntimeException("用户名密码错误！");
//        }
            String token = service.login(req);
            if (Objects.isNull(token)){
                return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
            }
            return ResponseUtil.create(ResponseCodeEnum.OK,token);
        }catch (BadCredentialsException e){
            throw new RuntimeException("用户名密码有问题");
        }
    }
}
