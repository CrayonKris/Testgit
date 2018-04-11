package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.service.DeptService;
import cn.tarena.ht.service.RoleService;
import cn.tarena.ht.service.UserInfoService;
import cn.tarena.ht.service.UserService;

@Controller
@RequestMapping("/sysadmin/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;//查询全部
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private RoleService roleService;
	@RequestMapping("/list")
	public String userList(Model model){
		List<User> users= userService.findAll();
		model.addAttribute("users", users);
		return "/sysadmin/user/jUserList";
		
	}
	//修改状态
	@RequestMapping("/stop")
	public String stop(@RequestParam(value="userId",required=false)String[] userIds){
		userService.changeState(0, userIds);
		return "redirect:/sysadmin/user/list";
	}
	@RequestMapping("/start")
	public String start(@RequestParam(value="userId",required=false)String[] userIds){
		userService.changeState(1, userIds);
		return "redirect:/sysadmin/user/list";
	}
	//删除
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="userId",required=false)String[] userIds){
		userService.delete(userIds);
		return "redirect:/sysadmin/user/list";
	}
	//查看
	@RequestMapping("/toview")
	public String toview(String userId,Model model){
		User user = userService.findOne(userId);
		model.addAttribute("user", user);
		List<Dept> depts = deptService.findAll();
		model.addAttribute("depts", depts);
		List<UserInfo> userInfos = userInfoService.findAll();
		model.addAttribute("userInfos", userInfos);
		return "/sysadmin/user/jUserView";
	}
	//新增================
	@RequestMapping("/tocreate")
	public String tocreate(Model model){
		List<Dept> depts = deptService.findAll();
		model.addAttribute("depts", depts);
		List<UserInfo> userInfos = userInfoService.findAll();
		model.addAttribute("userInfos", userInfos);
		return "/sysadmin/user/jUserCreate";
	}
	@RequestMapping("/tosave")
	public String tosave(User user){
		userService.saveUser(user);
		return "redirect:/sysadmin/user/list";
	}
	//修改
	@RequestMapping("/toupdate")
	public String toupdate(String userId,Model model){
		User user = userService.findOne(userId);
		model.addAttribute("user", user);
		
		List<Dept> depts = deptService.findAll();
		model.addAttribute("depts", depts);
		List<UserInfo> userInfos = userInfoService.findAll();
		model.addAttribute("userInfos", userInfos);
		return "/sysadmin/user/jUserUpdate";
	}
	@RequestMapping("/update")
	public String update(User user){
		userService.update(user);
		return "redirect:/sysadmin/user/list";
	}
	//转到角色分配页面
	@RequestMapping("/role")
	public String role(String userId,Model model) throws JsonProcessingException{
		//根据userid查询角色信息
		List<String> userRoles = userService.findRoels(userId);
		//获取所有的角色
		List<Role> roleList = roleService.findAll();
		for (Role role : roleList) {
			if (userRoles.contains(role.getId())) {
				//当前的ID是用户已有的角色信息
				role.setChecked(true);
			}
		}
		//把roleList对象转成json字符串
		ObjectMapper objectMapper = new ObjectMapper();
		String zTreeJson = objectMapper.writeValueAsString(roleList);
		System.out.println(zTreeJson);
		model.addAttribute("zTreeJson", zTreeJson);
		//把id传到页面中，因为下次请求需要用到
		model.addAttribute("id", userId);
		return "/sysadmin/user/jUserRole";
	}
	@RequestMapping("/saveUserRole")
	public String saveUserRole(String userId,@RequestParam(required=false)String[] roleIds){
		if (roleIds!=null) {
			//把用户和角色信息保存到中间关系表中
			userService.saveUserAndRole(userId,roleIds);
		}
		return "redirect:/sysadmin/user/list";
	}
	
}
