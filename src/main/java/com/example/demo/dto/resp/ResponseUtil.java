package com.example.demo.dto.resp;

/**
 * @description: 响应对象工具类，统一一下获取响应对象的方式，便于后期增加对象池优化
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-04-09 12:41:58
 * @modify:
 */

public class ResponseUtil {

    private ResponseUtil() {
    }

    public static Response<Object> create(ResponseCodeEnum code) {
        return ResponseUtil.create(code, "", "");
    }

    public static <T> Response<T> create(ResponseCodeEnum code, T data) {
        return create(code, data, "");
    }

    public static <T> Response<T> create(ResponseCodeEnum code, T data, String errorMessage) {
        return create(code, data, errorMessage, "");
    }

    public static <T> Response<T> create(ResponseCodeEnum code, T data, String errorMessage, String tipMessage) {
        return new Response<>(code, data, errorMessage, tipMessage);
    }

}
