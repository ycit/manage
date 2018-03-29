package com.ycit.config;

import com.ycit.security.AppLogoutFilter;
import com.ycit.security.AppRealm;
import com.ycit.security.AppUserFilter;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.Filter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * spring 主配置文件
 *
 * @author xlch
 * @Date 2018-03-21 11:18
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="com.ycit.mapper")
@ComponentScan(basePackages = {"com.ycit.service", "com.ycit.security"})
public class AppConfig {

    /**
     * 文件上传配置
     * @return CommonsMultipartResolver
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxUploadSize(50000000);
        return commonsMultipartResolver;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() throws IOException {
        Resource resource = new ClassPathResource("auth.properties");
        String authConfig = FileCopyUtils.copyToString(new FileReader(resource.getFile()));
        Map<String, Filter> filters = new HashMap<>();
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setFilters(filters);
        shiroFilter.setFilterChainDefinitions(authConfig);
//        shiroFilter.setSuccessUrl("/back/users");
        return shiroFilter;
    }

    @Bean
    public WebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(appRealm());
        securityManager.setRealms(realms);
        return securityManager;
    }

    @Bean
    public Realm appRealm() {
        return new AppRealm();
    }

    @Bean(name="app")
    public Filter appFilter() {
        return new AppUserFilter();
    }

    @Bean(name = "logout")
    public Filter logoutFilter() {
        return new AppLogoutFilter();
    }

}
