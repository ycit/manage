package com.ycit.service.builder;

import com.ycit.bean.modal.Order;
import com.ycit.bean.modal.OrderGoods;
import com.ycit.bean.vo.OrderVo;
import com.ycit.service.OrderGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单构建类
 *
 * @author xlch
 * @Date 2018-03-29 17:50
 */
@Service
public class OrderVoBuilder {

    private OrderGoodsService orderGoodsService;

    @Resource
    public void setOrderGoodsService(OrderGoodsService orderGoodsService) {
        this.orderGoodsService = orderGoodsService;
    }

    public OrderVo build(Order order) {
        OrderVo orderVo = OrderVo.fromBean(order);
        List<OrderGoods> orderGoods = orderGoodsService.findByOrderId(order.getId());
        orderVo.setGoods(orderGoods);
        return orderVo;
    }

    public List<OrderVo> builds(List<Order> orders) {
        List<OrderVo> orderVos = new ArrayList<>();
        orders.forEach(order -> {
            OrderVo orderVo = this.build(order);
            orderVos.add(orderVo);
        });
        return orderVos;
    }

}
