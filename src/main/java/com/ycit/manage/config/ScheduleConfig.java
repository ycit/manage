package com.ycit.manage.config;

import com.ycit.manage.bean.modal.Task;
import com.ycit.manage.bean.vo.Mail;
import com.ycit.manage.service.MailService;
import com.ycit.manage.service.TaskService;
import com.ycit.manage.util.ConstantDefine;
import com.ycit.manage.util.EmailUtil;
import com.ycit.manage.util.SigarUtil;
import org.hyperic.sigar.CpuPerc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务动态配置
 * <p>
 * Created by xlch at 2018/5/8
 */
//@Component
//@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    private Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<String, ScheduledFuture<?>>();

    public ScheduledTaskRegistrar taskRegistrar;

    @Autowired
    Environment env;

    @Autowired
    private MailService mailService;

    @Autowired
    private TaskService taskService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        String from = env.getProperty("mail.from");
        String to = env.getProperty("mail.to");
        List<Task> tasks = taskService.findByGroupId(ConstantDefine.TASK_SYSTEM_MONITOR_EMAIL);
        if (!CollectionUtils.isEmpty(tasks)) {
            tasks.forEach(task -> {
                taskRegistrar.addTriggerTask(() -> {
                    Mail mail = EmailUtil.getSysStatusMail(from, to);
                    mailService.sendHtml(mail);
                }, (triggerContext) -> {
                    CronTrigger cronTrigger = new CronTrigger(task.getCron());
                    System.out.println("dynamic schedule run...........................");
                    return cronTrigger.nextExecutionTime(triggerContext);
                });
            });
        }
        this.taskRegistrar = taskRegistrar;

    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }
}
