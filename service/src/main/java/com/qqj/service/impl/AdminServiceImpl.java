package com.qqj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqj.entity.Admin;
import com.qqj.entity.AdminRole;
import com.qqj.mapper.AdminMapper;
import com.qqj.service.IAdminRoleService;
import com.qqj.service.IAdminService;
import com.qqj.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private IAdminRoleService adminRoleService;

    @Autowired
    private RedisTemplate redisTemplate;


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

    @Override
    public List<Admin> getAllByMap(Map<String, Object> search) {
        return baseMapper.getAllByMap(search);
    }

    @Override
    public void saveAdminAndRoles(Admin admin, String roles) {
        baseMapper.updateById(admin);
        addTrainRecordBatchAdminRole(admin.getId(), roles);
    }

    @Override
    @Cacheable(value = "admin", key = "#id")
    public Admin getAdminById(Long id) {
        Admin admin = baseMapper.selectById(id);
        return admin;
    }

    @Override
    @CachePut(value = "admin", key = "#admin.id")
    public void saveAdmin(Admin admin) {
        baseMapper.insert(admin);
    }


    private void addTrainRecordBatchAdminRole(Long id, String roles) {
        if (ValidatorUtil.isNotNull(roles))
        {
            List<AdminRole> adminRoles = new ArrayList<>();
            for (String rolesId : roles.split(","))
            {
                AdminRole tempKey = new AdminRole();
                tempKey.setAdminId(id);
                tempKey.setRoleId(Long.parseLong(rolesId));
                adminRoles.add(tempKey);
            }
            adminRoleService.addTrainRecordBatch(adminRoles);
        }
    }
}
