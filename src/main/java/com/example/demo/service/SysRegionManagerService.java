package com.example.demo.service;

import com.example.demo.dto.req.SysRegionManagerDeleteReq;
import com.example.demo.dto.req.SysRegionManagerInsertReq;
import com.example.demo.dto.resp.SysRegionManagerResp;
import com.example.demo.entity.SysRegionManagerEntity;

import java.util.List;

public interface SysRegionManagerService {
    List<SysRegionManagerEntity> getAll();

    String deleteRegion(SysRegionManagerDeleteReq req);

    String insertRegion(SysRegionManagerInsertReq req);
}
