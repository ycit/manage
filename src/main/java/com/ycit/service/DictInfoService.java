package com.ycit.service;

import com.ycit.bean.modal.dict.Info;
import com.ycit.mapper.DictInfoMapper;
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

}
