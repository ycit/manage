package com.ycit.manage.util;

import com.ycit.manage.bean.vo.Mail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * 发送邮件工具类
 * Created by xlch at 2018/5/4
 */
public class EmailUtil {

    public static Mail getSysStatusMail(String from, String to) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double cpuUsed = SigarUtil.getCpuTotal();
        double memUsed = Double.valueOf(String.format("%.1f", SigarUtil.getMemUsed()));
        double diskUsed = SigarUtil.getDiskTotal();
        StringBuilder content = new StringBuilder();
        content.append("<html><body>").append("<h2>系统在 ").append(now).append("的使用情况:</h2>")
                .append("<h3>cpu利用率:").append(String.format("%.1f", cpuUsed * (double) 100)).append("%</h3>")
                .append("<h3>内存使用量").append(memUsed).append(" G</h3>")
                .append("<h3>磁盘使用量:").append(String.format("%.1f", diskUsed)).append(" G</h3>")
                .append("</body></html>");
        return new Mail(from, to, "系统使用情况", content.toString());
    }

    public static void main(String args[])
    {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost("smtp.163.com");

        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人 用数组发送多个邮件
        // String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
        // mailMessage.setTo(array);
        mailMessage.setTo("chenxl@vastio.com");
        mailMessage.setFrom("ycitcxl@163.com");
        mailMessage.setSubject(" 测试简单文本邮件发送！ ");
        mailMessage.setText(" 测试我的简单邮件发送机制！！ ");

        senderImpl.setUsername("ycitcxl@163.com"); // 根据自己的情况,设置username
        senderImpl.setPassword("wan1314xss"); // 根据自己的情况, 设置password

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);

        System.out.println(" 邮件发送成功.. ");
    }

}
