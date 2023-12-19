package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blackname")
public class SysBlackNumberEntity {
    @TableField("xuHao")
    private long xuHao;

    @TableField("czNumber")
    private String czNumber;

    @TableField("beiZhu")
    private String beiZhu;
}
