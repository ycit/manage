package com.ycit.service;

import com.ycit.bean.criteria.OrderSearchForm;
import com.ycit.bean.modal.Order;
import com.ycit.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单 service
 *
 * @author xlch
 * @Date 2018-03-29 17:21
 */
@Service
public class OrderService {

    private OrderMapper orderMapper;

    @Resource
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public List<Order> finds(OrderSearchForm searchForm) {
        return orderMapper.finds(searchForm);
    }

    public int updateStatusById(int id, int status) {
        return  orderMapper.updateStatusById(id, status);
    }

    public int deleteById(int id) {
        return orderMapper.deleteById(id);
    }

}
