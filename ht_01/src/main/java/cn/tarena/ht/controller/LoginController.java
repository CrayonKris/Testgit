package cn.tarena.ht.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;
import cn.tarena.ht.tool.MD5Utils;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping("/tologin")
	public String tologin(){
		return "/sysadmin/login/login";
	}
	@RequestMapping("/login")
	public String login(String username,String password,Model model,HttpSession session){
		//username!=null&&username!=""
		if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
			model.addAttribute("errorInfo", "用户名和密码不能为空！！");
			return "/sysadmin/login/login";
		}
		password = MD5Utils.getMd5(password, username);
		
		User user = userService.login(username,password);
		if (user==null) {
			model.addAttribute("errorInfo", "用户名或密码不正确");
			return "/sysadmin/login/login";
		}
		session.setAttribute("_CURRENT_USER", user);
		//登陆成功跳转到首页
		return "redirect:/home";
	}
}
