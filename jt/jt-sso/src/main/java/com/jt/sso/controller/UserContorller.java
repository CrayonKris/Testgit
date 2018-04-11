package com.jt.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

@Controller
@RequestMapping("user")
public class UserContorller {
	@Autowired
	private UserService userSerevice;
	//检查的接收和返回的方法
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	//http://www.jt.com/user/register.html
	public SysResult check(@PathVariable String param,@PathVariable Integer type){
		try {
			Boolean result = userSerevice.check(param, type);
			return SysResult.oK(result);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "失败");
		}
	}
	//http://sso.jt.com/user/register
	//用户注册  
	@RequestMapping("/register")
	@ResponseBody
	public SysResult register(User user){
		String username = userSerevice.register(user);
		return SysResult.oK(username);
	}
	//http://sso.jt.com/user/login
	@RequestMapping("/login")
	@ResponseBody
	public SysResult login(String u,String p){
		//用户登录
		String ticket = userSerevice.login(u, p);
		if (StringUtils.isNotEmpty(ticket)) {
			return SysResult.oK(ticket);
		}else {
			return SysResult.build(201, "失败");
		}
	}
	//http://sso.jt.com/query/{}
	@RequestMapping("query/{ticket}")
	@ResponseBody
	public SysResult queryByTicket(@PathVariable String ticket){
		String userJson=userSerevice.queryByTicket(ticket);
		return SysResult.oK(userJson);
	}
}
