package com.ycit.manage.controller;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.ycit.manage.bean.base.ApiResponse;
import com.ycit.manage.bean.criteria.DictForm;
import com.ycit.manage.bean.modal.dict.Info;
import com.ycit.manage.bean.modal.dict.Type;
import com.ycit.manage.service.DictInfoService;
import com.ycit.manage.service.DictTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典表管理控制层
 *
 * @author xlch
 * @Date 2018-03-22 11:06
 */
@Controller
@RequestMapping("/back")
public class DictTypeController extends BaseController<Type> {

    private DictTypeService dictTypeService;

    private DictInfoService dictInfoService;

    @Resource
    public void setDictInfoService(DictInfoService dictInfoService) {
        this.dictInfoService = dictInfoService;
    }

    @Resource
    public void setDictTypeService(DictTypeService dictTypeService) {
        this.dictTypeService = dictTypeService;
    }

    /**
     * 字典表管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/dict", method = RequestMethod.GET)
    public String home(Model model) {
        return "/dict-manager";
    }

    /**
     * 请求所有字典表数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dict/all", method = RequestMethod.GET)
    public List<Type> findAll() {
        return dictTypeService.findAll();
    }

    /**
     * 新增字典表页面 ，后期关闭该入口
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/dict/add", method = RequestMethod.GET)
    public String addHome(@RequestParam(value = "id", required = false, defaultValue = "0")int id,
                          Model model) {
        if (id != 0) {
            Type type = dictTypeService.findById(id);
            model.addAttribute("type", type);
        }
        return "/dict-add";
    }

    /**
     * 新增字典表请求
     * @param dictForm
     * @param result
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dict/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ApiResponse<Type> doAdd(@Valid DictForm dictForm, BindingResult result,
                                   @RequestParam(value = "id", required = false, defaultValue = "0")int id) {
        if (result.hasErrors()) {
            String message =  result.getAllErrors().stream().map(
                    ObjectError:: getDefaultMessage
            ).collect(Collectors.joining(","));
            return error(400, message);
        }
        Type type = null;
        if (id != 0) {
            type = dictTypeService.findById(id);
            if (type != null) {
                dictTypeService.updateById(dictForm, id);
                type = dictTypeService.findById(id);
            } else {
                type = dictTypeService.insert(dictForm);
            }
        } else {
            type = dictTypeService.insert(dictForm);
        }

        return success(type == null ? new ArrayList<>():ImmutableList.of(type), 1);
    }

    /**
     * 字典表删除请求，后期关闭该接口
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dict/delete", method = RequestMethod.POST)
    public int delete(@RequestParam("id")int id) {
        return dictTypeService.deleteType(id);
    }

    /**
     * 字典表详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/dict/{id}", method = RequestMethod.GET)
    public String dictInto(@PathVariable("id")int id, Model model) {
        List<Info> infos = dictInfoService.findByTypeId(id);
        Type type = dictTypeService.findById(id);
        model.addAttribute("type", type);
        model.addAttribute("infos", infos);
        return "/dict-one";
    }

    /**
     * 修改字典表
     * @param name
     * @param id
     * @return
     */
    @RequestMapping(value = "/dict/one/add", method = RequestMethod.POST)
    public String oneAdd(@RequestParam("name") String name, @RequestParam("id")int id) {
        List<Info> infos = buildInfoList(name, id);
        dictInfoService.editDict(infos, id);
        return "redirect:/back/dict";
    }

    private List<Info> buildInfoList(String names, int id) {
        List<Info> infos = new ArrayList<>();
        List<String> nameList = Splitter.on(",").splitToList(names);
        nameList.forEach(name -> {
            Info info = new Info(id, name);
            infos.add(info);
        });
        return infos;
    }

}
