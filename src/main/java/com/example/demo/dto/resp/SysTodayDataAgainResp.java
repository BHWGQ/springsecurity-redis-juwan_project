package com.example.demo.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysTodayDataAgainResp {

    private SysTodayDataResp SysTodayDataResp;

    private SysTodayDataResp SysTodayDataResp1;

    private SysTodayDataResp SysTodayDataResp2;

    private SysTodayDataResp SysTodayDataResp3;
}
