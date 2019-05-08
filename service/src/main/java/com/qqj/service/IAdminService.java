package com.qqj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qqj.entity.Admin;

import java.util.List;
import java.util.Map;

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

    List<Admin> getAllByMap(Map<String, Object> search);

    /**
     * 保存管理员和角色列表
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 16:12
     */
    void saveAdminAndRoles(Admin admin, String roles);

    /**
     * 根据id获取
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-05-07 16:09
     */
    Admin getAdminById(Long id);

    /**
     * 保存admin
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-05-07 16:39
     */
    void saveAdmin(Admin admin);
}
