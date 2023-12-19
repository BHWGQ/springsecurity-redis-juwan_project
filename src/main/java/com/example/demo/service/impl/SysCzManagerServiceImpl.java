package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.req.SysCzManager1Req;
import com.example.demo.dto.req.SysCzManagerReq;
import com.example.demo.dto.req.SysCzManagerXReq;
import com.example.demo.dto.resp.SysTodayDataResp;
import com.example.demo.entity.SysBlackNumberEntity;
import com.example.demo.entity.SysCzManagerEntity;
import com.example.demo.entity.SysOrderManagerEntity;
import com.example.demo.mapper.SysBlackNumberMapper;
import com.example.demo.mapper.SysCzManagerMapper;
import com.example.demo.mapper.SysOrderManagerMapper;
import com.example.demo.service.SysCzManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Service
public class SysCzManagerServiceImpl extends ServiceImpl<SysCzManagerMapper, SysCzManagerEntity> implements SysCzManagerService {

    @Resource
    private SysCzManagerMapper sysCzManagerMapper;

    @Resource
    private SysBlackNumberMapper sysBlackNumberMapper;

    @Resource
    private SysOrderManagerMapper sysOrderManagerMapper;

    @Override
    public List<SysCzManagerEntity> getAll(Integer a) {
        return sysCzManagerMapper.selectList(null);
    }

    @Override
    public long gettodadata(String a) {
        LocalDate currentDate = LocalDate.now();
        //使用了MySQL的date_format函数来从数据库中提取create_time字段的日期部分，并将其与currentDate进行比较
        LambdaQueryWrapper<SysCzManagerEntity>sysCzManagerEntityLambdaQueryWrapper = new QueryWrapper<SysCzManagerEntity>().lambda()
                .apply("date_format(create_time, '%Y-%m-%d') = {0}", currentDate);
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate);
        List<SysCzManagerEntity> resultList = sysCzManagerMapper.selectList(sysCzManagerEntityLambdaQueryWrapper);
        List<SysOrderManagerEntity> resultList1 = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        // 遍历resultList并将amount字段值相加
        long totalAmount = 0L;
        long totalAmount1 = 0L;
        for (SysCzManagerEntity entity : resultList) {
            totalAmount += Long.parseLong(entity.getRegion());
        }
        for (SysOrderManagerEntity entity : resultList1) {
            totalAmount1 += Long.parseLong(entity.getRegion());
        }
//        // 构造SysTodayDataResp对象，将totalAmount放入对象中
//        SysTodayDataResp sysTodayDataResp = new SysTodayDataResp();
//        sysTodayDataResp.setTadayAll(totalAmount);
        return totalAmount+totalAmount1;
    }

    @Override
    public long getyd(String b) {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysCzManagerEntity>sysCzManagerEntityLambdaQueryWrapper = new QueryWrapper<SysCzManagerEntity>().lambda()
                .apply("date_format(create_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysCzManagerEntity::getYys,b);
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysOrderManagerEntity::getYys,b);
        List<SysCzManagerEntity> resultList = sysCzManagerMapper.selectList(sysCzManagerEntityLambdaQueryWrapper);
        List<SysOrderManagerEntity> resultList1 = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        // 遍历resultList并将amount字段值相加
        long totalAmount = 0L;
        long totalAmount1 = 0L;
        for (SysCzManagerEntity entity : resultList) {
            totalAmount += Long.parseLong(entity.getRegion());
        }
        for (SysOrderManagerEntity entity : resultList1) {
            totalAmount1 += Long.parseLong(entity.getRegion());
        }
//        // 构造SysTodayDataResp对象，将totalAmount放入对象中
//        SysTodayDataResp sysTodayDataResp = new SysTodayDataResp();
//        sysTodayDataResp.setTadayAll(totalAmount);
        return totalAmount+totalAmount1;
    }

    @Override
    public long getdx(String c) {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysCzManagerEntity>sysCzManagerEntityLambdaQueryWrapper = new QueryWrapper<SysCzManagerEntity>().lambda()
                .apply("date_format(create_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysCzManagerEntity::getYys,c);
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysOrderManagerEntity::getYys,c);
        List<SysCzManagerEntity> resultList = sysCzManagerMapper.selectList(sysCzManagerEntityLambdaQueryWrapper);
        List<SysOrderManagerEntity> resultList1 = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        // 遍历resultList并将amount字段值相加
        long totalAmount = 0L;
        long totalAmount1 = 0L;
        for (SysCzManagerEntity entity : resultList) {
            totalAmount += Long.parseLong(entity.getRegion());
        }
        for (SysOrderManagerEntity entity : resultList1) {
            totalAmount1 += Long.parseLong(entity.getRegion());
        }
//        // 构造SysTodayDataResp对象，将totalAmount放入对象中
//        SysTodayDataResp sysTodayDataResp = new SysTodayDataResp();
//        sysTodayDataResp.setTadayAll(totalAmount);
        return totalAmount+totalAmount1;
    }

    @Override
    public long getLt(String d) {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysCzManagerEntity>sysCzManagerEntityLambdaQueryWrapper = new QueryWrapper<SysCzManagerEntity>().lambda()
                .apply("date_format(create_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysCzManagerEntity::getYys,d);
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysOrderManagerEntity::getYys,d);
        List<SysCzManagerEntity> resultList = sysCzManagerMapper.selectList(sysCzManagerEntityLambdaQueryWrapper);
        List<SysOrderManagerEntity> resultList1 = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        // 遍历resultList并将amount字段值相加
        long totalAmount = 0L;
        long totalAmount1 = 0L;
        for (SysCzManagerEntity entity : resultList) {
            totalAmount += Long.parseLong(entity.getRegion());
        }
        for (SysOrderManagerEntity entity : resultList1) {
            totalAmount1 += Long.parseLong(entity.getRegion());
        }
//        // 构造SysTodayDataResp对象，将totalAmount放入对象中
//        SysTodayDataResp sysTodayDataResp = new SysTodayDataResp();
//        sysTodayDataResp.setTadayAll(totalAmount);
        return totalAmount+totalAmount1;
    }

