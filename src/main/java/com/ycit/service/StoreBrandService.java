package com.ycit.service;

import com.ycit.bean.modal.StoreBrand;
import com.ycit.mapper.StoreBrandMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专卖店经营品牌
 *
 * @author xlch
 * @Date 2018-03-29 9:15
 */
@Service
public class StoreBrandService {

    private StoreBrandMapper storeBrandMapper;

    @Resource
    public void setStoreBrandMapper(StoreBrandMapper storeBrandMapper) {
        this.storeBrandMapper = storeBrandMapper;
    }

    public List<StoreBrand> insertBatch(List<StoreBrand> storeBrands) {
        storeBrandMapper.insertBatch(storeBrands);
        return storeBrands;
    }

    public int deleteByStoreId(int storeId) {
        return storeBrandMapper.deleteByStoreId(storeId);
    }

    public List<StoreBrand> findByStoreId(int storeId) {
        return storeBrandMapper.findByStoreId(storeId);
    }

}
