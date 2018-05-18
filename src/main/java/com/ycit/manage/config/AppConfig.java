package com.ycit.manage.config;

import com.ycit.manage.security.AppLogoutFilter;
import com.ycit.manage.security.AppRealm;
import com.ycit.manage.security.AppUserFilter;
import jdk.nashorn.internal.objects.annotations.Property;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.Filter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * spring 主配置文件
 *
 * @author xlch
 * @Date 2018-03-21 11:18
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:mail.properties")
@MapperScan(basePackages="com.ycit.manage.mapper")
@ComponentScan(basePackages = {"com.ycit.manage.service", "com.ycit.manage.security", "com.ycit.manage.task"})
public class AppConfig {

    @Autowired
    Environment env;

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

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mail.host"));
        mailSender.setUsername(env.getProperty("mail.username"));
        mailSender.setPassword(env.getProperty("mail.password"));
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.put("mail.debug", "true");
        mailSender.setJavaMailProperties(prop);
        mailSender.setDefaultEncoding("UTF-8");
        return mailSender;
    }

    public SchedulerFactoryBean schedulerFactoryBean() {
        return new SchedulerFactoryBean();
    }

}
