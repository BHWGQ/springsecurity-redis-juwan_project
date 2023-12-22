package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.dto.req.SysBlackNumberInsertReq;
import com.example.demo.dto.req.SysBlackNumberReq;
import com.example.demo.dto.resp.*;
import com.example.demo.entity.SysBlackNumberEntity;
import com.example.demo.service.SysBlackNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Api(tags = "用户黑名单接口管理")
@RequestMapping("/blackNumber")
public class SysBlackNumberController {
    @Resource
    private SysBlackNumberService service;

    @GetMapping("/getAll")
    @ApiOperation(value = "获取黑名单用户",notes = "获取所有的黑名单用户",tags = {"用户黑名单接口管理"})
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<List<SysBlackNumberResp>> listResponse (){
        List<SysBlackNumberEntity> sysBlackNumberEntities = service.getAllBlackNumber();
        if (Objects.isNull(sysBlackNumberEntities)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        List<SysBlackNumberResp> resps = sysBlackNumberEntities.stream()
                .map(entity -> BeanUtil.copyProperties(entity,SysBlackNumberResp.class)).collect(Collectors.toList());
        return ResponseUtil.create(ResponseCodeEnum.OK,resps);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "移除黑名单用户",notes = "移除该黑名单用户",tags = {"用户黑名单接口管理"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<SysBlackNumberDeleteResp> listResponse(@RequestBody SysBlackNumberReq req){
        SysBlackNumberDeleteResp sysBlackNumberEntities = service.deleteBlackNumber(req);
        if (Objects.isNull(sysBlackNumberEntities)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,sysBlackNumberEntities);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "插入黑名单用户",notes = "将用户的号码以及备注信息插入黑名单中",tags = {"用户黑名单接口管理"})
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<SysBlackNumberDeleteResp> sysBlackNumberDeleteRespResponse (@RequestBody SysBlackNumberInsertReq req){
        SysBlackNumberDeleteResp sysBlackNumberDeleteResp = service.insertNumber(req);
        if (Objects.isNull(sysBlackNumberDeleteResp)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,sysBlackNumberDeleteResp);
    }
}