//    @Override
//    public String xiadan(String order) {
//        LambdaQueryWrapper<SysCzManagerEntity>sysCzManagerEntityLambdaQueryWrapper = new QueryWrapper<SysCzManagerEntity>().lambda()
//                .eq(SysCzManagerEntity::getOrder,order);
//        SysCzManagerEntity sysCzManagerEntity = sysCzManagerMapper.selectOne(sysCzManagerEntityLambdaQueryWrapper);
//        if (Objects.isNull(sysCzManagerEntity)){
//            return null;
//        }
////        sysCzManagerMapper.delete(sysCzManagerEntityLambdaQueryWrapper);
//        return "success";
//    }

    @Override
    public String xiugai(List<SysCzManagerXReq> req) {
        for (SysCzManagerXReq req1 : req){
            LambdaQueryWrapper<SysOrderManagerEntity> lambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                    .eq(SysOrderManagerEntity::getOrder,req1.getOrder());
            SysOrderManagerEntity sysOrderManagerEntity = sysOrderManagerMapper.selectOne(lambdaQueryWrapper);
            if (!Objects.isNull(sysOrderManagerEntity)){
                return "出错了";
            }
            String orderStatus = "充值中";
            SysOrderManagerEntity sysOrderManagerEntity1 = new SysOrderManagerEntity(req1.getOrder(),req1.getCzqd(),req1.getNumber(),req1.getYys(),req1.getAddress(), req1.getXieHao(),req1.getRegion(),orderStatus,req1.getCzKind(),req1.getCreateTime(),req1.getOrigin(),req1.getOriginStatus());
            sysOrderManagerMapper.insert(sysOrderManagerEntity1);
            LambdaQueryWrapper<SysCzManagerEntity> lambdaQueryWrapper1 = new QueryWrapper<SysCzManagerEntity>().lambda()
                    .eq(SysCzManagerEntity::getOrder,req1.getOrder());
            SysCzManagerEntity sysCzManagerEntity = sysCzManagerMapper.selectOne(lambdaQueryWrapper1);
            if (Objects.isNull(sysCzManagerEntity)){
                return "出错了";
            }
            sysCzManagerMapper.delete(lambdaQueryWrapper1);
        }
        return "success";
    }

    @Override
    public long getCzAllDan() {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysCzManagerEntity>sysCzManagerEntityLambdaQueryWrapper = new QueryWrapper<SysCzManagerEntity>().lambda()
                .apply("date_format(create_time, '%Y-%m-%d') = {0}", currentDate);
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate);
        List<SysCzManagerEntity> resultListCzManager = sysCzManagerMapper.selectList(sysCzManagerEntityLambdaQueryWrapper);
        List<SysOrderManagerEntity>resultListOrderManager = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        //将两个长度相加返回
        long a = resultListCzManager.size()+resultListOrderManager.size();
        return a;
    }

    @Override
    public long getCzAll() {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysCzManagerEntity>sysCzManagerEntityLambdaQueryWrapper = new QueryWrapper<SysCzManagerEntity>().lambda()
                .apply("date_format(create_time, '%Y-%m-%d') = {0}", currentDate);
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate);
        List<SysCzManagerEntity> resultListCzManager = sysCzManagerMapper.selectList(sysCzManagerEntityLambdaQueryWrapper);
        List<SysOrderManagerEntity>resultListOrderManager = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        //分别遍历result,将两个result的region字段累加起来再相加
        long totalAmountCz = 0L;
        long totalAmountOrder = 0L;
        for (SysCzManagerEntity entity : resultListCzManager){
            totalAmountCz += Long.parseLong(entity.getRegion());
        }
        for (SysOrderManagerEntity entity : resultListOrderManager){
            totalAmountOrder += Long.parseLong(entity.getRegion());
        }
        long a = totalAmountCz + totalAmountOrder;
        return a;
    }

    @Override
    public long getCzIngDan() {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysOrderManagerEntity::getOrderStatus,"充值中");
        List<SysOrderManagerEntity>resultListOrderManager = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);

        return resultListOrderManager.size();
    }

    @Override
    public long getCzIng() {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysOrderManagerEntity::getOrderStatus,"充值中");
        List<SysOrderManagerEntity>result = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        long totalAmountCzIng = 0L;
        for (SysOrderManagerEntity entity : result){
            totalAmountCzIng += Long.parseLong(entity.getRegion());
        }
        return totalAmountCzIng;
    }

    @Override
    public long getCzSuccessDan() {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysOrderManagerEntity::getOrderStatus,"充值成功");
        List<SysOrderManagerEntity>resultListOrderManager = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        return resultListOrderManager.size();
    }

    @Override
    public long getCzSuccess() {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysOrderManagerEntity::getOrderStatus,"充值成功");
        List<SysOrderManagerEntity>result = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        long totalAmountCzSuccess = 0L;
        for (SysOrderManagerEntity entity : result){
            totalAmountCzSuccess += Long.parseLong(entity.getRegion());
        }
        return totalAmountCzSuccess;
    }

    @Override
    public long getCzFailDan() {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysOrderManagerEntity::getOrderStatus,"充值失败");
        List<SysOrderManagerEntity>resultListOrderManager = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        return resultListOrderManager.size();
    }

    @Override
    public long getCzFail() {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysOrderManagerEntity>sysOrderManagerEntityLambdaQueryWrapper = new QueryWrapper<SysOrderManagerEntity>().lambda()
                .apply("date_format(cz_time, '%Y-%m-%d') = {0}", currentDate)
                .eq(SysOrderManagerEntity::getOrderStatus,"充值失败");
        List<SysOrderManagerEntity>result = sysOrderManagerMapper.selectList(sysOrderManagerEntityLambdaQueryWrapper);
        long totalAmountCzFail = 0L;
        for (SysOrderManagerEntity entity : result){
            totalAmountCzFail += Long.parseLong(entity.getRegion());
        }
        return totalAmountCzFail;
    }

    @Override
    public long getCzWaitDan() {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysCzManagerEntity>sysCzManagerEntityLambdaQueryWrapper = new QueryWrapper<SysCzManagerEntity>().lambda()
                .apply("date_format(create_time, '%Y-%m-%d') = {0}", currentDate);
        List<SysCzManagerEntity> resultListCzManager = sysCzManagerMapper.selectList(sysCzManagerEntityLambdaQueryWrapper);
        return resultListCzManager.size();
    }

    @Override
    public long getCzWait() {
        LocalDate currentDate = LocalDate.now();
        LambdaQueryWrapper<SysCzManagerEntity>sysCzManagerEntityLambdaQueryWrapper = new QueryWrapper<SysCzManagerEntity>().lambda()
                .apply("date_format(create_time, '%Y-%m-%d') = {0}", currentDate);
        List<SysCzManagerEntity> resultList = sysCzManagerMapper.selectList(sysCzManagerEntityLambdaQueryWrapper);
        // 遍历resultList并将amount字段值相加
        long totalAmount = 0L;
        for (SysCzManagerEntity entity : resultList) {
            totalAmount += Long.parseLong(entity.getRegion());
        }
        return totalAmount;
    }

    boolean a = false;
    @Override
    public String insertcz(SysCzManager1Req req) {
        LambdaQueryWrapper<SysCzManagerEntity> sysCzManagerEntityLambdaQueryWrapper = new QueryWrapper<SysCzManagerEntity>().lambda()
                .eq(SysCzManagerEntity::getOrder,req.getOrder());
        SysCzManagerEntity sysCzManagerEntity = sysCzManagerMapper.selectOne(sysCzManagerEntityLambdaQueryWrapper);
        if (Objects.isNull(sysCzManagerEntity)){
            List<SysBlackNumberEntity> sysBlackNumberEntity = sysBlackNumberMapper.selectList(null);
            for (SysBlackNumberEntity entity : sysBlackNumberEntity){
                String blacklistedNumber = entity.getCzNumber();
                if (req.getNumber().equals(blacklistedNumber)){
                    return null;
                }
            }
            SysCzManagerEntity sysCzManagerEntity1 = new SysCzManagerEntity(req.getOrder(),req.getNumber(),req.getRegion(),req.getYys(),req.getAddress(),req.getXieHao(),req.getStatus(),req.getCzKind(),req.getCreateTime(),req.getOrigin(),req.getOriginStatus());
            sysCzManagerMapper.insert(sysCzManagerEntity1);
            return "insert success";
        }
        return null;
    }


    public static LocalDate parseStringToDate(String dateString) {
        // 创建DateTimeFormatter对象，指定日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 将日期字符串转换为LocalDate对象
        return LocalDate.parse(dateString, formatter);
    }

}
