package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.dto.req.SysCzManager1Req;
import com.example.demo.dto.req.SysCzManagerReq;
import com.example.demo.dto.req.SysCzManagerXReq;
import com.example.demo.dto.resp.*;
import com.example.demo.entity.SysCzManagerEntity;
import com.example.demo.service.SysCzManagerService;
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
@RequestMapping("/czmanager")
public class SysCzManagerController {
    @Resource
    private SysCzManagerService service;


    private static final Logger logger = LoggerFactory.getLogger(SysCzManagerController.class);

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public Response<List<SysCzManagerResp>> sysCzManagerRespResponse (@RequestParam("a") Integer a){
        List<SysCzManagerEntity> sysCzManagerEntities = service.getAll(a);
        if (Objects.isNull(sysCzManagerEntities)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        List<SysCzManagerResp> resps = sysCzManagerEntities.stream()
                .map(entity -> BeanUtil.copyProperties(entity, SysCzManagerResp.class)).collect(Collectors.toList());
        List<SysCzManagerResp> resps1 = BeanUtil.copyToList(sysCzManagerEntities, SysCzManagerResp.class);

        return ResponseUtil.create(ResponseCodeEnum.OK,resps);
    }

    @GetMapping("/gettodayData")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<String> stringResponse (@RequestBody SysCzManager1Req req){
        String sysCzManagerEntity = service.insertcz(req);
        if (Objects.isNull(sysCzManagerEntity)){
            return ResponseUtil.create(ResponseCodeEnum.UPDATE_FAIL,null);
        }
        return ResponseUtil.create(ResponseCodeEnum.OK,null);
    }
}
