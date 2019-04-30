package com.qqj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqj.bean.Constants;
import com.qqj.entity.Role;
import com.qqj.entity.RoleStrategy;
import com.qqj.form.FormRole;
import com.qqj.mapper.RoleMapper;
import com.qqj.service.IAdminRoleService;
import com.qqj.service.IRoleService;
import com.qqj.service.IRoleStrategyService;
import com.qqj.util.CommonUtil;
import com.qqj.util.DateUtils;
import com.qqj.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-04-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleStrategyService roleStrategyService;

    @Autowired
    private IAdminRoleService adminRoleService;

    @Override
    public List<Role> getByAdminId(Long adminId)
    {
        return baseMapper.getByAdminId(adminId);
    }

    @Override
    public void enable(Long id)
    {
        Role role = new Role();
        role.setId(id);
        role.setStatus(Constants.DATA_STATUS_NORMAL);
        role.setUpdateTime(DateUtils.getSystemTime());
        baseMapper.updateById(role);
    }

    @Override
    public void disable(Long id)
    {
        Role role = new Role();
        role.setId(id);
        role.setStatus(Constants.DATA_STATUS_DEL);
        role.setUpdateTime(DateUtils.getSystemTime());
        baseMapper.updateById(role);
    }

    @Override
    public List<Role> getAllEnable()
    {
        Map<String, Object> params = new HashMap<>();
        params.put("status", Constants.DATA_STATUS_NORMAL);
        return getAllByMap(params);
    }

    @Override
    public void delete(String ids)
    {
        // 1.根据ids删除用户角色
        adminRoleService.deleteByRoleIds(ids);
        // 2.根据ids删除角色策略
        roleStrategyService.deleteByRoleIds(ids);
        // 3.根据ids删除角色
        deleteByIds(ids);
    }

    private void deleteByIds(String ids) {
        baseMapper.deleteByPrimaryKeys(CommonUtil.strArray2longArray(ids.trim().split(",")));
    }

    @Override
    public List<Role> getAllByMap(Map<String, Object> params) {
       return baseMapper.getAllByMap(params);
    }

    @Override
    public void saveRoleAndStrategys(FormRole role)
    {
        // 1.新增角色
        Role newRole = new Role();
        CommonUtil.copyPropertiesIgnoreNull(role, newRole);
        Timestamp currTime = DateUtils.getSystemTime();
        newRole.setCreateTime(currTime);
        newRole.setUpdateTime(currTime);
        save(newRole);
        // 2.新增角色策略
        addTrainRecordBatchRoleStrategys(newRole.getId(), role.getStrategys());
    }

    @Override
    public void updateRoleAndStrategys(FormRole role)
    {
        Role existRole = new Role();
        CommonUtil.copyPropertiesIgnoreNull(role, existRole);
        existRole.setUpdateTime(DateUtils.getSystemTime());
        updateById(existRole);
        roleStrategyService.deleteByRoleId(role.getId());
        addTrainRecordBatchRoleStrategys(role.getId(), role.getStrategys());
    }

    /**
     * 批量新增角色策略
     *
     * @param roleId
     * @param strategys
     */
    private void addTrainRecordBatchRoleStrategys(Long roleId, String strategys)
    {
        if (ValidatorUtil.isNotNull(strategys))
        {
            List<RoleStrategy> roleStrategyKeys = new ArrayList<>();
            for (String strategyId : strategys.split(",")) {
                RoleStrategy roleStrategyKey = new RoleStrategy();
                roleStrategyKey.setRoleId(roleId);
                roleStrategyKey.setStrategyId(Long.parseLong(strategyId));
                roleStrategyKeys.add(roleStrategyKey);
            }
            roleStrategyService.addTrainRecordBatch(roleStrategyKeys);
        }
    }
}
