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
@TableName("login_table")
public class SysLoginEntity {
    @TableField("id")
    private String id;

    @TableField("user_name")
    private String userName;

    @TableField("phone_number")
    private long phoneNumber;

    @TableField("roler")
    private String roler;

    @TableField("bei_zhu")
    private String beiZhu;

    @TableField("create_time")
    private Date createTime;

    @TableField("password")
    private String password;

    @TableField("status")
    private Integer status;
}
