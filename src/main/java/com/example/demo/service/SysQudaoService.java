package com.example.demo.service;

import com.example.demo.dto.req.SysQudaoDeletReq;
import com.example.demo.dto.req.SysQudaoInsertReq;
import com.example.demo.entity.SysQudaoEntity;

import java.util.List;

public interface SysQudaoService {
    List<SysQudaoEntity> getQudao();

    String insert(SysQudaoInsertReq req);

    String delete(SysQudaoDeletReq req);
}
