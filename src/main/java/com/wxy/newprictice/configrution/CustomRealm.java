package com.wxy.newprictice.configrution;

import com.wxy.newprictice.entity.User;
import com.wxy.newprictice.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("harry:show");
        stringSet.add("user:admin");
        info.setStringPermissions(stringSet);
        return info;
    }
    /**
     * 这里可以注入userService
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        //从token中取出用户名
        String userName = (String) authenticationToken.getPrincipal();
        System.out.println(userName);
        //从token中取出密码
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        System.out.println(userPwd);
        //根据用户名从数据库获取密码
        User findbyusername = userService.findbyusername(userName);
        if (!userName.equals(findbyusername.getNickname())) {
            throw new AccountException("用户名不正确");
        }
        if (!userPwd.equals(findbyusername.getPswd())) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(userName,userPwd,getName());
    }
}
