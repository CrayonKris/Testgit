package com.jt.web.serivce;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.User;

@Service
public class CartService {
	@Autowired
	private HttpClientService httpClientService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	public List<Cart> show(Long userId) throws Exception{
		String url="http://cart.jt.com/cart/query/"+userId;
		String jsonData = httpClientService.doGet(url, "utf-8");
		JsonNode jsonNode = MAPPER.readTree(jsonData);
		JsonNode data = jsonNode.get("data");
		Object obj = null;
		if (data.isArray()&&data.size()>0) {
			obj=MAPPER.readValue(data.traverse(),
					MAPPER.getTypeFactory().
					constructCollectionType(List.class, Cart.class));
		}
		return (List<Cart>)obj;
		
	}
	public void saveCart(Long userId, Long itemId, Integer num) throws Exception {
		//调用后台获取三个冗余字段
		String url = "http://manage.jt.com/items/"+itemId;
		String jsonItem = httpClientService.doGet(url, "utf-8");
		Item item=MAPPER.readValue(jsonItem, Item.class);
		//2.调用购物车接口，传递数据
		url="http://cart.jt.com/cart/save";
		Map<String, String> param=new HashMap<String, String>();
		param.put("userId", userId+"");
		param.put("itemId", itemId+"");
		param.put("num", num+"");
		//从后台获取的数据封装
		param.put("itemTitle", item.getTitle());
		param.put("itemImage", item.getImages()[0]);
		param.put("itemPrice", item.getPrice()+"");
		httpClientService.doPost(url,param,"utf-8");
	}
	public void updateNum(Long userId,Long itemId, Integer num) throws Exception {
		String url="http://cart.jt.com/cart/update/num/"+userId+"/"+itemId+"/"+num;
		httpClientService.doGet(url, "utf-8");
	}
	public void delete(Long userId,Long itemId) throws Exception {
		String url="http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
		httpClientService.doGet(url, "utf-8");
	}
}
