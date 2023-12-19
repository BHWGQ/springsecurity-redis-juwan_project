package com.example.demo.service;

import com.example.demo.dto.req.SysFxsDeleteReq;
import com.example.demo.entity.SysFxsEntity;

import java.util.List;

public interface SysFxsService {
    List<SysFxsEntity> getAll();

    String insert(SysFxsDeleteReq req);

    String delete(SysFxsDeleteReq req);
}
