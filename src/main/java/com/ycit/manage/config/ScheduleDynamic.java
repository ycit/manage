package com.ycit.manage.config;

import com.ycit.manage.bean.modal.Task;
import com.ycit.manage.bean.vo.Mail;
import com.ycit.manage.service.MailService;
import com.ycit.manage.service.TaskService;
import com.ycit.manage.util.ConstantDefine;
import com.ycit.manage.util.EmailUtil;
import com.ycit.manage.util.SigarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 动态定时任务
 * <p>
 * Created by xlch at 2018/5/9
 */
@Component
@EnableScheduling
public class ScheduleDynamic {

    public Map<String, ScheduledFuture<?>> scheduledFutureMap = new HashMap<>();

    public ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    Environment env;

    @Autowired
    private MailService mailService;

    @Autowired
    private TaskService taskService;


    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        threadPoolTaskScheduler=new ThreadPoolTaskScheduler();
        /**需要实例化线程*/
        threadPoolTaskScheduler.initialize();
        threadPoolTaskScheduler.setPoolSize(5);
        String from = env.getProperty("mail.from");
        String to = env.getProperty("mail.to");
        List<Task> tasks = taskService.findByGroupId(ConstantDefine.TASK_SYSTEM_MONITOR_EMAIL);
        if (!CollectionUtils.isEmpty(tasks)) {
            tasks.forEach(task -> {
                threadPoolTaskScheduler.setThreadNamePrefix("sys" + task.getId());
                ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(()->{
                    Mail mail = EmailUtil.getSysStatusMail(from, to);
                    mailService.sendHtml(mail);
                },(triggerContext)->{
                    CronTrigger cronTrigger = new CronTrigger(task.getCron());
                    System.out.println("dynamic schedule run...........................");
                    return cronTrigger.nextExecutionTime(triggerContext);
                });
                scheduledFutureMap.put("sys" + task.getId(), scheduledFuture);
            });
        }
        return threadPoolTaskScheduler;
    }

}
