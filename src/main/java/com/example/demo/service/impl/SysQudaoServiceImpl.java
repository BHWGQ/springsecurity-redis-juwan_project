package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.req.SysQudaoDeletReq;
import com.example.demo.dto.req.SysQudaoInsertReq;
import com.example.demo.entity.SysQudaoEntity;
import com.example.demo.mapper.SysQudaoMapper;
import com.example.demo.service.SysQudaoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class SysQudaoServiceImpl extends ServiceImpl<SysQudaoMapper, SysQudaoEntity> implements SysQudaoService {
    @Resource
    private SysQudaoMapper sysQudaoMapper;

    Date currentDate = new Date();

    // 定义日期时间格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 将Date对象按照指定格式转换为字符串
    String formattedDate = sdf.format(currentDate);
    @Override
    public List<SysQudaoEntity> getQudao() {
        return sysQudaoMapper.selectList(null);
    }

    @Override
    public String insert(SysQudaoInsertReq req) {
        LambdaQueryWrapper<SysQudaoEntity> lambdaQueryWrapper = new QueryWrapper<SysQudaoEntity>().lambda()
                .eq(SysQudaoEntity::getApi,req.getApi());
        SysQudaoEntity sysQudaoEntity = sysQudaoMapper.selectOne(lambdaQueryWrapper);
        if (!Objects.isNull(sysQudaoEntity)){
            return null;
        }
        SysQudaoEntity sysQudaoEntity1 = new SysQudaoEntity(req.getQudao(),req.getApi(),currentDate);
        sysQudaoMapper.insert(sysQudaoEntity1);
        return "success";
    }

    @Override
    public String delete(SysQudaoDeletReq req) {
        LambdaQueryWrapper<SysQudaoEntity> sysQudaoEntityLambdaQueryWrapper = new QueryWrapper<SysQudaoEntity>().lambda()
                .eq(SysQudaoEntity::getApi,req.getApi())
                .eq(SysQudaoEntity::getQudao,req.getQudao());
        SysQudaoEntity sysQudaoEntity = sysQudaoMapper.selectOne(sysQudaoEntityLambdaQueryWrapper);
        if (Objects.isNull(sysQudaoEntity)){
            return null;
        }
        sysQudaoMapper.delete(sysQudaoEntityLambdaQueryWrapper);
        return "success";
    }
}
