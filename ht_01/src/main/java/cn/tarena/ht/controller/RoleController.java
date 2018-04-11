package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.service.ModuleService;
import cn.tarena.ht.service.RoleService;

@Controller
@RequestMapping("/sysadmin/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuleService moduleService;
	@RequestMapping("/list")
	public String list(Model model){
		//查询所有的角色信息
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "/sysadmin/role/jRoleList";
	}
	@RequestMapping("/delete")
	public String delete (@RequestParam(value="roleId",required=false)String[] roleIds){
		if (roleIds!=null) {
			roleService.delete(roleIds);
			
		}
		return "redirect:/sysadmin/role/list";
	}
	@RequestMapping("/tocreate")
	public String tocreate(){
		return "/sysadmin/role/jRoleCreate";
	}
	@RequestMapping("/tosave")
	public String tosave(Role role){
		roleService.tosave(role);
		return "redirect:/sysadmin/role/list";
	}
	@RequestMapping("/toview")
	public String toview(String roleId,Model model){
		Role role = roleService.findOne(roleId);
		model.addAttribute("role", role);
		return "sysadmin/role/jRoleView";
	}
	@RequestMapping("/toupdate")
	public String toupdate(String roleId,Model model){
		Role role = roleService.findOne(roleId);
		model.addAttribute("role", role);
		return "/sysadmin/role/jRoleUpdate";
	}
	@RequestMapping("/update")
	public String update(Role role){
		roleService.update(role);
		return "redirect:/sysadmin/role/list";
	}
	@RequestMapping("/toModule")
	public String toModule(String roleId,Model model) throws JsonProcessingException{
		List<Module> modules = moduleService.findAll();
		List<String> roleModules = moduleService.findModuleByRoleId(roleId);
		for (Module module : modules) {
			if (roleModules.contains(module.getModuleId())) {
				module.setChecked(true);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String zTreeJson = mapper.writeValueAsString(modules);
		model.addAttribute("zTreeJson", zTreeJson);
		model.addAttribute("roleId", roleId);
		return "sysadmin/role/jRoleModule";	
	}
	@RequestMapping("/saveRoleModule")
	public String saveRoleModule(String roleId,String[] moduleIds){
		roleService.saveRoleModule(roleId,moduleIds);
		return "redirect:/sysadmin/role/list";
	}
}
