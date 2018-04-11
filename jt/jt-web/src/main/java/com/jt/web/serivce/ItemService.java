package com.jt.web.serivce;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.service.RedisService;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;

@Service
public class ItemService {
	@Autowired
	private HttpClientService httpService;
	@Autowired
	private RedisService redisService;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public Item getItem(Long itemId) {
		// 模拟发起http请求，需要一个url
		String url = "http://manage.jt.com/items/" + itemId;
		// 引入redies的缓存，如果有数据就直接返回，没有数据直接执行业务
		// key值得计算，和后台需要一致
		String ITEM_KEY = "ITEM_" + itemId;
		// 先获取key和结果json,如果有值直接返回如果没值调用httpClien
		String jsonData = redisService.get(ITEM_KEY);
		try {
			if (StringUtils.isNotEmpty(jsonData)) {
				Item item = MAPPER.readValue(jsonData, Item.class);
				return item;
			} else {
				jsonData = httpService.doGet(url, "utf-8");
				Item item = MAPPER.readValue(jsonData, Item.class);
				// 如果查询到数据将数据保存在redis中
				// 习惯上的一个过期时间，10天， 60*60*24*10
				redisService.set(ITEM_KEY, jsonData, 60 * 60 * 24 * 10);
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ItemDesc getItemDescById(Long itemId) {
		// 模拟发起http请求，需要一个url
		String url = "http://manage.jt.com/item/desc/" + itemId;
		// 引入redies的缓存，如果有数据就直接返回，没有数据直接执行业务
		// key值得计算，和后台需要一致
		String ITEM_DESC_KEY = "ITEM_DESC_" + itemId;
		// 先获取key和结果json,如果有值直接返回如果没值调用httpClien
		String jsonData = redisService.get(ITEM_DESC_KEY);
		try {
			if (StringUtils.isNotEmpty(jsonData)) {
				ItemDesc itemDesc = MAPPER.readValue(jsonData, ItemDesc.class);
				return itemDesc;
			} else {
				jsonData = httpService.doGet(url, "utf-8");
				ItemDesc itemDesc = MAPPER.readValue(jsonData, ItemDesc.class);
				// 如果查询到数据将数据保存在redis中
				// 习惯上的一个过期时间，10天， 60*60*24*10
				redisService.set(ITEM_DESC_KEY, jsonData, 60 * 60 * 24 * 10);
				return itemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
