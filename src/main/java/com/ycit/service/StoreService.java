package com.ycit.service;

import com.ycit.bean.modal.dict.Info;
import com.ycit.mapper.StoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专卖店 service
 *
 * @author xlch
 * @Date 2018-03-23 9:57
 */
@Service
public class StoreService {

    private StoreMapper storeMapper;

    @Resource
    public void setStoreMapper(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    public List<Info> findAll() {
        return storeMapper.findAll();
    }

}
