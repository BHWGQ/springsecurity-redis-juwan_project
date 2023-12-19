package com.example.demo.dto.resp;

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
public class SysLoginResp {
    private String id;

    private String userName;

    private long phoneNumber;

    private String roler;

    private String beiZhu;

    private Date createTime;
}
