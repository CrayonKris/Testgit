package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.web.pojo.Cart;
import com.jt.web.serivce.CartService;
import com.jt.web.threadLocal.UserThreadLocal;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	private CartService cartService;
	//向购物页面跳转
	@RequestMapping("/show")
	public String show(Model model) throws Exception{
		//
		Long userId = UserThreadLocal.getUserId();
		List<Cart> cartList=cartService.show(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	//保存商品到购物车 cart/add/562379.html  从商品页面加入购物车
	@RequestMapping("add/{itemId}")
	public String addCart(@PathVariable Long itemId,Integer num) throws Exception{
		//需要当前用户
		//Long userId=7L;
		Long userId=UserThreadLocal.getUserId();
		cartService.saveCart(userId,itemId,num);
		return "redirect:/cart/show.html";//重定向在一个数据链上就能获取到数据
	}
	//更新商品数量service/cart/update/{num}
	@RequestMapping("update/num/{itemId}/{num}")
	@ResponseBody
	public String updateNum(@PathVariable Long itemId,@PathVariable Integer num) throws Exception{
		//Long userId=531L;
		Long userId=UserThreadLocal.getUserId();
		cartService.updateNum(userId,itemId,num);
		return "";//如果这里直接返回,springmvc会不会拼接
		//WEB-INF/views*.jsp
	}
	//删除购物车的商品
	//http://www.jt.com/cart/delete/{itemId}.html
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId) throws Exception{
		//Long userId=531L;
		Long userId=UserThreadLocal.getUserId();
		cartService.delete(userId,itemId);
		return "redirect:/cart/show.html";
	}
}
