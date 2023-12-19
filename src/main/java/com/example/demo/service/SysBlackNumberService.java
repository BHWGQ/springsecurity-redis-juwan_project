package com.example.demo.service;

import com.example.demo.dto.req.SysBlackNumberInsertReq;
import com.example.demo.dto.req.SysBlackNumberReq;
import com.example.demo.dto.resp.SysBlackNumberDeleteResp;
import com.example.demo.entity.SysBlackNumberEntity;

import java.util.List;

public interface SysBlackNumberService {
    List<SysBlackNumberEntity> getAllBlackNumber();

    SysBlackNumberDeleteResp deleteBlackNumber(SysBlackNumberReq req);

    SysBlackNumberDeleteResp insertNumber(SysBlackNumberInsertReq req);
}
