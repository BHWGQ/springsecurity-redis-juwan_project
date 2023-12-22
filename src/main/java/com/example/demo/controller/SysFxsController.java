package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.dto.req.SysFxsDeleteReq;
import com.example.demo.dto.resp.*;
import com.example.demo.entity.SysFxsEntity;
import com.example.demo.service.SysFxsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Api(tags = "用户分销商接口管理")
@RequestMapping("/Fxs")
public class SysFxsController {
    @Resource
    private SysFxsService service;

    @GetMapping("/getAll")
    @ApiOperation(value = "获取分销商名单")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<List<SysFxsResp>> response (){
        List<SysFxsEntity>sysFxsEntity = service.getAll();
        if (Objects.isNull(sysFxsEntity)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        List<SysFxsResp> resps = sysFxsEntity.stream()
                .map(entity ->
                BeanUtil.copyProperties(entity, SysFxsResp.class))
                .collect(Collectors.toList());
        return ResponseUtil.create(ResponseCodeEnum.OK,resps);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "添加分销商")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<String> stringResponse (@RequestBody SysFxsDeleteReq req){
        String a = service.insert(req);
        if (Objects.isNull(a)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,null);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "移除分销商")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<String> stringResponse1 (@RequestBody SysFxsDeleteReq req){
        String a = service.delete(req);
        if (Objects.isNull(a)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,null);
    }
}
