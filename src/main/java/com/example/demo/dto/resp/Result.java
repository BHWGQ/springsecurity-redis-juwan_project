package com.example.demo.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xpf
 * @version 1.0
 * @description
 * @since 2023-10-12 09:34:36
 */

// FIXME: 2023/10/17 修改此类中出现的魔法值（20000 这种直接写死的常量）为常量，可以单独新建一个类来专门维护响应 code
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(20000, "success", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(20000, "success", data);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(20000, message, null);
    }

    public static <T> Result<T> fail() {
        return new Result<>(20001, "fail", null);
    }

    public static <T> Result<T> fail(Integer code) {
        return new Result<>(code, "fail", null);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T>
    fail(String message) {
        return new Result<>(20001, message, null);
    }

}
