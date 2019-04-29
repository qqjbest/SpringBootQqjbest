package com.qqj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqj.entity.AdminRole;
import com.qqj.mapper.AdminRoleMapper;
import com.qqj.service.IAdminRoleService;
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
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {
    @Override
    public void deleteByAdminId(Long adminId) {
        baseMapper.deleteByAdminId(adminId);
    }

    @Override
    public void addTrainRecordBatch(List<AdminRole> adminRoles) {
        baseMapper.addTrainRecordBatch(adminRoles);
    }

    @Override
    public void deleteByRoleIds(String roleIds) {
        baseMapper.deleteByRoleIds(CommonUtil.strArray2longArray(roleIds.trim().split(",")));
    }
}
