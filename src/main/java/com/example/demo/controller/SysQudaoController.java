package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.dto.req.SysQudaoDeletReq;
import com.example.demo.dto.req.SysQudaoInsertReq;
import com.example.demo.dto.resp.*;
import com.example.demo.entity.SysQudaoEntity;
import com.example.demo.service.SysQudaoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Qudao")
public class SysQudaoController {
    @Resource
    private SysQudaoService service;

    @GetMapping("/getQudao")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<List<SysQudaoResp>> listResponse (){
        List<SysQudaoEntity> sysQudaoEntity = service.getQudao();
        if (Objects.isNull(sysQudaoEntity)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        List<SysQudaoResp> result = sysQudaoEntity.stream()
                .map(entity -> BeanUtil.copyProperties(entity,SysQudaoResp.class)).collect(Collectors.toList());
        return ResponseUtil.create(ResponseCodeEnum.OK,result);
    }

    @PostMapping("/insert")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<String> stringResponse (@RequestBody SysQudaoInsertReq req){
        String a = service.insert(req);
        if (Objects.isNull(a)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,null);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<String> stringResponse (@RequestBody SysQudaoDeletReq req){
        String a = service.delete(req);
        if (Objects.isNull(a)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,null);
    }
}
