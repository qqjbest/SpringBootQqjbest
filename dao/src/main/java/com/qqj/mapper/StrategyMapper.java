package com.qqj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qqj.entity.Strategy;

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

}
