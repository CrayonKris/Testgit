package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.ItemCatResult;
import com.jt.manage.service.WebItemCatService;

@Controller
@RequestMapping("/web/itemcat")
public class WebItemCatController {
	@Autowired
	private WebItemCatService itemCatService;
	@RequestMapping("all")
	@ResponseBody
	public ItemCatResult queryItemCatList(){
		ItemCatResult result = itemCatService.queryItemCat();
		return result;
	}
}
