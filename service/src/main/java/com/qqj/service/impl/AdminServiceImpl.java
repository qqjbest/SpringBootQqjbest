package com.qqj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqj.entity.Admin;
import com.qqj.mapper.AdminMapper;
import com.qqj.service.IAdminService;
import com.qqj.util.ValidatorUtil;
import org.springframework.stereotype.Service;

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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Override
    public List<Admin> getName() {
        QueryWrapper<Admin> q = new QueryWrapper();
        q.like("name","qqj1");
        List<Admin> admins = baseMapper.selectList(q);
        return admins;
    }

    @Override
    public Admin findByAccount(String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("account", username);
        List<Admin> adminusers = baseMapper.getAllByMap(params);
        if (ValidatorUtil.isNotNull(adminusers))
        {
            return adminusers.get(0);
        }
        return null;
    }
}
