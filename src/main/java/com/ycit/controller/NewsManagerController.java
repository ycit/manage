package com.ycit.controller;

import com.google.common.collect.ImmutableList;
import com.ycit.bean.base.ApiResponse;
import com.ycit.bean.modal.News;
import com.ycit.service.NewsService;
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
 * 资讯管理控制层
 *
 * @author xlch
 * @Date 2018-03-22 11:42
 */
@Controller
@RequestMapping("/back")
public class NewsManagerController extends BaseController<News> {

    private NewsService newsService;

    @Resource
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "/newses", method = RequestMethod.GET)
    public String home(Model model) {
        return "/news-manager";
    }

    @ResponseBody
    @RequestMapping(value = "/newses", method = RequestMethod.POST)
    public ApiResponse<News> find(@RequestParam(value = "n", required = false, defaultValue = "5")int n) {
        List<News> newsList = newsService.findTopN(n);
        return success(newsList, newsList.size());
    }

    @RequestMapping(value = "/newses/add", method = RequestMethod.GET)
    public String addHome(Model model) {
        return "/news-add";
    }

    @ResponseBody
    @RequestMapping(value = "/newses/add", method = RequestMethod.POST)
    public ApiResponse<News> add(@Valid News news, BindingResult result) {
        if (result.hasErrors()) {
            String message =  result.getAllErrors().stream().map(
                    ObjectError:: getDefaultMessage
            ).collect(Collectors.joining(","));
            return error(400, message);
        }
        News newsDb = newsService.insert(news);
        return success(newsDb == null ? new ArrayList<>() : ImmutableList.of(newsDb), 1);
    }

    @RequestMapping(value = "/newses/edit", method = RequestMethod.GET)
    public String editHome(@RequestParam("id")int id, Model model) {
        model.addAttribute("news", newsService.findById(id));
        return "/news-edit";
    }

    @ResponseBody
    @RequestMapping(value = "/newses/edit", method = RequestMethod.POST)
    public ApiResponse<News> edit(@Valid News news, BindingResult result) {
        if (result.hasErrors()) {
            String message =  result.getAllErrors().stream().map(
                    ObjectError:: getDefaultMessage
            ).collect(Collectors.joining(","));
            return error(400, message);
        }
        News newsDb = newsService.updateById(news);
        return success(newsDb == null? new ArrayList<>() : ImmutableList.of(newsDb), 1);
    }

    @ResponseBody
    @RequestMapping("/newses/img/upload")
    public ApiResponse<News> uploadImg(@RequestParam(value = "id")int id,
                                       @RequestParam MultipartFile image) {
        News news = newsService.uploadImg(id, image);
        return success(news == null? new ArrayList<>(): ImmutableList.of(news), 1);
    }

    @ResponseBody
    @RequestMapping(value = "/newses/delete", method = RequestMethod.POST)
    public int edit(@RequestParam("id")int id) {
        return newsService.deleteById(id);
    }

}
