package com.wxy.newprictice.configrution;

import com.wxy.newprictice.entity.Permmission;
import com.wxy.newprictice.entity.Role;
import com.wxy.newprictice.entity.User;
import com.wxy.newprictice.service.PermmissionService;
import com.wxy.newprictice.service.RoleService;
import com.wxy.newprictice.service.UserService;
import com.wxy.newprictice.util.CheckObjIsEmpty;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm<Per> extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermmissionService permmissionService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User users = userService.findbyusername(username);
        Role role = roleService.findbyUid(users.getId());
        //TODO
        //此处异常需要处理
        if(CheckObjIsEmpty.checkObjAllFieldsIsNull(role)){
            throw new NullPointerException("该用户没有对应的访问角色");
        }
        //TODO
        //此处异常需要处理
        Permmission permmission = permmissionService.findbyRid(role.getId());
        if(CheckObjIsEmpty.checkObjAllFieldsIsNull(permmission)){
            throw new NullPointerException("该角色并没有对应的访问权限");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //用户权限
        Set<String> stringSet = new HashSet<>();
        stringSet.add(permmission.getName());
        info.setStringPermissions(stringSet);
        //用户角色
        Set<String> stringSet2 = new HashSet<>();
        stringSet2.add(role.getType());
        info.setRoles(stringSet2);
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
        System.out.println("------身份认证方法-------");
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
        return new SimpleAuthenticationInfo(userName, userPwd, getName());
    }
}
