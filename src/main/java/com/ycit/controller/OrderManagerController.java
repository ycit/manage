package com.ycit.controller;

import com.ycit.bean.base.ApiResponse;
import com.ycit.bean.criteria.OrderSearchForm;
import com.ycit.bean.modal.Order;
import com.ycit.bean.vo.OrderVo;
import com.ycit.service.OrderService;
import com.ycit.service.UserService;
import com.ycit.service.builder.OrderVoBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 订单管理 控制层
 *
 * @author xlch
 * @Date 2018-03-22 11:39
 */
@Controller
@RequestMapping("/back")
public class OrderManagerController extends BaseController<OrderVo> {

    private OrderService orderService;

    private UserService userService;

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    private OrderVoBuilder orderVoBuilder;

    @Resource
    public void setOrderVoBuilder(OrderVoBuilder orderVoBuilder) {
        this.orderVoBuilder = orderVoBuilder;
    }

    /**
     * 订单管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("users", userService.finds());
        return "/order-manager";
    }

    /**
     * 查询订单
     * @param searchForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ApiResponse<OrderVo> finds(@Valid OrderSearchForm searchForm) {
        List<Order> orders = orderService.finds(searchForm);
        List<OrderVo> orderVos = orderVoBuilder.builds(orders);
        return success(orderVos, orders.size());
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/orders/delete", method = RequestMethod.GET)
    public int delete(@RequestParam("id")int id) {
        return orderService.doDeleteOrder(id);
    }

    /**
     * 发货
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/orders/send", method = RequestMethod.GET)
    public int sendGoods(@RequestParam("id")int id) {
        return orderService.updateStatusById(id, 1);
    }

}
