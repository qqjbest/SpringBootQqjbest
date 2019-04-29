package com.qqj.web.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qqj.bean.PagerBean;
import com.qqj.bean.Result;
import com.qqj.bean.ResultGenerator;
import com.qqj.entity.Role;
import com.qqj.form.FormRole;
import com.qqj.service.IRoleService;
import com.qqj.util.CommonUtil;
import com.qqj.util.ValidatorUtil;
import com.qqj.vo.VoRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-04-25
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController{
    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody PagerBean<Role> getAll(PagerBean<Role> pager)
    {
        if (pager.isPage())
        {
            Page<Role> page = PageHelper.startPage(pager.getCurPage(), pager.getLimit());
            roleService.getAllByMap(pager.getSearch());
            pager.setRowData(page.getResult());
            pager.setTotal((int) page.getTotal());
        }
        else
        {
            pager.setRowData(roleService.getAllByMap(pager.getSearch()));
        }
        return pager;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody VoRole get(@PathVariable("id") Long id)
    {
        Role role = roleService.getById(id);
        VoRole jsonrole = new VoRole();
        CommonUtil.copyPropertiesIgnoreNull(role, jsonrole);
        jsonrole.setCreateTime(role.getCreateTime().getTime());
        return jsonrole;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Result post(FormRole role)
    {
        roleService.saveRoleAndStrategys(role);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Result put(FormRole role)
    {
        roleService.updateRoleAndStrategys(role);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "{ids}", method = RequestMethod.DELETE)
    public @ResponseBody Result del(String ids)
    {
        roleService.delete(ids);
        return  ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/getByAdminId")
    public @ResponseBody
    List<VoRole> getByAdminId(Long id)
    {
        List<VoRole> voRoles = new ArrayList<>();
        List<Role> ownRoles = new ArrayList<>();
        if (ValidatorUtil.isNotNull(id))
        {
            ownRoles = roleService.getByAdminId(id);
        }
        for (Role Role : ownRoles)
        {
            VoRole tempRoels = new VoRole();
            CommonUtil.copyPropertiesIgnoreNull(Role, tempRoels);
            tempRoels.setOwn(true);
            voRoles.add(tempRoels);
        }

        List<Role> allRole = roleService.getAllEnable();
        allRole.removeAll(ownRoles);
        for (Role Role : allRole)
        {
            VoRole tempRoels = new VoRole();
            CommonUtil.copyPropertiesIgnoreNull(Role, tempRoels);
            voRoles.add(tempRoels);
        }
        return voRoles;
    }

    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public @ResponseBody Result enable(Long id)
    {
        roleService.enable(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public @ResponseBody Result disable(Long id)
    {
        roleService.disable(id);
        return ResultGenerator.genSuccessResult();
    }
}
