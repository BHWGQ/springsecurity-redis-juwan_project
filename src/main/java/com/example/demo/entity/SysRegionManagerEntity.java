package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("region_manager")
public class SysRegionManagerEntity {
    @TableField("xu_hao")
    private long xuHao;

    @TableField("sku_id")
    private String skuId;

    private String region;

    @TableField("bei_zhu")
    private String beiZhu;

    @TableField("create_time")
    private Date createTime;
}
