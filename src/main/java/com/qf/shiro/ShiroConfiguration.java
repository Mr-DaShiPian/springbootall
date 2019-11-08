package com.qf.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/User/loginPage");
        shiroFilterFactoryBean.setSuccessUrl("/User/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/User/unauth");
        Map<String,String> map = new HashMap<String,String>();
        map.put("/User/loginPage","anon");
        map.put("/User/login","anon");
        map.put("/**/**","authc");
        map.put("/User/addUser","roles[role1]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    @Bean(name = "securityManager")
    public SecurityManager getSecurityManager(MyRealm myRealm){
        SecurityManager securityManager = new DefaultWebSecurityManager(myRealm);
        return securityManager;
    }
    @Bean(name = "myRealm")
    public MyRealm getRealm(){
        return new MyRealm();
    }

}
