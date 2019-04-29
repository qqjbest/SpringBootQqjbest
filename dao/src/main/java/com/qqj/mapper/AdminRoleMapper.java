package com.qqj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qqj.entity.AdminRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-04-24
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    void deleteByAdminId(Long adminId);
    void addTrainRecordBatch(List<AdminRole> adminRoles);
    void deleteByRoleIds(Long[] longs);
}
