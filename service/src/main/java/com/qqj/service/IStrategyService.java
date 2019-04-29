package com.qqj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qqj.entity.Strategy;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-04-25
 */
public interface IStrategyService extends IService<Strategy> {

    /**
     * 启用
     *
     * @author qqjbest
     * @since 2017年10月10日 15:21:19
     * @param id
     */
    void enable(Long id);

    /**
     * 禁用
     *
     * @author qqjbest
     * @since 2017年10月10日 15:21:19
     * @param id
     */
    void disable(Long id);

    /**
     * 根据账号获取角色策略
     *
     * @param account
     * @return
     */
    Set<String> getStrategyByAccount(String account);

    /**
     * 根据账号获取角色权限
     *
     * @param account
     * @return
     */
    Set<String> getPermissionByAccount(String account);

    /**
     * 根据角色id获取策略
     *
     * @param id
     * @return
     */
    List<Strategy> getByRoleId(Long id);

    /**
     * 获取所有状态正常的策略
     *
     * @return
     */
    List<Strategy> getAllEnable();

    /**
     * 根据策略ids删除
     *
     * @param ids
     */
    void delete(String ids);

    List<Strategy> getAllByMap(Map<String, Object> search);
}
