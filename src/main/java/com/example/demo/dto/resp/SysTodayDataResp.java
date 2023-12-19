package com.example.demo.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysTodayDataResp {
    private long todayAll;

    private long todayYidong;

    private long todayDianxin;

    private long todayLiantong;
}
