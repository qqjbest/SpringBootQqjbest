package com.qqj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qqj.entity.Role;
import com.qqj.mapper.RoleMapper;
import com.qqj.service.IRoleService;
import org.springframework.stereotype.Service;

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

}
