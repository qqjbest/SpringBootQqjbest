package com.qqj.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 登录返回管理员信息
 *
 * @author qqjbest
 * @email qqjbest@sina.com
 * @date 2017年10月10日 15:21:19
 */
@Data
public class VoLoginAdmin implements Serializable
{
    private static final long serialVersionUID = 1L;
    // ID
    private Long id;
    // 姓名
    private String name;
    // 头像名称
    private String avatar;
    // 头像路径
    private String avatarUrl;
    // 账号
    private String account;
    //角色id
    private Integer roleId;
    //角色名称
    private String roleName;
    // 权限策略
    private Set<String> roleStrategy;
    //权限
    private List<VoStrategy> strategies = new ArrayList<>();

    private String sessionId;

    //TODO: 2019-04-29 寻找解决方案
   /* public String getAvatarUrl()
    {
        if (ValidatorUtil.isNull(avatar))
        {
            return BusinessUtil.genPicUrl("portrait_default_178.png");
        }
        else if (avatar.startsWith("http"))
        {
            return avatar;
        }
        return BusinessUtil.genPicUrl(avatar);
    }*/
}
