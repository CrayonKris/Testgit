package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

@Service
public class ItemService extends BaseService<Item> {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Autowired
	private RedisService redisService;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private Logger log = Logger.getLogger(ItemService.class);
	public List<Item> queryItemList(){
		return itemMapper.queryItemList();
	}
	//新增商品和商品详情
	public void saveItem(Item item,String desc) {
		item.setStatus(1);//1.表示上架  2表示下架  3 表示删除
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insertSelective(item);
		
		//新增商品详情
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());//详情的ID能否使用item的getid方法
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.insert(itemDesc);//插入的方法不能放service两个方法中 需要事务的维护
		String ITEM_KEY="ITEM_"+item.getId();
		String jsonData=null;
		try {
			jsonData = MAPPER.writeValueAsString(item);
			redisService.set(ITEM_KEY, jsonData,60*60*24*10);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		//引入rabbitmq进行消息的传递，传递的消息是关键字itemId
		//这里直接使用spring加载的模板实例中的一个
		String routingKey="item.add";
		rabbitTemplate.convertAndSend(routingKey, item.getId());
	}
	public void updateItem(Item item,String desc){
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		//商品详情
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateByPrimaryKey(itemDesc);//保持一个方法中
		String ITEM_KEY="ITEM_"+item.getId();
		redisService.del(ITEM_KEY);
	}
	public void deleteItems(Long[] ids){
		itemDescMapper.deleteByIDS(ids);//习惯上先删除子表
		itemMapper.deleteByIDS(ids);
	}
	public ItemDesc getItemDesc(Long itemId) {
		//主外键一致
		ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		return itemDesc;
	}
}
