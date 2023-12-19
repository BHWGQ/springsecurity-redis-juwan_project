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
public class SysOrderManagerResp {
    private String order;

    private String czqd;

    private String number;

    private String yys;

    private String address;

    private String xieHao;

    private String region;

    private String orderStatus;

    private String czKind;

    private Date czTime;

    private String origin;

    private String originStatus;
}
