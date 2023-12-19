package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.SysCzManagerEntity;
import com.example.demo.entity.SysOrderManagerEntity;
import com.example.demo.mapper.SysOrderManagerMapper;
import com.example.demo.service.SysOrderManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysOrderManagerServiceImpl extends ServiceImpl<SysOrderManagerMapper, SysOrderManagerEntity> implements SysOrderManagerService {

    @Resource
    private SysOrderManagerMapper sysOrderManagerMapper;

    @Override
    public List<SysOrderManagerEntity> getAll() {
        QueryWrapper<SysOrderManagerEntity> sysOrderManagerEntityQueryWrapper = new QueryWrapper<SysOrderManagerEntity>();
        sysOrderManagerEntityQueryWrapper.orderByDesc("cz_time");
        return sysOrderManagerMapper.selectList(sysOrderManagerEntityQueryWrapper);
    }
}
