package com.jt.order.mapper;

import java.util.Date;

import com.jt.order.pojo.Order;

public interface OrderMapper {

	public void orderCreated(Order order);
	public Order queryByOrderId(String orderId);
	public void paymentOrderScan(Date date);

}
