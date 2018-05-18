package com.ycit.manage.task;

import com.ycit.manage.bean.vo.Mail;
import com.ycit.manage.service.MailService;
import com.ycit.manage.service.SpringContext;
import com.ycit.manage.service.SystemService;
import com.ycit.manage.util.DateUtil;
import com.ycit.manage.util.SigarUtil;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.SigarException;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 发送邮件的定时任务
 * <p>
 * Created by xlch at 2018/5/7
 */
@Component
public class MailTask {

    private SystemService systemService;

    private MailService mailService;

    Environment env;

    @Resource
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Resource
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Resource
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Scheduled(cron = "* * * * * ?")
    public void sendMonitorResult() {
        double cpuThreshold = Double.valueOf(systemService.findByName("cpu"));
        double memThreshold = Double.valueOf(systemService.findByName("mem"));
        double diskThreshold = Double.valueOf(systemService.findByName("disk"));
        long t1 = System.currentTimeMillis();
        double cpuUsed = SigarUtil.getCpuTotal();

        Mem mem = null;
        try {
            mem = SigarUtil.getInstance().getMem();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        float memUsed = (float) mem.getUsed() / (float) (1024L * 1024L * 1024L);

        double diskUsed = SigarUtil.getDiskTotal();

        double cpu = cpuUsed * (double) 100;
        if (cpuThreshold < cpu) {
            StringBuilder text = new StringBuilder();
            text.append("您的系统于").append(DateUtil.longToLocalDateTime(t1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .append(" cpu利用率超出").append(cpuThreshold).append("% 的阀值");
            Mail mail = new Mail(env.getProperty("mail.from"), env.getProperty("mail.to"),
                    "平台监控告警通知", text.toString());
            mailService.sendMsg(mail);
        }
        if (memThreshold < memUsed) {
            StringBuilder text = new StringBuilder();
            text.append("您的系统于").append(DateUtil.longToLocalDateTime(t1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .append(" 内存使用率超出").append(memThreshold).append("G 的阀值");
            Mail mail = new Mail(env.getProperty("mail.from"), env.getProperty("mail.to"),
                    "平台监控告警通知", text.toString());
            mailService.sendMsg(mail);
        }
        if (diskThreshold < diskUsed) {
            StringBuilder text = new StringBuilder();
            text.append("您的系统于").append(DateUtil.longToLocalDateTime(t1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .append(" 硬盘总的使用超出").append(diskThreshold).append("G 的阀值");
            Mail mail = new Mail(env.getProperty("mail.from"), env.getProperty("mail.to"),
                    "平台监控告警通知", text.toString());
            mailService.sendMsg(mail);
        }
        System.out.println("scheduled   success============================================= time is" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
