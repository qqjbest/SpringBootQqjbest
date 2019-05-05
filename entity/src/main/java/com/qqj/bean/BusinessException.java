package com.qqj.bean;
/**
 * @auther qqjbest qqjbest
 * @create 2019-05-05
 */

import lombok.Data;

/**
 *
 * @auther qjqiu
 * @create 2019-05-05
 */
@Data
public class BusinessException extends RuntimeException{

    private String status;
    private String errCode = "";
    private String message = "";

    private static final long serialVersionUID = -4854901217206974677L;

    public BusinessException() {
    }

    public BusinessException(String message){
        this.message = message;
    }

    public BusinessException(String status, String errCode, String message){
        this.status = status;
        this.errCode = errCode;
        this.message = message;
    }
}
