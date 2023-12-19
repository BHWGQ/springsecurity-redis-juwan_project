package com.example.demo.dto.resp;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @description: 统一定义各个服务的响应码
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-04-08 22:59:32
 * @modify:
 */

public enum ResponseCodeEnum {

    OK("10001", "成功"),

    FAIL("50000", "账号已经存在，请换一个账号重新注册"),
    LOGIN_FAIL("20001", "登录失败"),
    BAD_TOKEN("20003", "token 校验失败"),
    NO_LOGIN("20005", "未登录"),
    PROJECT_FAIL("00000", "查找项目失败"),
    UPDATE_FAIL("10002", "密码修改失败"),
    UPLOAD_FAIL("20007", "上传文件失败");

    private final String code;

    private final String description;

    ResponseCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
/*

 */