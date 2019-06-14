package com.nwafu.seckill.exception;

/**
 * 秒杀关闭异常
 * @auther
 * @date 2019/6
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
