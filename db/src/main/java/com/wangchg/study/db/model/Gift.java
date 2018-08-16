package com.wangchg.study.db.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Date;

public class Gift implements Serializable {
    private Long id;
    private String key;
    private Date createTime;
    private int flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
