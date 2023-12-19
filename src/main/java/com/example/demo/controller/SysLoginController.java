package com.example.demo.controller;

import com.example.demo.dto.req.SysLoginInsertReq;
import com.example.demo.dto.req.SysLoginReq;
import com.example.demo.dto.resp.Response;
import com.example.demo.dto.resp.ResponseCodeEnum;
import com.example.demo.dto.resp.ResponseUtil;
import com.example.demo.dto.resp.SysLoginResp;
import com.example.demo.service.SysLoginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/login")
public class SysLoginController {
    @Resource
    private SysLoginService service;

//    @PostMapping()
    public Response<String> stringResponse (@RequestBody SysLoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        String token = service.login(req);
        if (Objects.isNull(token)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,token);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<List<SysLoginResp>> listResponse (){
        List<SysLoginResp> sysLoginResps = service.getAll();
        if (Objects.isNull(sysLoginResps)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,sysLoginResps);
    }

    @PostMapping("/regist")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<String> stringResponse (@RequestBody SysLoginInsertReq req){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        req.setPassword(passwordEncoder.encode(req.getPassword()));
        String a = service.regist(req);
        if (Objects.isNull(a)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,null);
    }
}
