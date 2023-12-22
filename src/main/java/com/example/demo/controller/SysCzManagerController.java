package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.dto.req.SysCzManager1Req;
import com.example.demo.dto.req.SysCzManagerReq;
import com.example.demo.dto.req.SysCzManagerXReq;
import com.example.demo.dto.resp.*;
import com.example.demo.entity.SysCzManagerEntity;
import com.example.demo.service.SysCzManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Api(tags = "用户充值接口管理")
@RequestMapping("/czmanager")
public class SysCzManagerController {
    @Resource
    private SysCzManagerService service;

    @GetMapping("/getAll")
    @ApiOperation(value = "获取未充值的所有用户信息",tags = {"用户充值接口管理"})
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<List<SysCzManagerResp>> sysCzManagerRespResponse (@RequestParam("a") Integer a){
        List<SysCzManagerEntity> sysCzManagerEntities = service.getAll(a);
        if (Objects.isNull(sysCzManagerEntities)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        List<SysCzManagerResp> resps = sysCzManagerEntities.stream()
                .map(entity -> BeanUtil.copyProperties(entity, SysCzManagerResp.class)).collect(Collectors.toList());
        return ResponseUtil.create(ResponseCodeEnum.OK,resps);
    }

    @GetMapping("/gettodayData")
    @ApiOperation(value = "获取今日的数据信息",notes = "分别为充值总金额，移动、电信、联通分金额",tags = {"用户充值接口管理","用户订单接口管理"})
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<SysTodayDataResp> sysTodayDataRespResponse (@RequestParam("a")String a,
                                                                @RequestParam("b") String b,
                                                                @RequestParam("c") String c,
                                                                @RequestParam("d")String d){
        long sysTodayDataResp = service.gettodadata(a);
        long sysTodayDataResp1 = service.getyd(b);
        long sysTodayDataResp2 = service.getdx(c);
        long sysTodayDataResp3 = service.getLt(d);
        SysTodayDataResp sysTodayDataAgainResp = new SysTodayDataResp()
                .setTodayAll(sysTodayDataResp)
                .setTodayYidong(sysTodayDataResp1)
                .setTodayDianxin(sysTodayDataResp2)
                .setTodayLiantong(sysTodayDataResp3);
        if (Objects.isNull(sysTodayDataAgainResp)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,sysTodayDataAgainResp);
    }

    @PostMapping("/xiadan")
    @ApiOperation(value = "模拟给用户完成下单",notes = "完成下单，单号会传递给orderManager",tags = {"用户充值接口管理","用户订单接口管理"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<String> stringResponse (@RequestBody List<SysCzManagerXReq> req){
        String a = service.xiugai(req);
        if (a.equals("出错了")){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,null);
    }

    @GetMapping("/cz")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<SysCzAllResp>sysCzAllRespResponse(){
        long CzAllDan = service.getCzAllDan();
        SysCzAllResp sysCzAllResp = new SysCzAllResp()
                .setCzAllDan(CzAllDan);
        return ResponseUtil.create(ResponseCodeEnum.OK,sysCzAllResp);
    }

    @GetMapping("/orderStatistics")
    @ApiOperation(value = "获取当日信息",notes = "获取充值总量，充值成功，待充值,充值失败，正在充值的订单等信息，获取他们的订单数以及金额",tags = "用户订单接口管理")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<SysOrderStatisticsResp>sysOrderStatisticsRespResponse (){
        long CzAllDan = service.getCzAllDan();
        long CzAll = service.getCzAll();
        long CzIngDan = service.getCzIngDan();
        long CzIng = service.getCzIng();
        long CzSuccessDan = service.getCzSuccessDan();
        long CzSuccess = service.getCzSuccess();
        long CzFailDan = service.getCzFailDan();
        long CzFail = service.getCzFail();
        long CzWaitDan = service.getCzWaitDan();
        long CzWait = service.getCzWait();
        SysOrderStatisticsResp sysOrderStatisticsResp= new SysOrderStatisticsResp()
                .setCzAllDan(CzAllDan)
                .setCzAll(CzAll)
                .setCzIngDan(CzIngDan)
                .setCzIng(CzIng)
                .setCzSuccessDan(CzSuccessDan)
                .setCzSuccess(CzSuccess)
                .setCzFailDan(CzFailDan)
                .setCzFail(CzFail)
                .setCzWaitDan(CzWaitDan)
                .setCzWait(CzWait);
        if (Objects.isNull(sysOrderStatisticsResp)) {
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,sysOrderStatisticsResp);
    }

    @PostMapping("/insertcz")
    @ApiOperation(value = "模拟用户端下单流程",notes = "下单首先会通过黑名单查询，不在黑名单才能成功下单",tags = {"用户充值接口管理","用户黑名单接口管理"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<String> stringResponse (@RequestBody SysCzManager1Req req){
        String sysCzManagerEntity = service.insertcz(req);
        if (Objects.isNull(sysCzManagerEntity)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,null);
    }
}
