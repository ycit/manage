package com.ycit.manage.controller;

import com.ycit.manage.bean.vo.KeyValue;
import com.ycit.manage.bean.vo.ReportVo;
import com.ycit.manage.util.DateUtil;
import com.ycit.manage.util.SigarUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * 平台监控
 * <p>
 * Created by xlch at 2018/5/3
 */
@Controller
@RequestMapping("/back/monitor")
public class MonitorController {

    @RequestMapping(value = "/home")
    public String home() {
        return "/monitor/monitor";
    }

    @ResponseBody
    @RequestMapping(value = "/cpu", method = RequestMethod.GET)
    public ReportVo cpuInfo() {
        return SigarUtil.getCpuInfo();
    }

    @ResponseBody
    @RequestMapping(value = "/mem", method = RequestMethod.GET)
    public ReportVo memInfo() {
        return SigarUtil.getMemoryInfo();
    }

    @ResponseBody
    @RequestMapping(value = "/disk", method = RequestMethod.GET)
    public ReportVo diskInfo() {
        return SigarUtil.getDiskInfo();
    }


}
