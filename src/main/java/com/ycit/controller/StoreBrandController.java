package com.ycit.controller;

import com.ycit.bean.base.ApiResponse;
import com.ycit.bean.modal.StoreBrand;
import com.ycit.service.StoreBrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专卖店经营品牌 控制层
 *
 * @author xlch
 * @Date 2018-03-29 9:18
 */
@Controller
@RequestMapping("/back")
public class StoreBrandController extends BaseController<StoreBrand> {

    private StoreBrandService storeBrandService;

    @Resource
    public void setStoreBrandService(StoreBrandService storeBrandService) {
        this.storeBrandService = storeBrandService;
    }

    @ResponseBody
    @RequestMapping(value = "/stores/{storeId}/brands", method = RequestMethod.POST)
    public ApiResponse<StoreBrand> find(@PathVariable("storeId")int storeId) {
        List<StoreBrand> storeBrands = storeBrandService.findByStoreId(storeId);
        return success(storeBrands, storeBrands.size());
    }
}
