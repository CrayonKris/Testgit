package cn.tedu.entity;

import java.util.Map;

public class OrderInfo {
	//保存orders表中一条订单信息
	private Order order;
	//保存orderItem表中多条信息
	private Map<Product,Integer> map;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Map<Product, Integer> getMap() {
		return map;
	}
	public void setMap(Map<Product, Integer> map) {
		this.map = map;
	}

}
