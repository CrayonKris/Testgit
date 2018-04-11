package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult queryMyCart(@PathVariable Long userId){
		List<Cart> cartList = cartService.queryMyCart(userId);
		if (!cartList.isEmpty()) {
			return SysResult.oK(cartList);
		}else{
			return SysResult.build(201, "购物车为空");
		}
	}
	//保存购物车
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveCart(Cart cart){
		cartService.saveCart(cart);
		return SysResult.oK();//210 202前台没有用
	}
	//更新购物车数量
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	public SysResult updateNum(@PathVariable Long userId,@PathVariable Long itemId, 
			@PathVariable Integer num){
		Cart param = new Cart();
		param.setUserId(userId);
		param.setItemId(itemId);
		param.setNum(num);
		cartService.updateNum(param);
		return SysResult.oK();
	}
	//删除购物车/delete/{userId}/{itemId}
	@RequestMapping("/delete/{userId}/{itemId}")
	@ResponseBody
	public SysResult deleteCart(@PathVariable Long userId,
			@PathVariable Long itemId){
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cartService.deleteByWhere(cart);
		return SysResult.oK();
	}
}
