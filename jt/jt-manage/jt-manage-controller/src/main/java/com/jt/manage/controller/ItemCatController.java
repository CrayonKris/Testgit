package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	@ResponseBody//springmvc会将返回的对象利用jackson json转换成对应格式的字符串
	@RequestMapping("/queryall")
	public List<ItemCat> queryAll(){
		List<ItemCat> list = itemCatService.queryAll();
		return list;
	}
	@RequestMapping("/list")
	@ResponseBody
	public List<ItemCat> queryItemCatList(@RequestParam(defaultValue="0")Integer id){
		List<ItemCat> itemCats = itemCatService.queryItemCatList(id);
		return itemCats;
	}
}
