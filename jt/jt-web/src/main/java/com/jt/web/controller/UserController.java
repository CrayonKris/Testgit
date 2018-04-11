package com.jt.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.serivce.UserService;
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	//http://www.jt.com/user/register.html
	@RequestMapping("user/register")
	public String registerIndex(){
		return "register";
	}
	@RequestMapping("/user/doRegister")
	@ResponseBody
	public SysResult doRegister(User user){
		String username=null;
		try {
			username = userService.doRegister(user);
			if (StringUtils.isNotEmpty(username)) {
				return SysResult.oK(username);
			}else {
				return SysResult.build(201, "注册失败！",user.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "注册失败",user.getUsername());
		}
	}
	//转向登录页面/user/login.html
	@RequestMapping("user/login")
	public String loginIndex(){
		return "login";
	}
	@RequestMapping("user/doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletRequest request
			,HttpServletResponse response) throws Exception{
		String ticket = userService.doLogin(user);
		if (StringUtils.isNotEmpty(ticket)) {
			//如果有值则登录成功，封装ticket到cookie
			String cookieName = "JT_TICKET";
			CookieUtils.setCookie(request, response, cookieName, ticket);
			return SysResult.oK();
		}else {
			return SysResult.build(201, "登录失败");
		}
	}
	//用户注销/user/logout.html
	@RequestMapping("user/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		//删除cookie
		String cookieName = "JT_TICKET";
		CookieUtils.deleteCookie(request, response, cookieName);
		//还需要清除UserThreadLocal
		//缓存可以清除redies 除了删除 过期 LUR还会计算最近未使用，并将其删除
		return "index";
	}
	
}
