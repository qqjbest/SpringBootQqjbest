package com.qqj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqj.entity.Strategy;
import com.qqj.mapper.StrategyMapper;
import com.qqj.service.IStrategyService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-04-25
 */
@Service
public class StrategyServiceImpl extends ServiceImpl<StrategyMapper, Strategy> implements IStrategyService {

    @Override
    public Set<String> getStrategyByAccount(String account) {
        return baseMapper.getStrategyByAccount(account);
    }

    @Override
    public Set<String> getPermissionByAccount(String account) {
        return baseMapper.getPermissionByAccount(account);
    }
}
