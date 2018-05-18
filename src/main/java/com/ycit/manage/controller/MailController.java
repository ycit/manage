package com.ycit.manage.controller;

import com.ycit.manage.bean.vo.Mail;
import com.ycit.manage.service.MailService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 邮件发送控制层
 * <p>
 * Created by xlch at 2018/5/7
 */
@Controller
@RequestMapping("/back")
public class MailController {

    private MailService mailService;

    private Environment env;

    @Resource
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Resource
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping("/mail/send")
    public void send() {
        Mail mail = new Mail(env.getProperty("mail.from"), env.getProperty("mail.to"), "问候", "你好，早安");
        mailService.sendMsg(mail);
    }

}
