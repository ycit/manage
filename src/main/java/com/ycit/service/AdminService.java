package com.ycit.service;

import com.ycit.bean.modal.Admin;
import com.ycit.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员 service 层
 *
 * @author xlch
 * @Date 2018-03-21 13:41
 */
@Service
public class AdminService {

    private AdminMapper adminMapper;

    @Resource
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public Admin findByUsername(String username) {
        return adminMapper.findByUsername(username);
    }


}
