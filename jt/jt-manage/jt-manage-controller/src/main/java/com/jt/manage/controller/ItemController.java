package com.jt.manage.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;
@Controller
@RequestMapping("item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	private Logger log = Logger.getLogger(ItemController.class);
	@RequestMapping("query")
	@ResponseBody
	public EasyUIResult queryItemList(Integer page,Integer rows){
		PageHelper.startPage(page, rows);
		//只开启当前startPage方法下的第一条查询语句的拦截
		List<Item> items = itemService.queryItemList();
		PageInfo<Item> pageInfo = new PageInfo<Item>(items);
		return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
	}
	@RequestMapping("/save")
	@ResponseBody
	public SysResult insertItem(Item item,String desc){
		try {
			itemService.saveItem(item,desc);
			return SysResult.oK();
		} catch (Exception e) {
			//写日志
			log.error(e.getMessage());
			//设置错误返回消息
			return SysResult.build(201, "新增错误"+e.getMessage());
		}
		
	}
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item,desc);
			return SysResult.oK();
		} catch (Exception e) { 
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItems(Long[] ids){
		try {
			itemService.deleteItems(ids);
			return SysResult.oK();
		} catch (Exception e) {
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
	//获取商品详情
	@RequestMapping("query/item/desc/{itemId}")
	@ResponseBody
	public SysResult getItemDesc(@PathVariable Long itemId){
		ItemDesc itemDesc = itemService.getItemDesc(itemId);
		return SysResult.oK(itemDesc);
	}
	
}
