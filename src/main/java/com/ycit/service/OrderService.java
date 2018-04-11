package com.ycit.service;

import com.ycit.bean.criteria.OrderSearchForm;
import com.ycit.bean.modal.Order;
import com.ycit.mapper.OrderMapper;
import com.ycit.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
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

    private OrderGoodsService orderGoodsService;

    @Resource
    public void setOrderGoodsService(OrderGoodsService orderGoodsService) {
        this.orderGoodsService = orderGoodsService;
    }

    @Resource
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public List<Order> finds(OrderSearchForm searchForm) {
        if (searchForm.getStartTime() != null) {
            LocalDateTime localDateTime = DateUtil.longToLocalDateTime(searchForm.getStartTime());
            LocalDateTime begin = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
            searchForm.setStart(Date.from(begin.atZone(ZoneId.systemDefault()).toInstant()));
        }
        if (searchForm.getEndTime() != null) {
            LocalDateTime localDateTime = DateUtil.longToLocalDateTime(searchForm.getEndTime());
            LocalDateTime end = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
            searchForm.setEnd(Date.from(end.atZone(ZoneId.systemDefault()).toInstant()));
        }
        return orderMapper.finds(searchForm);
    }

    public int updateStatusById(int id, int status) {
        return  orderMapper.updateStatusById(id, status);
    }

    public int deleteById(int id) {
        return orderMapper.deleteById(id);
    }


    @Transactional
    public int doDeleteOrder(int id) {
        int num = this.deleteById(id);
        orderGoodsService.deleteByOrderId(id);
        return num;
    }

}
