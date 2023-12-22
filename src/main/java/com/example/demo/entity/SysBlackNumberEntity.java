package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户黑名单类")
@TableName("blackname")
public class SysBlackNumberEntity {
    @ApiModelProperty("序号")
    @TableField("xuHao")
    private long xuHao;

    @TableField("czNumber")
    private String czNumber;

    @TableField("beiZhu")
    private String beiZhu;
}
