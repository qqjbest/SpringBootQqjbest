package com.qqj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqj.entity.RoleStrategy;
import com.qqj.mapper.RoleStrategyMapper;
import com.qqj.service.IRoleStrategyService;
import com.qqj.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-04-25
 */
@Service
public class RoleStrategyServiceImpl extends ServiceImpl<RoleStrategyMapper, RoleStrategy> implements IRoleStrategyService {

    @Override
    public void deleteByRoleIds(String roleIds)
    {
        baseMapper.deleteByRoleIds(CommonUtil.strArray2longArray(roleIds.split(",")));
    }

    @Override
    public void deleteByStrategyIds(String strategyIds)
    {
        baseMapper.deleteByStrategyIds(CommonUtil.strArray2longArray(strategyIds.trim().split(",")));
    }

    @Override
    public void addTrainRecordBatch(List<RoleStrategy> roleStrategyKeys)
    {
        baseMapper.addTrainRecordBatch(roleStrategyKeys);
    }

    @Override
    public void deleteByRoleId(Long id)
    {
        baseMapper.deleteByRoleId(id);
    }
}
