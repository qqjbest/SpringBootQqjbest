package com.qqj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qqj.entity.RoleStrategy;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-04-25
 */
public interface IRoleStrategyService extends IService<RoleStrategy> {
    /**
     * 根据角色ids删除角色策略
     *
     * @param roleIds
     */
    void deleteByRoleIds(String roleIds);

    /**
     * 根据策略ids删除角色策略
     *
     * @param strategyIds
     */
    void deleteByStrategyIds(String strategyIds);

    /**
     * 批量新增
     *
     * @param roleStrategyKeys
     */
    void addTrainRecordBatch(List<RoleStrategy> roleStrategyKeys);

    /**
     * 根据角色id删除角色策略
     *
     * @param id
     */
    void deleteByRoleId(Long id);
}
