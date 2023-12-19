package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SysCzManagerEntity;
import com.example.demo.entity.SysOrderManagerEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysOrderManagerMapper extends BaseMapper<SysOrderManagerEntity> {
    SysOrderManagerEntity selectOne(LambdaQueryWrapper<SysCzManagerEntity> lambdaQueryWrapper);
}
