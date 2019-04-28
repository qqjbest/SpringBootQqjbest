package com.qqj.shiro.realm;

import com.qqj.entity.Admin;
import com.qqj.service.IAdminService;
import com.qqj.service.IRoleService;
import com.qqj.service.IStrategyService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public class UserRealm extends AuthorizingRealm
{
    @Autowired
    private IAdminService adminuserService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IStrategyService strategyService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        String account = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(strategyService.getStrategyByAccount(account));
        authorizationInfo.setStringPermissions(strategyService.getPermissionByAccount(account));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {

        String username = (String) token.getPrincipal();

        Admin user = adminuserService.findByAccount(username);

        if (user == null)
        {
            throw new UnknownAccountException();// 没找到帐号
        }

        if (user.getLocked())
        {
            throw new LockedAccountException(); // 帐号锁定
        }

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getAccount(), // 用户名
                user.getPassword(), // 密码
                ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=username+salt
                getName() // realm name
        );
        return authenticationInfo;
    }
}
