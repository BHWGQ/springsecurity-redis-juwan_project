package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.req.SysFxsDeleteReq;
import com.example.demo.entity.SysFxsEntity;
import com.example.demo.mapper.SysFxsMapper;
import com.example.demo.service.SysFxsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class SysFxsServiceImpl extends ServiceImpl<SysFxsMapper, SysFxsEntity> implements SysFxsService {
    @Resource
    private SysFxsMapper sysFxsMapper;

    @Override
    public List<SysFxsEntity> getAll() {
        return sysFxsMapper.selectList(null);
    }

    Date currentDate = new Date();

    // 定义日期时间格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 将Date对象按照指定格式转换为字符串
    String formattedDate = sdf.format(currentDate);

    @Override
    public String insert(SysFxsDeleteReq req) {
        LambdaQueryWrapper<SysFxsEntity> sysFxsEntityLambdaQueryWrapper = new QueryWrapper<SysFxsEntity>().lambda()
                .eq(SysFxsEntity::getFxs,req.getFxs());
        SysFxsEntity sysFxsEntity = sysFxsMapper.selectOne(sysFxsEntityLambdaQueryWrapper);
        if (!Objects.isNull(sysFxsEntity)){
            return null;
        }
        SysFxsEntity sysFxs = new SysFxsEntity(req.getFxs(),currentDate);
        sysFxsMapper.insert(sysFxs);
        return "success";
    }

    @Override
    public String delete(SysFxsDeleteReq req) {
        LambdaQueryWrapper<SysFxsEntity> sysFxsEntityLambdaQueryWrapper = new QueryWrapper<SysFxsEntity>().lambda()
                .eq(SysFxsEntity::getFxs,req.getFxs());
        SysFxsEntity sysFxsEntity = sysFxsMapper.selectOne(sysFxsEntityLambdaQueryWrapper);
        if (Objects.isNull(sysFxsEntity)){
            return null;
        }
        sysFxsMapper.delete(sysFxsEntityLambdaQueryWrapper);
        return "success";
    }
}
