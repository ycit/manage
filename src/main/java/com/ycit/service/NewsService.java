package com.ycit.service;

import com.ycit.YcitException;
import com.ycit.bean.modal.News;
import com.ycit.mapper.NewsMapper;
import com.ycit.util.ConstantDefine;
import com.ycit.util.ImgUtil;
import com.ycit.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 资讯 service
 *
 * @author xlch
 * @Date 2018-03-28 17:25
 */
@Service
public class NewsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsService.class);

    private NewsMapper newsMapper;

    @Resource
    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    public News insert(News news) {
        newsMapper.insert(news);
        return news;
    }

    public int updateImgById(int id, String img) {
        return newsMapper.updateImgById(id, img);
    }

    public int deleteById(int id) {
        return newsMapper.deleteById(id);
    }

    public News updateById(News news) {
        newsMapper.updateById(news);
        return newsMapper.findById(news.getId());
    }

    public List<News> findTopN(int n) {
        return newsMapper.findTopN(n);
    }

    public News findById(int id) {
        return newsMapper.findById(id);
    }

    /**
     * 上传图片同时更新表的 img
     * @param id
     * @param file
     * @return
     */
    @Transactional
    public News uploadImg(int id, MultipartFile file) {
        String dir = ConstantDefine.NEWS_IMG_PATH;
        String imgPath = ConstantDefine.GOODS_IMG_IMG_ABSOLUTE_PATH;
        String originalPath = ConstantDefine.GOODS_IMG_ORIGINAL_ABSOLUTE_PATH;
        String uuid = UUIDGenerator.getUUID();
        String originalName = file.getOriginalFilename();
        if (originalName.lastIndexOf('.') == -1) {
            throw new YcitException("上传文件不合法");
        }
        String newName = uuid + originalName.substring(originalName.lastIndexOf('.'), originalName.length());
        String originalFullPath = dir + originalPath + newName;
        String imgFullPath = dir + imgPath + newName;
        ImgUtil.createDir(originalFullPath);
        try {
            file.transferTo(new File(originalFullPath));
            ImgUtil.compress(originalFullPath, imgFullPath, ConstantDefine.NEWS_IMG_HEIGHT, ConstantDefine.NEWS_IMG_WIDTH, true);
        } catch (IOException e) {
            LOGGER.debug("EXCEPTION", e);
        }
        String imgSavePath =  File.separator + imgFullPath.substring(imgFullPath.indexOf("mall"), imgFullPath.length());
        updateImgById(id, imgSavePath);
        return this.findById(id);
    }

}
