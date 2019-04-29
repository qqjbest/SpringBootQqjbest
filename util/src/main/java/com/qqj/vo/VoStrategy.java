package com.qqj.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author qqjbest
 * @email qqjbest@sina.com
 * @date 2017年10月10日 15:21:19
 */
@Data
public class VoStrategy implements Serializable
{
    private static final long serialVersionUID = 1L;
    // ID
    private Integer id;
    // 名称
    private String name = "";
    // 备注
    private String remark = "";
    //路径
    private String url = "";
    // 权限
    private Integer permission;
    //下标
    private Integer orderIndex;
    //上级id
    private Integer parentId;
    // 类型
    private Integer type;
    // 状态
    private Integer status;
    // 创建时间
    private Long createTime;
    // 更新时间
    private Long updateTime;
    // 是否拥有
    private Boolean isOwn = false;

    private List<VoStrategy> childs = new ArrayList<>();

}
