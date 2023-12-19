package com.example.demo.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysOrderStatisticsResp {
    private long CzAllDan;

    private long CzAll;

    private long CzIngDan;

    private long CzIng;

    private long CzSuccessDan;

    private long CzSuccess;

    private long CzFailDan;

    private long CzFail;

    private long CzWaitDan;

    private long CzWait;
}
