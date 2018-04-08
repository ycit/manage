package com.ycit.service;

import com.ycit.bean.modal.OrderGoods;
import com.ycit.mapper.OrderGoodsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单对应商品 service
 *
 * @author xlch
 * @Date 2018-03-29 17:57
 */
@Service
public class OrderGoodsService {

    private OrderGoodsMapper orderGoodsMapper;

    @Resource
    public void setOrderGoodsMapper(OrderGoodsMapper orderGoodsMapper) {
        this.orderGoodsMapper = orderGoodsMapper;
    }

    public List<OrderGoods> findByOrderId(int orderId) {
        return orderGoodsMapper.findByOrderId(orderId);
    }

    public int deleteByOrderId(int orderId) {
        return orderGoodsMapper.deleteByOrderId(orderId);
    }
}
