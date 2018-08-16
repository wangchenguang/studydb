package com.wangchg.study.db.exception;

public class SecKillCloseException extends RuntimeException {
    public SecKillCloseException() {
        super("活动已关闭");
    }
}
