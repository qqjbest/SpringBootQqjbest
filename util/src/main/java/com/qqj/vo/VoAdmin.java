package com.qqj.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author qqjbest
 * @email qqjbest@sina.com
 * @date 2017年10月10日 15:21:19
 */
@Data
public class VoAdmin implements Serializable
{
    private static final long serialVersionUID = 1L;
    // ID
    private Integer id;
    // 姓名
    private String name;
    // 账号
    private String account;
    // 状态
    private Boolean locked;
}
