package com.ycit.controller;

import com.google.common.collect.ImmutableList;
import com.ycit.bean.base.ApiResponse;
import com.ycit.bean.modal.Store;
import com.ycit.service.StoreService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 专卖店理 控制层
 *
 * @author xlch
 * @Date 2018-03-22 11:40
 */
@Controller
@RequestMapping("/back")
public class StoreManagerController extends BaseController<Store> {

    private StoreService storeService;

    @Resource
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * 专卖店主页
     * @param model
     * @return
     */
    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public String home(Model model) {
        return "/store-manager";
    }

    /**
     * 请求专卖店数据集合
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stores", method = RequestMethod.POST)
    public ApiResponse<Store> find() {
        List<Store> storeList = storeService.findAll();
        return success(storeList, storeList.size());
    }

    /**
     * 新增专卖店页面
     * @return
     */
    @RequestMapping(value = "/stores/add", method = RequestMethod.GET)
    public String addHome() {
        return "/store-add";
    }

    /**
     * 新增专卖店页面
     * @param store
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stores/add", method = RequestMethod.POST)
    public ApiResponse<Store> add(@Valid Store store, BindingResult result) {
        if (result.hasErrors()) {
            String message =  result.getAllErrors().stream().map(
                    ObjectError:: getDefaultMessage
            ).collect(Collectors.joining(","));
            return error(400, message);
        }
        storeService.updateById(store);
        return success(new ArrayList<>(), 0);
    }

    /**
     * 编辑专卖店页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/stores/edit", method = RequestMethod.GET)
    public String editHome(@RequestParam("id")int id, Model model) {
        model.addAttribute("store", storeService.findById(id));
        return "/store-edit";
    }

    /**
     * 编辑专卖店请求
     * @param store
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stores/edit", method = RequestMethod.POST)
    public ApiResponse<Store> edit(@Valid Store store, BindingResult result) {
        if (result.hasErrors()) {
            String message =  result.getAllErrors().stream().map(
                    ObjectError:: getDefaultMessage
            ).collect(Collectors.joining(","));
            return error(400, message);
        }
        storeService.updateById(store);
        Store storeDb = storeService.findById(store.getId());
        return success(storeDb == null ? new ArrayList<>() : ImmutableList.of(storeDb), 1);
    }

    /**
     * 图片上传
     * @param img
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stores/img/upload", method = RequestMethod.POST)
    public ApiResponse<Store> uploadImg(@RequestParam(value = "storeId", required = false, defaultValue = "0")int storeId, MultipartFile img) {
        Store store = storeService.uploadImg(storeId, img);
        return success(store == null ? new ArrayList<>() : ImmutableList.of(store), 1);
    }

    @ResponseBody
    @RequestMapping(value = "/stores/delete", method = RequestMethod.POST)
    public int delete(@RequestParam("id") int id) {
        return storeService.deleteById(id);
    }


}
