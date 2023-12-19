package com.example.demo.Exception;

public class TeamException extends CommonException {
    public static final TeamException Login_ShiXiao = new TeamException("登录凭证已过期，请重新登录");

    public TeamException(String message) {
        super(message);
    }

    public TeamException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeamException(Throwable cause) {
        super(cause);
    }

    public TeamException() {
    }
}
