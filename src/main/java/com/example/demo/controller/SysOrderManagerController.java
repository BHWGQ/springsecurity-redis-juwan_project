package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.dto.resp.Response;
import com.example.demo.dto.resp.ResponseCodeEnum;
import com.example.demo.dto.resp.ResponseUtil;
import com.example.demo.dto.resp.SysOrderManagerResp;
import com.example.demo.entity.SysOrderManagerEntity;
import com.example.demo.service.SysOrderManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping("/order")
@RestController
public class SysOrderManagerController {

    @Autowired
    private SysOrderManagerService service;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<List<SysOrderManagerResp>> listResponse () {
        List<SysOrderManagerEntity> sysOrderManagerEntities = service.getAll();
        if (Objects.isNull(sysOrderManagerEntities)) {
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL, null);
        }
        List<SysOrderManagerResp> sysOrderManagerResps = sysOrderManagerEntities.stream()
                .map(sysOrderManagerEntity -> BeanUtil.copyProperties(sysOrderManagerEntity, SysOrderManagerResp.class)).collect(Collectors.toList());
        return ResponseUtil.create(ResponseCodeEnum.OK, sysOrderManagerResps);
    }
}
