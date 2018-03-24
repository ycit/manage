package com.ycit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 订单管理 控制层
 *
 * @author xlch
 * @Date 2018-03-22 11:39
 */
@Controller
@RequestMapping("/back")
public class OrderManagerController {

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String home(Model model) {
        return "/order-manager";
    }

}
