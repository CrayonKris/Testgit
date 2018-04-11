package com.jt.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.order.pojo.Order;
import com.jt.order.service.OrderService;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	private static final ObjectMapper MAPPER =new ObjectMapper();
	//新增订单 http://order.jt.com/order/create
	@RequestMapping("create")
	@ResponseBody
	public String orderCreate(@RequestBody String json) throws Exception{
		Order order = MAPPER.readValue(json, Order.class);
		String orderId=orderService.saveOrder(order);
		return orderId;
	}
	//根据订单ID查询订单
	@RequestMapping("query/{orderId}")
	@ResponseBody
	public Order quertByOrderId(@PathVariable String orderId){
		Order order = orderService.queryByOrderId(orderId);
		return order;
	}
}
