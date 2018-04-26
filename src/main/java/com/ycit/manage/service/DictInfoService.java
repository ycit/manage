package com.ycit.manage.service;

import com.ycit.manage.bean.modal.dict.Info;
import com.ycit.manage.mapper.DictInfoMapper;
import com.ycit.manage.util.ConstantDefine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典数据
 *
 * @author xlch
 * @Date 2018-03-24 16:13
 */
@Service
public class DictInfoService {

    private DictInfoMapper dictInfoMapper;

    @Resource
    public void setDictInfoMapper(DictInfoMapper dictInfoMapper) {
        this.dictInfoMapper = dictInfoMapper;
    }

    public List<Info> findByTypeId(int typeId) {
        return dictInfoMapper.findByTypeId(typeId);
    }

    public int deleteByTypeId(int typeId) {
        return dictInfoMapper.deleteByTypeId(typeId);
    }

    public int insertBatch(List<Info> infos) {
        return dictInfoMapper.insertBatch(infos);
    }

    @Transactional
    public int editDict(List<Info> infos, int typeId) {
        deleteByTypeId(typeId);
        return insertBatch(infos);
    }

    public Info findById(int id) {
        return dictInfoMapper.findById(id);
    }

    public List<Info> findBrands() {
        return this.findByTypeId(ConstantDefine.DICT_BRAND_ID);
    }

    public List<Info> findCategories() {
        return this.findByTypeId(ConstantDefine.DICT_CATEGORY_ID);
    }

    public List<Info> findUsages() {
        return this.findByTypeId(ConstantDefine.DICT_USAGE_ID);
    }

}
