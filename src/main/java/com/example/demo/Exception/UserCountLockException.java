package com.example.demo.Exception;

import javax.security.sasl.AuthenticationException;

public class UserCountLockException extends AuthenticationException {
    public UserCountLockException(String msg, Throwable t) {
        super(msg, t);
    }
    public UserCountLockException(String msg) {
        super(msg);
    }
}
