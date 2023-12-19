package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.req.SysRegionManagerDeleteReq;
import com.example.demo.dto.req.SysRegionManagerInsertReq;
import com.example.demo.dto.resp.SysRegionManagerResp;
import com.example.demo.entity.SysBlackNumberEntity;
import com.example.demo.entity.SysRegionManagerEntity;
import com.example.demo.mapper.SysRegionManagerMapper;
import com.example.demo.service.SysRegionManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class SysRegionManagerServiceImpl extends ServiceImpl<SysRegionManagerMapper, SysRegionManagerEntity> implements SysRegionManagerService {

    @Resource
    private SysRegionManagerMapper sysRegionManagerMapper;

    @Override
    public List<SysRegionManagerEntity> getAll() {
        return sysRegionManagerMapper.selectList(null);
    }


    // 创建一个Date对象，它将表示当前系统时间
    Date currentDate = new Date();

    // 定义日期时间格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 将Date对象按照指定格式转换为字符串
    String formattedDate = sdf.format(currentDate);

    @Override
    public String deleteRegion(SysRegionManagerDeleteReq req) {
        LambdaQueryWrapper<SysRegionManagerEntity> sysRegionManagerEntityLambdaQueryWrapper = new QueryWrapper<SysRegionManagerEntity>().lambda()
                .eq(SysRegionManagerEntity::getXuHao,req.getXuHao())
                .eq(SysRegionManagerEntity::getSkuId,req.getSkuId())
                .eq(SysRegionManagerEntity::getRegion,req.getRegion());
        SysRegionManagerEntity sysRegionManagerEntity = sysRegionManagerMapper.selectOne(sysRegionManagerEntityLambdaQueryWrapper);
        if (Objects.isNull(sysRegionManagerEntity)){
            return null;
        }
        sysRegionManagerMapper.delete(sysRegionManagerEntityLambdaQueryWrapper);
        LambdaUpdateWrapper<SysRegionManagerEntity> lambdaQueryWrapper = new UpdateWrapper<SysRegionManagerEntity>().lambda()
                .gt(SysRegionManagerEntity::getXuHao,req.getXuHao())
                .setSql("xu_hao = xu_hao - 1");
        sysRegionManagerMapper.update(null,lambdaQueryWrapper);
        return "success";
    }

    @Override
    public String insertRegion(SysRegionManagerInsertReq req) {
        LambdaQueryWrapper<SysRegionManagerEntity>sysRegionManagerEntityLambdaQueryWrapper = new QueryWrapper<SysRegionManagerEntity>().lambda()
                .eq(SysRegionManagerEntity::getSkuId,req.getSkuId())
                .eq(SysRegionManagerEntity::getRegion,req.getRegion());
        SysRegionManagerEntity sysRegionManagerEntity = sysRegionManagerMapper.selectOne(sysRegionManagerEntityLambdaQueryWrapper);
        if (!Objects.isNull(sysRegionManagerEntity)){
            return null;
        }
        //获取最大的序号值
        LambdaQueryWrapper<SysRegionManagerEntity> maxXuhao = new QueryWrapper<SysRegionManagerEntity>().lambda()
                .select(SysRegionManagerEntity::getXuHao)
                .orderByDesc(SysRegionManagerEntity::getXuHao)
                .last("LIMIT 1");
        SysRegionManagerEntity maxEntity = sysRegionManagerMapper.selectOne(maxXuhao);
        long max = (maxEntity != null) ? maxEntity.getXuHao() : 0;
        SysRegionManagerEntity sysRegionManagerEntity1 = new SysRegionManagerEntity();
        sysRegionManagerEntity1.setXuHao(max + 1);
        sysRegionManagerEntity1.setSkuId(req.getSkuId());
        sysRegionManagerEntity1.setRegion(req.getRegion());
        sysRegionManagerEntity1.setBeiZhu(req.getBeiZhu());
        sysRegionManagerEntity1.setCreateTime(currentDate);
        sysRegionManagerMapper.insert(sysRegionManagerEntity1);
        return "success";
    }
}
