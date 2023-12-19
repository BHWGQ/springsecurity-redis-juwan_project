package com.example.demo.service;

import com.example.demo.dto.req.SysCzManager1Req;
import com.example.demo.dto.req.SysCzManagerReq;
import com.example.demo.dto.req.SysCzManagerXReq;
import com.example.demo.dto.resp.SysTodayDataResp;
import com.example.demo.entity.SysCzManagerEntity;

import java.util.List;

public interface SysCzManagerService {
    List<SysCzManagerEntity> getAll(Integer a);

    long gettodadata(String a);

    long getyd(String b);

    long getdx(String c);

    long getLt(String d);

    String xiugai(List<SysCzManagerXReq> req);

    long getCzAllDan();

    long getCzAll();

    long getCzIngDan();

    long getCzIng();

    long getCzSuccessDan();

    long getCzSuccess();

    long getCzFailDan();

    long getCzFail();

    long getCzWaitDan();

    long getCzWait();

    String insertcz(SysCzManager1Req req);
}
