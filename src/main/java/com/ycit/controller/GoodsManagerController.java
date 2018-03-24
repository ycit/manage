package com.ycit.controller;

import com.ycit.bean.entity.GoodsSearchForm;
import com.ycit.bean.modal.Goods;
import com.ycit.service.GoodsService;
import com.ycit.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品管理
 *
 * @author xlch
 * @Date 2018-03-22 10:19
 */
@Controller
@RequestMapping("/back")
public class GoodsManagerController {

    private GoodsService goodsService;


    private StoreService storeService;

    @Resource
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }



    @Resource
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    private String home() {
        return "/goods-manager";
    }

    @ResponseBody
    @RequestMapping(value = "/goods", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public List<Goods> finds(GoodsSearchForm searchForm) {
        return goodsService.finds(searchForm);
    }

    @RequestMapping(value = "/goods/add", method = RequestMethod.GET)
    public String addHome(Model model) {
//        List<Brand> brands = brandService.findAll();
//        List<Info> infos = storeService.findAll();
//        model.addAttribute("brands", brands);
//        model.addAttribute("stores", infos);
        return "/goods-add";
    }

}
