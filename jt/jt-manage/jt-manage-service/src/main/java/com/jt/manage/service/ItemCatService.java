package com.jt.manage.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;
@Service
public class ItemCatService extends BaseService<ItemCat> {
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private RedisService redisService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	public List<ItemCat> queryItemCatList(Integer parentId){
		//定义key值
		String ITEM_CAT_KEY = "ITEM_CAT"+parentId;
		//读取缓存调用get方法拿到字符串，需要jason格式，jason格式可以转化成Java对象
		String jsonData = redisService.get(ITEM_CAT_KEY);
		//判断是否有值
		List<ItemCat> itemCats = null;
		//判断缓存值是否为空
		if (StringUtils.isNotEmpty(jsonData)) {
			//缓存有数据jason字符串转化成对象
			
			try {
				JsonNode jsonNode = MAPPER.readTree(jsonData);
				itemCats = MAPPER.readValue(jsonNode.traverse(), MAPPER.getTypeFactory().
						constructCollectionType(List.class, ItemCat.class));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}else {
			itemCats=itemCatMapper.queryItemCatList(parentId);
			//保存到缓存中，对象变jason
			String json =null;
			//对象变json,存入缓存
			try {
				json = MAPPER.writeValueAsString(itemCats);
				//以当前生成的key
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return itemCatMapper.queryItemCatList(parentId);
	}
}
