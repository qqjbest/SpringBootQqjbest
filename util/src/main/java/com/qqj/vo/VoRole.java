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
public class VoRole implements Serializable
{
    private static final long serialVersionUID = 1L;
    // ID
    private Integer id;
    // 名称
    private String name;
    // 备注
    private String remark;
    // 状态
    private Integer status;
    private Boolean isOwn = false;
    // 创建时间
    private Long createTime;
    // 更新时间
    private Long updateTime;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }

    public Long getCreateTime()
    {
        return createTime;
    }

    public void setUpdateTime(Long updateTime)
    {
        this.updateTime = updateTime;
    }

    public Long getUpdateTime()
    {
        return updateTime;
    }

    public Boolean getOwn()
    {
        return isOwn;
    }

    public void setOwn(Boolean own)
    {
        isOwn = own;
    }
}
