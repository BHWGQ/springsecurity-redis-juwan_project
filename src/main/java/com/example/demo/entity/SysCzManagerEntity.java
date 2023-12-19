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
@TableName("cz_manager")
public class SysCzManagerEntity {
    @TableField("`order`")
    private String order;

    @TableField("`number`")
    private String number;

    private String region;

    private String yys;

    private String address;

    @TableField("xie_hao")
    private String xieHao;

    private String status;

    @TableField("cz_kind")
    private String czKind;

    @TableField("create_time")
    private Date createTime;

    private String origin;

    @TableField("origin_status")
    private String originStatus;
}
