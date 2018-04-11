package com.jt.web.serivce;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.HttpClientService;
import com.jt.common.service.RedisService;
@Service
public class RabbitItemService {
	@Autowired
	private HttpClientService httpClient;
	@Autowired
	private RedisService redisService;
	public void updateItem(Long itemId){
		//在rabbitmq的配置文件中，配置类名，方法名，消息
		//封装到参数
		//准备redies的缓存内容，需要按照商品id查找商品详情
		try {
			String url="http://manage.jt.com/items/"+itemId;
			
			String jsonData = httpClient.doGet(url, "utf-8");
			String key="ITEM_"+itemId;
			redisService.set(key,jsonData,60*60*24);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
