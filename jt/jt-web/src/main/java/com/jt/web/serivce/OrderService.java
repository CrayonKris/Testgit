package com.jt.web.serivce;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;
@Service
public class OrderService {
	@Autowired
	private HttpClientService httpClient;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	public List<Cart> queryCartList(Long userId) throws Exception {
		String url="http://cart.jt.com/cart/query/"+userId;
		String jsonData=httpClient.doGet(url, "utf-8");
		JsonNode jsonNode = MAPPER.readTree(jsonData);
		JsonNode data=jsonNode.get("data");
		Object obj=null;
		if (data.isArray()&&data.size()>0) {
			obj=MAPPER.readValue(data.traverse(), 
					MAPPER.getTypeFactory().
					constructCollectionType(List.class, Cart.class));
		}
		return (List<Cart>)obj;
	}
	//创建订单
	public String saveOrder(Order order) throws Exception {
		String url="http://order.jt.com/order/create";
		String json=MAPPER.writeValueAsString(order);
		String orderId=httpClient.doPostJson(url, json);
		return orderId;
	}
	//查询某个订单
	public Order queryByOrderId(String orderId) throws Exception {
		String url="http://order.jt.com/order/query/"+orderId;
		String jsonData=httpClient.doGet(url, "utf-8");
		Order order=MAPPER.readValue(jsonData, Order.class);
		return order;
	}

}
