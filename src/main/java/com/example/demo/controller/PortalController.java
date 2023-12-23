package com.example.demo.controller;

import com.example.demo.dto.req.SysLoginReq;
import com.example.demo.dto.resp.Response;
import com.example.demo.dto.resp.ResponseCodeEnum;
import com.example.demo.dto.resp.ResponseUtil;
import com.example.demo.service.SysLoginService;
import com.example.demo.service.impl.PasswordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@Api(tags = "用户登录接口管理")
@RestController
public class PortalController {

    @Resource
    private SysLoginService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/user/login")
    @ApiOperation(value = "登录的具体接口")
    public Response<String> stringResponse (@RequestBody SysLoginReq req){
        try {
//            // 创建BCryptPasswordEncoder实例
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            // 对密码进行加密
//            String encodedPassword = passwordEncoder.encode(req.getPassword());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String token = service.login(req);
            if (Objects.isNull(token)){
                return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
            }
            passwordService.storePassword(req.getUsername(),req.getPassword());
            return ResponseUtil.create(ResponseCodeEnum.OK,token);
        }catch (BadCredentialsException e){
            throw new RuntimeException("用户名密码有问题");
        }
    }
}
