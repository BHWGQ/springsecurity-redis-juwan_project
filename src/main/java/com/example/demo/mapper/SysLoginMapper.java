package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.dto.resp.SysLoginResp;
import com.example.demo.entity.SysLoginEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysLoginMapper extends BaseMapper<SysLoginEntity> {

    @Select("select id,user_name,phone_number,roler,bei_zhu,create_time from login_table")
    List<SysLoginResp> getAll();
}
