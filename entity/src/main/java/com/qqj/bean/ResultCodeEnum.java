package com.qqj.bean;
/**
 * @auther qqjbest qqjbest
 * @create 2019-04-26
 */

/**
 *
 * @auther qjqiu
 * @create 2019-04-26
 */
public enum ResultCodeEnum {
    SUCCESS("1"), //成功
    FAIL("400"),  //失败
    UNAUTHORIZED("401"), //未认证
    NOT_COUND("404"), //接口不存在
    INTERNAL_SERVER_ERROR("400"); //服务器内部错误

    public String status;

    ResultCodeEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
