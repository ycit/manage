package com.ycit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 资讯管理控制层
 *
 * @author xlch
 * @Date 2018-03-22 11:42
 */
@Controller
@RequestMapping("/back")
public class NewsManagerController {

    @RequestMapping(value = "/newses", method = RequestMethod.GET)
    public String home(Model model) {
        return "/news-manager";
    }

}
