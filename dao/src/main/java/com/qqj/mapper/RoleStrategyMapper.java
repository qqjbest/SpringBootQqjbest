package com.qqj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qqj.entity.RoleStrategy;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-04-24
 */
public interface RoleStrategyMapper extends BaseMapper<RoleStrategy> {
    void deleteByRoleIds(Long[] longs);

    void deleteByStrategyIds(Long[] longs);

    void addTrainRecordBatch(List<RoleStrategy> roleStrategyKeys);

    void deleteByRoleId(Long id);
}
