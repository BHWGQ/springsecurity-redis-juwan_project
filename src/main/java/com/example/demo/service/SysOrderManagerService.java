package com.example.demo.service;

import com.example.demo.dto.resp.SysOrderManagerResp;
import com.example.demo.entity.SysOrderManagerEntity;

import java.util.List;

public interface SysOrderManagerService {
    List<SysOrderManagerEntity> getAll();
}
