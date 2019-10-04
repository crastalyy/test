package com.hw.shirospringboot.shiro;

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

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
         //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截
         * 常用的过滤器
         *   anon:无需认证（登陆）可以访问
         *   authc:必须认证才可以访问
         *   user:如果使用rememberMe的功能可以直接访问
         *   perms：该资源必须得到资源权限才可以访问
         *   role:该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap =new LinkedHashMap<String,String>();

        // filterMap.put("/add","authc");
        //filterMap.put("/update","authc");
        filterMap.put("/test","anon");
        filterMap.put("/denglu","anon");
        filterMap.put("/*","authc");
        filterMap.put("/add","perms[user-add]");
        filterMap.put("/update","perms[user-update]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login");

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联数据
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "realm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 用配置shiroDialect,用于thymeleaf与shiro标签配合使
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
