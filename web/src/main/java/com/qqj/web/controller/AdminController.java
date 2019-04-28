package com.qqj.web.controller;


import com.qqj.bean.Result;
import com.qqj.bean.ResultGenerator;
import com.qqj.entity.Admin;
import com.qqj.service.IAdminService;
import com.qqj.shiro.realm.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author jobob
 * @since 2019-04-25
 */
@RestController
@RequestMapping("/common/admin")
public class AdminController {

    @Autowired
    private PasswordHelper passwordHelper;

    @Autowired
    private IAdminService adminService;

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
    public <T> Result reset(@RequestParam Integer id){

        Admin admin = adminService.getById(id);
        admin.setPassword("12345678");
        passwordHelper.encryptPassword(admin);
        adminService.updateById(admin);
        return ResultGenerator.genSuccessResult("重置成功");
    }

/*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody String login(@ModelAttribute("admin") Admin admin){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getAccount(), admin.getPassword());
        subject.login(token);
        return "index";
    }

    @RequestMapping("/findByAccount")
    @ResponseBody
    public Admin findByAccount(String username) {
        Admin admin = adminService.findByAccount(username);
        return admin;
    }*/
}
