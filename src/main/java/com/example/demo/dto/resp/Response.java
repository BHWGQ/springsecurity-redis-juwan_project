package com.example.demo.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 服务的响应对象
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-04-08 22:58:20
 * @modify:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private ResponseCodeEnum code;

    private T data;

    private String errorMessage;

    private String tipMessage;
}
