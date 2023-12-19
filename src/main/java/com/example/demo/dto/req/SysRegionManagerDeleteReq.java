package com.example.demo.dto.req;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysRegionManagerDeleteReq {
    private long xuHao;

    private String skuId;

    private String region;

    private String beiZhu;

    private Date createTime;
}
