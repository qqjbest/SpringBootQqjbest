package com.qqj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qqj.entity.Strategy;

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

    Set<String> getStrategyByAccount(String account);

    Set<String> getPermissionByAccount(String account);
}
