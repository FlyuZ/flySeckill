package com.nwafu.seckill.exception;

/**
 * 秒杀相关的异常
 *
 * @auther
 * @date 2019/6
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
