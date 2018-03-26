package com.ycit.controller;

import com.google.common.collect.ImmutableList;
import com.ycit.bean.base.ApiResponse;
import com.ycit.bean.entity.GoodsSearchForm;
import com.ycit.bean.modal.Goods;
import com.ycit.bean.modal.dict.Info;
import com.ycit.bean.vo.GoodsVo;
import com.ycit.service.DictInfoService;
import com.ycit.service.GoodsService;
import com.ycit.service.builder.GoodsVoBuilder;
import com.ycit.util.ConstantDefine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品管理
 *
 * @author xlch
 * @Date 2018-03-22 10:19
 */
@Controller
@RequestMapping("/back")
public class GoodsManagerController extends BaseController<Goods> {

    private GoodsService goodsService;

    private DictInfoService dictInfoService;

    private GoodsVoBuilder goodsVoBuilder;

    @Resource
    public void setGoodsVoBuilder(GoodsVoBuilder goodsVoBuilder) {
        this.goodsVoBuilder = goodsVoBuilder;
    }

    @Resource
    public void setDictInfoService(DictInfoService dictInfoService) {
        this.dictInfoService = dictInfoService;
    }

    @Resource
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    private String home(Model model) {
        List<Info> brands = dictInfoService.findByTypeId(ConstantDefine.DICT_BRAND_ID);
        List<Info> stores = dictInfoService.findByTypeId(ConstantDefine.DICT_STORE_ID);
        List<Info> categories = dictInfoService.findByTypeId(ConstantDefine.DICT_CATEGORY_ID);
        List<Info> usages = dictInfoService.findByTypeId(ConstantDefine.DICT_USAGE_ID);
        model.addAttribute("brands", brands);
        model.addAttribute("stores", stores);
        model.addAttribute("categories", categories);
        model.addAttribute("usages", usages);
        return "/goods-manager";
    }

    @ResponseBody
    @RequestMapping(value = "/goods", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public List<GoodsVo> finds(GoodsSearchForm searchForm, MultipartFile image) {
        List<Goods> goods =  goodsService.finds(searchForm);
        return goodsVoBuilder.buildList(goods);
    }

    @RequestMapping(value = "/goods/add", method = RequestMethod.GET)
    public String addHome(Model model) {
        List<Info> brands = dictInfoService.findByTypeId(ConstantDefine.DICT_BRAND_ID);
        List<Info> stores = dictInfoService.findByTypeId(ConstantDefine.DICT_STORE_ID);
        List<Info> categories = dictInfoService.findByTypeId(ConstantDefine.DICT_CATEGORY_ID);
        List<Info> usages = dictInfoService.findByTypeId(ConstantDefine.DICT_USAGE_ID);
        model.addAttribute("brands", brands);
        model.addAttribute("stores", stores);
        model.addAttribute("categories", categories);
        model.addAttribute("usages", usages);
        return "/goods-add";
    }

    @ResponseBody
    @RequestMapping(value = "/goods/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ApiResponse<Goods> add(@Valid Goods goods, BindingResult result) {
        if (result.hasErrors()) {
            String message =  result.getAllErrors().stream().map(
                    ObjectError:: getDefaultMessage
            ).collect(Collectors.joining(","));
            return error(400, message);
        }
        Goods goodsDb = goodsService.insert(goods);
        return success(ImmutableList.of(goodsDb), 1);
    }

    @ResponseBody
    @RequestMapping(value = "/goods/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public int delete(@RequestParam("id")int id) {
        return goodsService.deleteById(id);
    }

}
