package com.rts.config;

public class RedisLimitException extends Throwable {
    public RedisLimitException(String message) {
        super(message);
    }
}
