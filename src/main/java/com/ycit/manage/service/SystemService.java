package com.ycit.manage.service;

import com.ycit.manage.bean.modal.Config;
import com.ycit.manage.mapper.SystemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统参数
 * <p>
 * Created by xlch at 2018/5/7
 */
@Service
public class SystemService {

    private SystemMapper systemMapper;

    @Resource
    public void setSystemMapper(SystemMapper systemMapper) {
        this.systemMapper = systemMapper;
    }

    public List<Config> finds() {
        return systemMapper.finds();
    }

    public int updateBatch(List<Config> configs) {
        return systemMapper.updateBatch(configs);
    }

    public String findByName(String name) {
        return systemMapper.findByName(name);
    }

}
