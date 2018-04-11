package com.jt.order.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.order.mapper.OrderMapper;
import com.jt.order.pojo.Order;
@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;
	public String saveOrder(Order order){
		//前台只负责传递订单的部分学段内容
		//需要业务层封装一部分，orderId userId+currentTime
		String orderId=order.getUserId()+""+System.currentTimeMillis();
		order.setOrderId(orderId);
		order.setCreated(new Date());
		order.setUpdated(order.getCreated());
		orderMapper.orderCreated(order);
		return orderId;
	}
	//根据订单ID查询订单
		public Order queryByOrderId(String orderId){
			Order order = orderMapper.queryByOrderId(orderId);
			return order;
		}
}
