package com.ycit.controller;

import com.ycit.bean.base.ApiResponse;
import com.ycit.bean.modal.dict.Info;
import com.ycit.service.DictInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典表信息控制层
 *
 * @author xlch
 * @Date 2018-03-29 9:33
 */
@Controller
@RequestMapping("/back")
public class DictInfoController extends BaseController<Info> {

    private DictInfoService dictInfoService;

    @Resource
    public void setDictInfoService(DictInfoService dictInfoService) {
        this.dictInfoService = dictInfoService;
    }

    @RequestMapping(value = "/brands", method = RequestMethod.POST)
    public ApiResponse<Info> findBrand() {
        List<Info> infos = dictInfoService.findBrands();
        return success(infos, infos.size());
    }

}
