package com.qqj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qqj.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-04-24
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getByAdminId(Long adminId);

    List<Role> getAllByMap(Map<String,Object> params);

    void deleteByPrimaryKeys(Long[] longs);
}
