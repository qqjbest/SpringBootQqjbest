package com.qqj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqj.bean.Constants;
import com.qqj.entity.Strategy;
import com.qqj.mapper.StrategyMapper;
import com.qqj.service.IRoleStrategyService;
import com.qqj.service.IStrategyService;
import com.qqj.util.CommonUtil;
import com.qqj.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private IRoleStrategyService roleStrategyService;

    @Override
    public Set<String> getStrategyByAccount(String account) {
        return baseMapper.getStrategyByAccount(account);
    }

    @Override
    public Set<String> getPermissionByAccount(String account) {
        return baseMapper.getPermissionByAccount(account);
    }


    @Override
    public void enable(Long id)
    {
        Strategy strategy = new Strategy();
        strategy.setId(id);
        strategy.setStatus(Constants.DATA_STATUS_NORMAL);
        strategy.setUpdateTime(DateUtils.getSystemTime());
        baseMapper.updateById(strategy);
    }

    @Override
    public void disable(Long id)
    {
        Strategy strategy = new Strategy();
        strategy.setId(id);
        strategy.setStatus(Constants.DATA_STATUS_DEL);
        strategy.setUpdateTime(DateUtils.getSystemTime());
        baseMapper.updateById(strategy);
    }

    @Override
    public List<Strategy> getByRoleId(Long id)
    {
        return baseMapper.getByRoleId(id);
    }

    @Override
    public List<Strategy> getAllEnable()
    {
        Map<String, Object> params = new HashMap<>();
        params.put("status", Constants.DATA_STATUS_NORMAL);
        return baseMapper.getAllByMap(params);
    }

    @Override
    public void delete(String ids)
    {
        roleStrategyService.deleteByStrategyIds(ids);

        deleteByIds(ids);
    }

    private void deleteByIds(String ids) {
        baseMapper.deleteByPrimaryKeys(CommonUtil.strArray2longArray(ids.trim().split(",")));
    }

    @Override
    public List<Strategy> getAllByMap(Map<String, Object> search) {
        return baseMapper.getAllByMap(search);
    }
}
