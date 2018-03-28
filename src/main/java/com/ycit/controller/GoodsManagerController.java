package com.ycit.controller;

import com.google.common.collect.ImmutableList;
import com.ycit.bean.base.ApiResponse;
import com.ycit.bean.entity.GoodsSearchForm;
import com.ycit.bean.modal.Goods;
import com.ycit.bean.modal.GoodsAlbum;
import com.ycit.bean.modal.Store;
import com.ycit.bean.modal.dict.Info;
import com.ycit.bean.vo.GoodsVo;
import com.ycit.service.DictInfoService;
import com.ycit.service.GoodsAlbumService;
import com.ycit.service.GoodsService;
import com.ycit.service.StoreService;
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

    private GoodsAlbumService goodsAlbumService;

    private StoreService storeService;

    @Resource
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @Resource
    public void setGoodsAlbumService(GoodsAlbumService goodsAlbumService) {
        this.goodsAlbumService = goodsAlbumService;
    }

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

    /**
     * 商品管理入口
     * @param model
     * @return
     */
    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    private String home(Model model) {
        List<Info> brands = dictInfoService.findByTypeId(ConstantDefine.DICT_BRAND_ID);
        List<Store> stores = storeService.findAll();
        List<Info> categories = dictInfoService.findByTypeId(ConstantDefine.DICT_CATEGORY_ID);
        List<Info> usages = dictInfoService.findByTypeId(ConstantDefine.DICT_USAGE_ID);
        model.addAttribute("brands", brands);
        model.addAttribute("stores", stores);
        model.addAttribute("categories", categories);
        model.addAttribute("usages", usages);
        return "/goods-manager";
    }

    /**
     * 请求 商品数据
     * @param searchForm
     * @param image
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/goods", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public List<GoodsVo> finds(GoodsSearchForm searchForm, MultipartFile image) {
        List<Goods> goods =  goodsService.finds(searchForm);
        return goodsVoBuilder.buildList(goods);
    }

    /**
     *  新增 商品入口
     * @param model
     * @return
     */
    @RequestMapping(value = "/goods/add", method = RequestMethod.GET)
    public String addHome(Model model) {
        List<Info> brands = dictInfoService.findByTypeId(ConstantDefine.DICT_BRAND_ID);
        List<Store> stores = storeService.findAll();
        List<Info> categories = dictInfoService.findByTypeId(ConstantDefine.DICT_CATEGORY_ID);
        List<Info> usages = dictInfoService.findByTypeId(ConstantDefine.DICT_USAGE_ID);
        model.addAttribute("brands", brands);
        model.addAttribute("stores", stores);
        model.addAttribute("categories", categories);
        model.addAttribute("usages", usages);
        return "/goods-add";
    }

    /**
     *  新增商品请求
     * @param goods
     * @param result
     * @return
     */
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

    /**
     *  删除 商品请求
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/goods/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public int delete(@RequestParam("id")int id) {
        return goodsService.deleteById(id);
    }

    @RequestMapping(value = "/goods/edit", method = RequestMethod.GET)
    public String editHome(@RequestParam("id")int id, Model model) {
        List<Info> brands = dictInfoService.findByTypeId(ConstantDefine.DICT_BRAND_ID);
        List<Store> stores = storeService.findAll();
        List<Info> categories = dictInfoService.findByTypeId(ConstantDefine.DICT_CATEGORY_ID);
        List<Info> usages = dictInfoService.findByTypeId(ConstantDefine.DICT_USAGE_ID);
        model.addAttribute("brands", brands);
        model.addAttribute("stores", stores);
        model.addAttribute("categories", categories);
        model.addAttribute("usages", usages);
        Goods goods = goodsService.findById(id);
        model.addAttribute("goods", goods);
        List<GoodsAlbum> goodsAlbums = goodsAlbumService.findByGoodsId(id);
        model.addAttribute("albums", goodsAlbums);
        return "goods-edit";
    }

    @ResponseBody
    @RequestMapping(value = "/goods/edit", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ApiResponse<Goods> edit(@Valid Goods goods, BindingResult result) {
        if (result.hasErrors()) {
            String message =  result.getAllErrors().stream().map(
                    ObjectError:: getDefaultMessage
            ).collect(Collectors.joining(","));
            return error(400, message);
        }
        goodsService.updateBeanById(goods);
        Goods goodsDb = goodsService.findById(goods.getId());
        return success(ImmutableList.of(goodsDb), 1);
    }

}
