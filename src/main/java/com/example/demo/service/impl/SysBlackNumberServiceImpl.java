package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.req.SysBlackNumberInsertReq;
import com.example.demo.dto.req.SysBlackNumberReq;
import com.example.demo.dto.resp.SysBlackNumberDeleteResp;
import com.example.demo.entity.SysBlackNumberEntity;
import com.example.demo.mapper.SysBlackNumberMapper;
import com.example.demo.service.SysBlackNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class SysBlackNumberServiceImpl extends ServiceImpl<SysBlackNumberMapper, SysBlackNumberEntity> implements SysBlackNumberService {
    @Resource
    private SysBlackNumberMapper sysBlackNumberMapper;

    @Override
    public List<SysBlackNumberEntity> getAllBlackNumber() {
        return sysBlackNumberMapper.selectList(null);
    }

    @Transactional
    @Override
    public SysBlackNumberDeleteResp deleteBlackNumber(SysBlackNumberReq req) {
        LambdaQueryWrapper<SysBlackNumberEntity> sysBlackNumberEntityLambdaQueryWrapper = new QueryWrapper<SysBlackNumberEntity>().lambda()
                .eq(SysBlackNumberEntity::getXuHao,req.getXuHao())
                .eq(SysBlackNumberEntity::getCzNumber,req.getCzNumber())
                .eq(SysBlackNumberEntity::getBeiZhu,req.getBeiZhu());
        SysBlackNumberEntity sysBlackNumberEntity = sysBlackNumberMapper.selectOne(sysBlackNumberEntityLambdaQueryWrapper);
        if (Objects.isNull(sysBlackNumberEntity)){
            return null;
        }
        sysBlackNumberMapper.delete(sysBlackNumberEntityLambdaQueryWrapper);
        LambdaUpdateWrapper<SysBlackNumberEntity> updateWrapper = new UpdateWrapper<SysBlackNumberEntity>().lambda()
                .gt(SysBlackNumberEntity::getXuHao, req.getXuHao()) // 条件：xuHao大于被删除记录的xuHao
                .setSql("xuHao = xuHao - 1"); // 更新语句：xuHao字段减去1
        sysBlackNumberMapper.update(null, updateWrapper);
        return new SysBlackNumberDeleteResp().setCzNumber(req.getCzNumber());
    }

    @Override
    public SysBlackNumberDeleteResp insertNumber(SysBlackNumberInsertReq req) {
        LambdaQueryWrapper<SysBlackNumberEntity> sysBlackNumberEntityLambdaQueryWrapper = new QueryWrapper<SysBlackNumberEntity>().lambda()
                .eq(SysBlackNumberEntity::getCzNumber,req.getCzNumber());
        SysBlackNumberEntity sysBlackNumberEntity = sysBlackNumberMapper.selectOne(sysBlackNumberEntityLambdaQueryWrapper);
        if (!Objects.isNull(sysBlackNumberEntity)){
            return null;
        }
        // 获取数据库中已有数据的最大xuHao值
        LambdaQueryWrapper<SysBlackNumberEntity> maxXuHaoWrapper = new QueryWrapper<SysBlackNumberEntity>().lambda()
                .select(SysBlackNumberEntity::getXuHao).orderByDesc(SysBlackNumberEntity::getXuHao).last("LIMIT 1");
        SysBlackNumberEntity maxEntity = sysBlackNumberMapper.selectOne(maxXuHaoWrapper);
        long maxXuHao = (maxEntity != null) ? maxEntity.getXuHao() : 0;

        // 创建一个新的实体对象，并设置其他两个字段的值
        SysBlackNumberEntity newEntity = new SysBlackNumberEntity();
        newEntity.setXuHao(maxXuHao + 1);
        newEntity.setCzNumber(req.getCzNumber());
        newEntity.setBeiZhu(req.getBeiZhu());

        // 插入数据到数据库
        sysBlackNumberMapper.insert(newEntity);
        return new SysBlackNumberDeleteResp().setCzNumber(req.getCzNumber());
    }
}
