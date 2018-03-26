package com.ycit.controller;

import com.ycit.bean.base.ApiResponse;
import com.ycit.bean.modal.GoodsAlbum;
import com.ycit.service.GoodsAlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品相册控制层
 *
 * @author xlch
 * @Date 2018-03-26 10:01
 */
@Controller
@RequestMapping("/back")
public class GoodsAlbumController extends BaseController<GoodsAlbum> {

    private GoodsAlbumService goodsAlbumService;

    @Resource
    public void setGoodsAlbumService(GoodsAlbumService goodsAlbumService) {
        this.goodsAlbumService = goodsAlbumService;
    }

    @ResponseBody
    @RequestMapping(value = "/goods/album", method = RequestMethod.POST)
    public ApiResponse<GoodsAlbum> saveImg(@RequestParam("goodsId")int goodsId, @RequestParam("images")MultipartFile[] images) {
        List<GoodsAlbum> goodsAlbums =  goodsAlbumService.upload(goodsId, images);
        return success(goodsAlbums, goodsAlbums.size());
    }

}
