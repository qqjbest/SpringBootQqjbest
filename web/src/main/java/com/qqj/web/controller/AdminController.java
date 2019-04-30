package com.qqj.web.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qqj.bean.*;
import com.qqj.entity.Admin;
import com.qqj.entity.AdminRole;
import com.qqj.form.FormAdmin;
import com.qqj.service.IAdminRoleService;
import com.qqj.service.IAdminService;
import com.qqj.service.IRoleService;
import com.qqj.service.IStrategyService;
import com.qqj.shiro.realm.PasswordHelper;
import com.qqj.util.CommonUtil;
import com.qqj.util.ValidatorUtil;
import com.qqj.vo.VoAdmin;
import com.qqj.vo.VoLoginAdmin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 *
 * @author jobob
 * @since 2019-04-25
 */
@RestController
@RequestMapping("/admin/admin")
public class AdminController {
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private IAdminService adminService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IAdminRoleService adminRoleService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody Result login(@ModelAttribute(Key.admin) Admin admin)
    {
        VoLoginAdmin voLoginAdmin = new VoLoginAdmin();
        try
        {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(admin.getAccount(), admin.getPassword());
            subject.login(token);
            Set<String> roleStrategy = strategyService.getPermissionByAccount(admin.getAccount());
            Admin retAdmin = adminService.findByAccount(admin.getAccount());
            CommonUtil.copyPropertiesIgnoreNull(retAdmin, voLoginAdmin);
            voLoginAdmin.setRoleId(3);
            voLoginAdmin.setRoleStrategy(roleStrategy);
        }
        catch (UnknownAccountException e)
        {
            return ResultGenerator.genFailResult("账号或密码错误");
        }
        catch (IncorrectCredentialsException e)
        {
            return ResultGenerator.genFailResult("账号或密码错误");
        }
        catch (ExcessiveAttemptsException e)
        {
            return ResultGenerator.genFailResult("登录失败多次，账户锁定10分钟!");
        }
        catch (LockedAccountException e){
            return ResultGenerator.genFailResult("账号已锁定");
        }
        return ResultGenerator.genSuccessResult(voLoginAdmin);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public @ResponseBody
    Result hellowWord(){
        return ResultGenerator.genSuccessResult("helloworld");
    }

    @RequestMapping(value="/login")
    public <T> Result login(@RequestParam String userName, @RequestParam String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            token.clear();
            return ResultGenerator.genFailResult("登录失败，用户名或密码错误！");
        }
        return ResultGenerator.genSuccessResult("登录成功");
    }

    @RequestMapping(value="/resetPassword")
    public <T> Result reset(@RequestParam Long id){
        Admin admin = adminService.getById(id);
        admin.setPassword("1234567");
        passwordHelper.encryptPassword(admin);
        adminService.updateById(admin);
        return ResultGenerator.genSuccessResult("重置成功");
    }

    @RequestMapping(value = "/all")
    public @ResponseBody
    PagerBean<Admin> getAll(PagerBean<Admin> pager)
    {
        if (pager.isPage())
        {
            Page<Admin> page = PageHelper.startPage(pager.getCurPage(), pager.getLimit());
            adminService.getAllByMap(pager.getSearch());
            pager.setRowData(page.getResult());
            pager.setTotal((int) page.getTotal());
        }
        else
        {
            pager.setRowData(adminService.getAllByMap(pager.getSearch()));
        }
        return pager;
    }

    /**
     * 退出登录
     *
     * @Auther qqjbest qqjbest@sina.com
     * @Date 2017/10/20 17:48
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public @ResponseBody Result loginout()
    {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 跳转页面设计
     *
     * @param model
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String goListHotKey(Model model, HttpServletRequest request, @PathVariable("id") String id)
    {
        request.getSession().setAttribute("ttt", "ccc");
        return "/test";
    }

    /**
     * 查看
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody
    VoAdmin get(@PathVariable("id") Long id)
    {
        Admin admin = adminService.getById(id);
        VoAdmin jsonAdmin = new VoAdmin();
        CommonUtil.copyPropertiesIgnoreNull(admin, jsonAdmin);
        return jsonAdmin;
    }

    /**
     * 修改
     *
     * @param admin
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Result update(FormAdmin admin)
    {
        // 修改姓名等信息
        Admin existAdmin = adminService.getById(admin.getId());
        CommonUtil.copyPropertiesIgnoreNull(admin, existAdmin);
        adminService.updateById(existAdmin);

        // 管理员角色重新建立联系
        adminRoleService.deleteByAdminId(admin.getId());
        addTrainRecordBatchAdminRole(admin.getId(), admin.getRoles());
        return ResultGenerator.genSuccessResult();
    }

    private void addTrainRecordBatchAdminRole(Long id, String roles) {
        if (ValidatorUtil.isNotNull(roles))
        {
            List<AdminRole> adminuserRolesKeys = new ArrayList<>();
            for (String rolesId : roles.split(","))
            {
                AdminRole tempKey = new AdminRole();
                tempKey.setAdminId(id);
                tempKey.setRoleId(Long.parseLong(rolesId));
                adminuserRolesKeys.add(tempKey);
            }
            adminRoleService.addTrainRecordBatch(adminuserRolesKeys);
        }
    }

    /**
     * 修改名称和头像
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/updNameAndAvatar", method = RequestMethod.POST)
    public @ResponseBody Result updNameAndAvatar(Long id, String name, String avatar)
    {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setName(name);
        admin.setAvatar(avatar);
        adminService.updateById(admin);

        VoLoginAdmin voLoginAdmin = new VoLoginAdmin();
        admin = adminService.getById(id);
        CommonUtil.copyPropertiesIgnoreNull(admin, voLoginAdmin);
        return ResultGenerator.genSuccessResult(voLoginAdmin);
    }

    /**
     * 修改密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/updPassword", method = RequestMethod.POST)
    public @ResponseBody Result updPassword(String account, String oldPassword, String newPassword)
    {

        Admin admin = adminService.findByAccount(account);
        if (!passwordHelper.checkPassword(admin, oldPassword))
        {
            return  ResultGenerator.genFailResult("旧密码错误");
        }
        admin.setPassword(newPassword);
        passwordHelper.encryptPassword(admin);
        adminService.updateById(admin);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/validateOldPwd", method = RequestMethod.GET)
    public @ResponseBody Result updPassword(String oldPassword)
    {
        Subject subject = SecurityUtils.getSubject();
        Admin admin = adminService.findByAccount(subject.getPrincipal().toString());
        if (!passwordHelper.checkPassword(admin, oldPassword))
        {
            return ResultGenerator.genFailResult("旧密码错误");
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 新增
     *
     * @param admin
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Result save(FormAdmin admin)
    {
        Admin newAdmin = new Admin();
        CommonUtil.copyPropertiesIgnoreNull(admin, newAdmin);
        newAdmin.setPassword("1234567");
        newAdmin.setStatus(Constants.DATA_STATUS_NORMAL);
        passwordHelper.encryptPassword(newAdmin);
        adminService.saveAdminAndRoles(newAdmin, admin.getRoles());
        return ResultGenerator.genSuccessResult();
    }

}
