package com.ycit.manage.controller;

import com.ycit.manage.bean.modal.Config;
import com.ycit.manage.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统控制层
 * <p>
 * Created by xlch at 2018/5/3
 */
@Controller
@RequestMapping("/back/system")
public class SystemController {

    private SystemService systemService;

    @Resource
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("configs", systemService.finds());
        return "/sys/sys-setting";
    }

    @ResponseBody
    @RequestMapping("/config/edit")
    public int update(@RequestBody List<Config> configs) {
        return systemService.updateBatch(configs);
    }

}
