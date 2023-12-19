package com.example.demo.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysRegionManagerResp {
    private long xuHao;

    private String skuId;

    private String region;

    private String beiZhu;

    private Date createTime;
}
