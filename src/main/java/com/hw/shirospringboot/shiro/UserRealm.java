package com.hw.shirospringboot.shiro;

import com.hw.shirospringboot.entity.User;
import com.hw.shirospringboot.service.UserService;
import com.hw.shirospringboot.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user-add");
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        User dbuser= userService.findUserById(user.getId());
        info.addStringPermission(dbuser.getPerms());
        return info;
    }

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token1= (UsernamePasswordToken) token;
        User user = userService.findByName(token1.getUsername());
        if(user==null){
            //用户名不存在
            return null;
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
