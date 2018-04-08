package com.ycit.service;

import com.ycit.bean.criteria.DictForm;
import com.ycit.bean.modal.dict.Type;
import com.ycit.mapper.DictTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典管理 service
 *
 * @author xlch
 * @Date 2018-03-23 10:34
 */
@Service
public class DictTypeService {

    private DictTypeMapper dictTypeMapper;

    private DictInfoService dictInfoService;

    @Resource
    public void setDictInfoService(DictInfoService dictInfoService) {
        this.dictInfoService = dictInfoService;
    }

    @Resource
    public void setDictTypeMapper(DictTypeMapper dictTypeMapper) {
        this.dictTypeMapper = dictTypeMapper;
    }

    public List<Type> findAll() {
        return dictTypeMapper.findAll();
    }

    public Type insert(DictForm dictForm) {
        Type type = Type.fromBean(dictForm);
        dictTypeMapper.insert(type);
        return type;
    }

    public int deleteById(int id) {
        return dictTypeMapper.deleteById(id);
    }

    public Type findById(int id) {
        return dictTypeMapper.findById(id);
    }

    public int updateById(DictForm dictForm, int id) {
        Type type = Type.fromBean(dictForm);
        type.setId(id);
        return dictTypeMapper.updateById(type);
    }

    @Transactional
    public int deleteType(int id) {
        deleteById(id);
        return dictInfoService.deleteByTypeId(id);
    }

}
