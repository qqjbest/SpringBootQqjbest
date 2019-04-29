package com.qqj.form;

import lombok.Data;

/**
 * 管理员表单对象
 *
 * @author qqjbest
 * @since 2016年7月12日 下午10:55:00
 */
@Data
public class FormAdmin
{
    private Long id;

    private String name;

    private String account;

    private boolean locked = false;

    private String roles;
}
