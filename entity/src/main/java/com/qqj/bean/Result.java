package com.qqj.bean;

/**
 *
 * @auther qjqiu
 * @create 2019-04-26
 */
public class Result<T> {
    private String status;
    private String errCode;
    private String message = "";
    private  T data;

    public String getStatus() {
        return status;
    }

    public Result() {
    }

    public Result setStatus(ResultCodeEnum status) {
        this.status = status.getStatus();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}
