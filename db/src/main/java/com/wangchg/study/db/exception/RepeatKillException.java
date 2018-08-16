package com.wangchg.study.db.exception;

public class RepeatKillException extends RuntimeException {
    public RepeatKillException() {
        super("请勿重复秒杀");
    }
}
