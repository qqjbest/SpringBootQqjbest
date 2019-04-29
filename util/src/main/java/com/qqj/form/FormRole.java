package com.qqj.form;

import lombok.Data;

/**
 * 角色表单对象
 *
 * @author qqjbest
 * @since 2016年7月12日 下午10:11:38
 */
@Data
public class FormRole
{
    private Long id;

    private String name;

    private String remark;

    private Byte status;

    private String strategys;
}
