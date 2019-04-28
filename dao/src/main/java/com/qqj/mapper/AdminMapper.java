package com.qqj.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qqj.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminMapper extends BaseMapper<Admin> {
    List<Admin> getAllByMap(Map<String,Object> params);
    Long countByMap(Map<String,Object> params);
}
