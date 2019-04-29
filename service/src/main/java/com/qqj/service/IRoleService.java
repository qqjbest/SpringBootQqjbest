package com.qqj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qqj.entity.Role;
import com.qqj.form.FormRole;

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
public interface IRoleService extends IService<Role> {

    public List<Role> getAllByMap(Map<String,Object> params);

    /**
     *  保存角色和策略
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 11:28
     */
    void saveRoleAndStrategys(FormRole role);

    /**
     * 更新角色和策略
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 11:28
     */
    void updateRoleAndStrategys(FormRole role);

    /**
     * 删除角色
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 11:33
     */
    void delete(String ids);

    /**
     * 根据管理员id获取
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 11:33
     */
    List<Role> getByAdminId(Long id);

    /**
     * 获取启用状态下的角色列表
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 11:34
     */
    List<Role> getAllEnable();

    /**
     * 启用
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 13:35
     */
    void enable(Long id);

    /**
     * 禁用
     *
     * @author: qjqiu  qjqiu@onlyou.com
     * @Date: 2019-04-29 13:35
     */
    void disable(Long id);
}
