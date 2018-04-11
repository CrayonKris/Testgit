package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;
import com.jt.web.serivce.OrderService;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	//http://order.jt.com/order/create.hmtl
	@RequestMapping("create")
	public String orderCreate(Model model) throws Exception{
		//需要userid
		Long userId=549L;
		List<Cart> cartList=orderService.queryCartList(userId);
		model.addAttribute("carts", cartList);
		return "order-cart";
	}
	//订单提交/service/order/submit
	@RequestMapping("submit")
	@ResponseBody 
	public SysResult orderSubmit(Order order) throws Exception{
		Long userId=549L;
		order.setUserId(userId);
		String orderId=orderService.saveOrder(order);
		return SysResult.oK(orderId);
	}
	//转向成功页面  /order/success.html?id="+result.data//orderId;
	@RequestMapping("success")
	public String success(@RequestParam("id")String orderId,Model model) throws Exception{
		Order order=orderService.queryByOrderId(orderId);
		model.addAttribute("order", order);
		return "success";
	}
}
