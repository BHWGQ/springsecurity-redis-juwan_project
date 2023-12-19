package com.example.demo.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysCzManagerReq {
    private String order;

    private String number;

    private String region;

    private String yys;

    private String address;

    private String xieHao;

    private String status;

    private String czKind;

    private Date createTime;

    private String origin;

    private String originStatus;

}
