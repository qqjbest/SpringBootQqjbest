package com.qqj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qqj.entity.Admin;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-04-25
 */
public interface IAdminService extends IService<Admin> {
    public List<Admin> getName();

    /**
     * 根据账号获取用户
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-26 9:56
     */
    Admin findByAccount(String username);
}
