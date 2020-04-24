package com.company.project.core;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * 统一API响应结果封装
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;
    private Date date = new Date();

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public Date getDate() {
        return date;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
