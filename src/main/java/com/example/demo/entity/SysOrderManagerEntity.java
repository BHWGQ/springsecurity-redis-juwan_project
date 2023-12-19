package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_manager")
public class SysOrderManagerEntity {
    @TableField("`order`")
    private String order;

    private String czqd;

    @TableField("`number`")
    private String number;

    private String yys;

    private String address;

    @TableField("xie_hao")
    private String xieHao;

    private String region;

    @TableField("order_status")
    private String orderStatus;

    @TableField("cz_kind")
    private String czKind;

    @TableField("cz_time")
    private Date czTime;

    private String origin;

    @TableField("origin_status")
    private String originStatus;

}
