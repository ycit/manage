package com.ycit.service;

import com.ycit.YcitException;
import com.ycit.bean.modal.GoodsAlbum;
import com.ycit.mapper.GoodsAlbumMapper;
import com.ycit.util.ConstantDefine;
import com.ycit.util.ImgUtil;
import com.ycit.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品相册 service
 *
 * @author xlch
 * @Date 2018-03-26 9:57
 */
@Service
public class GoodsAlbumService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsAlbumService.class);

    private GoodsAlbumMapper goodsAlbumMapper;

    private GoodsService goodsService;

    @Resource
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Resource
    public void setGoodsAlbumMapper(GoodsAlbumMapper goodsAlbumMapper) {
        this.goodsAlbumMapper = goodsAlbumMapper;
    }

    public GoodsAlbum insert(GoodsAlbum goodsAlbum) {
        goodsAlbumMapper.insert(goodsAlbum);
        return goodsAlbum;
    }

    public int deleteByGoodsId(int goodsId) {
        return goodsAlbumMapper.deleteByGoodsId(goodsId);
    }

    public List<GoodsAlbum> findByGoodsId(int goodsId) {
        return goodsAlbumMapper.findByGoodsId(goodsId);
    }

    public GoodsAlbum findById(int id) {
        return goodsAlbumMapper.findById(id);
    }

    public List<GoodsAlbum> upload(int goodsId, MultipartFile[] files) {
        String uploadPath = ConstantDefine.GOODS_IMG_PATH;
        String imgDir = ConstantDefine.GOODS_IMG_IMG_PATH;
        String thumbnailDir = ConstantDefine.GOODS_IMG_THUMBNAIL_PATH;
        String originalDir = ConstantDefine.GOODS_IMG_ORIGINAL_PATH;
        List<GoodsAlbum> goodsAlbums = new ArrayList<>();
        if (files.length > 0) {
            String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            for (int size = 0, length = files.length; size < length; size ++) {
                MultipartFile file = files[size];
                String originalName = file.getOriginalFilename();
                String uuid = UUIDGenerator.getUUID();
                if (originalName.lastIndexOf('.') == -1) {
                    throw new YcitException("上传文件不合法");
                }
                String newName = uuid + originalName.substring(originalName.lastIndexOf('.'), originalName.length());
                String imgPath = uploadPath + imgDir + now;
                String imgFullPath = uploadPath + imgDir + now + File.separator + newName;
                String thumbnailPath = uploadPath + thumbnailDir + now;
                String thumbnailFullPath = uploadPath + thumbnailDir + now + File.separator + newName;
                String originalPath = uploadPath + originalDir + now;
                String originalFullPath = uploadPath + originalDir + now + File.separator + newName;
                ImgUtil.createDir(imgPath);
                ImgUtil.createDir(originalPath);
                ImgUtil.createDir(thumbnailPath);
                try {
                    File originalSource = new File(originalFullPath);
                    file.transferTo(originalSource);
                    ImgUtil.compress(originalFullPath, imgFullPath, ConstantDefine.IMG_HEIGHT, ConstantDefine.IMG_WIDTH, true);
                    ImgUtil.compress(originalFullPath, thumbnailFullPath, ConstantDefine.THUMBNAIL_HEIGHT, ConstantDefine.THUMBNAIL_WIDTH, true);
                } catch (IOException e) {
                    LOGGER.debug("context", e);
                }
                GoodsAlbum goodsAlbum = new GoodsAlbum(goodsId, imgFullPath, thumbnailFullPath);
                insert(goodsAlbum);
                goodsAlbums.add(goodsAlbum);
                if (size == 0) {
                    goodsService.updateById(goodsId, imgFullPath, thumbnailFullPath);
                }
            }
        }
        return goodsAlbums;
    }

}
