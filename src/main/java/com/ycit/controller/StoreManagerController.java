package com.ycit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 专门店管理 控制层
 *
 * @author xlch
 * @Date 2018-03-22 11:40
 */
@Controller
@RequestMapping("/back")
public class StoreManagerController {

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public String home(Model model) {
        return "/store-manager";
    }

}
