package com.ycit.bean.vo;

import com.ycit.bean.modal.Order;
import com.ycit.bean.modal.OrderGoods;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 订单显示数据
 *
 * @author xlch
 * @Date 2018-03-29 17:50
 */
public class OrderVo extends Order{

    private List<OrderGoods> goodsList;

    public static OrderVo fromBean(Order order) {
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order, orderVo);
        return orderVo;
    }

    public List<OrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoods> goodsList) {
        this.goodsList = goodsList;
    }
}
