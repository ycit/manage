package com.ycit.service;

import com.ycit.YcitException;
import com.ycit.bean.modal.Store;
import com.ycit.mapper.StoreMapper;
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
import java.util.List;

/**
 * 专卖店 service
 *
 * @author xlch
 * @Date 2018-03-23 9:57
 */
@Service
public class StoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);

    private StoreMapper storeMapper;

    @Resource
    public void setStoreMapper(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    public List<Store> findAll() {
        return storeMapper.findAll();
    }

    public Store insert(Store store) {
        storeMapper.insert(store);
        return store;
    }

    public int deleteById(int id) {
        return storeMapper.deleteById(id);
    }

    public Store findById(int id) {
        return storeMapper.findById(id);
    }

    public int updateById(Store store) {
        return storeMapper.updateById(store);
    }

    public Store uploadImg(int storeId, MultipartFile file) {
        String dir = ConstantDefine.STORE_IMG_PATH;
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
            ImgUtil.compress(originalFullPath, imgFullPath, ConstantDefine.STORE_IMG_HEIGHT, ConstantDefine.STORE_IMG_WIDTH, true);
        } catch (IOException e) {
            LOGGER.debug("context", e);
        }
        String imgSavePath =  File.separator + imgFullPath.substring(imgFullPath.indexOf("mall"), imgFullPath.length());
        Store store = new Store(imgSavePath);
        if (storeId != 0) {
            storeMapper.updateImg(storeId, imgSavePath);
        } else {
            storeMapper.insertImg(store);
        }
        return store;
    }

}
