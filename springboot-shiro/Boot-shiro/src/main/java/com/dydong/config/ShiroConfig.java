package com.dydong.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        //添加shiro的内置过滤器
        /*
            anon:无需认证就可以访问
            antuc:只有认证才能访问
            perms:拥有某个资源权限才可访问
            role:拥有某个角色权限才可访问
         */
        Map<String, String> filterMap =new LinkedHashMap<String,String>();
        //权限授权
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

//        filterMap.put("/user/add","anon");
        filterMap.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录页
        bean.setLoginUrl("/toLogin");
        //未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    //2：创建管理用户需要用到
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);

        return securityManager;

    }

    //创建 realm对象，自定义类
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiroDialect:用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}