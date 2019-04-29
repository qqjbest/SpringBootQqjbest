package com.qqj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qqj.entity.Strategy;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-04-24
 */
public interface StrategyMapper extends BaseMapper<Strategy> {

    /**
     *  根据账号获取策略列表
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-26 10:01
     */
    Set<String> getStrategyByAccount(String account);

    /**
     * 根据账号获取权限列表
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-26 10:03
     */
    Set<String> getPermissionByAccount(String account);

    /**
     * 根据角色id获取策略列表
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 16:44
     */
    List<Strategy> getByRoleId(Long id);

    /**
     * 获取全部
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 16:49
     */
    List<Strategy> getAllByMap(Map<String, Object> params);

    /**
     * 批量删除
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 16:52
     */
    void deleteByPrimaryKeys(Long[] longs);
}
