package com.dydong.config;

import com.dydong.mapper.UserMapper;
import com.dydong.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义realm并且重写方法
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前对象
        Subject subject = SecurityUtils.getSubject();
        //根据下面传值得到user
        User currentUser = (User) subject.getPrincipal();
        info.addStringPermission(currentUser.getPerms());
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");
        //连接真实数据库
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userMapper.queryUserByName(userToken.getUsername());
        if(user==null)  return null;
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);
        //可以加密： MD5
        //密码认证，shiro做，防止泄露，可以加密
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
