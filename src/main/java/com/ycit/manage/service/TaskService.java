package com.ycit.manage.service;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.ycit.manage.bean.modal.Task;
import com.ycit.manage.bean.vo.Mail;
import com.ycit.manage.config.ScheduleConfig;
import com.ycit.manage.config.ScheduleDynamic;
import com.ycit.manage.mapper.TaskMapper;
import com.ycit.manage.util.ConstantDefine;
import com.ycit.manage.util.EmailUtil;
import com.ycit.manage.util.SigarUtil;
import org.apache.commons.lang.StringUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ContextLifecycleScheduledTaskRegistrar;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

/**
 * 任务调度 服务层
 * <p>
 * Created by xlch at 2018/5/7
 */
@Service
public class TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private ScheduleDynamic scheduleDynamic;

    @Autowired
    Environment env;

    @Autowired
    private MailService mailService;

    public Task findById(int id) {
        return taskMapper.findById(id);
    }

    public List<Task> findByGroupId(int groupId) {
        return taskMapper.findByGroup(groupId);
    }

    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    public Task insert(Task task) {
        String[] times = task.getCron().split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        int second = Integer.parseInt(times[2]);
        String cron = second + " " + minute + " " + hour + " * * ?";
        task.setCron(cron);
        taskMapper.insert(task);
        Task taskDb =  this.findById(task.getId());
        if (task.getStatus() == ConstantDefine.TASK_STATUS_RUNNING) {
            insertTask(taskDb);
        }
        return taskDb;
    }



    public int updateById(Task task) {
        Task taskDb = this.findById(task.getId());
        String[] times = task.getCron().split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        int second = Integer.parseInt(times[2]);
        String cron = second + " " + minute + " " + hour + " * * ?";
        task.setCron(cron);
        if (!cron.equals(taskDb.getCron()) && task.getStatus() == ConstantDefine.TASK_STATUS_RUNNING) {
            updateTask(task);
        }
        return taskMapper.updateById(task);
    }


    public int deleteById(int id) {
        Task task = this.findById(id);
        deleteTask(task);
        return this.updateStatusById(id, -1);
    }


    public int updateStatusById(int id, int status) {
        return taskMapper.updateStatusById(id, status);
    }

    /**
     * 动态新增 定时任务
     * @param task
     */
    private void insertTask(Task task) {
        String from = env.getProperty("mail.from");
        String to = env.getProperty("mail.to");
        Map<String, ScheduledFuture<?>> futureMap = scheduleDynamic.scheduledFutureMap;
        String taskKey = ConstantDefine.TASK_SYSTEM_MONITOR_PREFIX + task.getId();
        ScheduledFuture<?> scheduledFuture = scheduleDynamic.threadPoolTaskScheduler.schedule(() -> {
            Mail mail = EmailUtil.getSysStatusMail(from, to);
            mailService.sendHtml(mail);
        }, (triggerContext) -> {
            CronTrigger cronTrigger = new CronTrigger(task.getCron());
            LOGGER.debug("new cronTrigger prepared...........................,new cron is {}", task.getCron());
            return cronTrigger.nextExecutionTime(triggerContext);
        });
        futureMap.put(taskKey, scheduledFuture);
    }

    /**
     * 动态更新 定时任务（先删除 再新增）
     * @param task
     */
    private void updateTask(Task task) {
        this.deleteTask(task);
        this.insertTask(task);
    }


    /**
     * 动态删除 定时任务
     * @param task
     */
    public void deleteTask(Task task) {
        String taskKey = ConstantDefine.TASK_SYSTEM_MONITOR_PREFIX + task.getId();
        Map<String, ScheduledFuture<?>> futureMap = scheduleDynamic.scheduledFutureMap;
        if (futureMap.containsKey(taskKey)) {
            ScheduledFuture<?> scheduledFuture = futureMap.get(taskKey);
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            futureMap.remove(taskKey);
        }
    }


}
