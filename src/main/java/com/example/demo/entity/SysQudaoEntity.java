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
@TableName("qudao")
public class SysQudaoEntity {
    @TableField("qudao")
    private String Qudao;

    @TableField("Czapi")
    private String Api;

    @TableField("create_time")
    private Date createTime;
}
