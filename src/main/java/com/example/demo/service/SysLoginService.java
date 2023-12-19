package com.example.demo.service;

import com.example.demo.dto.req.SysLoginInsertReq;
import com.example.demo.dto.req.SysLoginReq;
import com.example.demo.dto.resp.SysLoginResp;
import com.example.demo.entity.SysLoginEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLoginService {
    String login(SysLoginReq req);

    List<SysLoginResp> getAll();

    String regist(SysLoginInsertReq req);

    SysLoginEntity getByUsername(String username);

    List<String> getUserPermissions(String username);

    String getUserPassword(String username);
}
