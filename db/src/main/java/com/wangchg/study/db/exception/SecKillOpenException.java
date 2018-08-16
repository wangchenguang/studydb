package com.wangchg.study.db.exception;

public class SecKillOpenException extends RuntimeException {
    public SecKillOpenException(){
        super("活动未开始");
    }
}
