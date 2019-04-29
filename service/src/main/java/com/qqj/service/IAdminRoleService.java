package com.qqj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qqj.entity.AdminRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-04-25
 */
public interface IAdminRoleService extends IService<AdminRole> {

    /**
     * 根据管理员id解除管理员和角色的关系
     *
     * @param adminId
     */
    void deleteByAdminId(Long adminId);

    /**
     * 批量新增管理员角色关系
     *
     * @param adminRoles
     */
    void addTrainRecordBatch(List<AdminRole> adminRoles);

    /**
     * 根据角色ids删除
     *
     * @param roleIds
     */
    void deleteByRoleIds(String roleIds);
}
