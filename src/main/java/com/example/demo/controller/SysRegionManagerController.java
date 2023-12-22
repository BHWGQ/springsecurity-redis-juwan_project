package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.dto.req.SysRegionManagerDeleteReq;
import com.example.demo.dto.req.SysRegionManagerInsertReq;
import com.example.demo.dto.resp.*;
import com.example.demo.entity.SysRegionManagerEntity;
import com.example.demo.service.SysRegionManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Api(tags ="商品SKU面值接口管理")
@RequestMapping("/regionManager")
public class SysRegionManagerController {
    @Resource
    private SysRegionManagerService sysRegionManagerService;

    @GetMapping("/getAll")
    @ApiOperation(value = "获取商品SKU的信息")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<List<SysRegionManagerResp>> listResponse (){
        List<SysRegionManagerEntity> sysRegionManagerResps = sysRegionManagerService.getAll();
        if (Objects.isNull(sysRegionManagerResps)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        List<SysRegionManagerResp> resultList = sysRegionManagerResps.stream()
                .map(entity -> BeanUtil.copyProperties(entity,SysRegionManagerResp.class)).collect(Collectors.toList());
        return ResponseUtil.create(ResponseCodeEnum.OK,resultList);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除该商品的SKU信息")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<SysRegionManagerDeleteResp> sysRegionManagerDeleteRespResponse (@RequestBody SysRegionManagerDeleteReq req){
        String sysRegionManagerEntity = sysRegionManagerService.deleteRegion(req);
        if (Objects.isNull(sysRegionManagerEntity)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,new SysRegionManagerDeleteResp().setSkuId(req.getSkuId()));
    }

    @PostMapping("/insertRegion")
    @ApiOperation(value = "添加商品SKU信息")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<String> stringResponse (@RequestBody SysRegionManagerInsertReq req){
        String result = sysRegionManagerService.insertRegion(req);
        if (Objects.isNull(result)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,null);
    }
}
