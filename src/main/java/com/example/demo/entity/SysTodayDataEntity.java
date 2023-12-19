package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("today_data")
public class SysTodayDataEntity {

    @TableField("today_all")
    private long tadayAll;

    @TableField("today_yidong")
    private long todayYidong;

    @TableField("today_dianxin")
    private long todayDianxin;

    @TableField("today_liantong")
    private long todayLiantong;
}
