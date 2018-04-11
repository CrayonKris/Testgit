package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.service.ModuleService;
import cn.tarena.ht.service.RoleService;

@Controller
@RequestMapping("/sysadmin/module")
public class ModuleController {
	
	@Autowired
	private ModuleService moduleService;
	//关联删除 注入  
	@RequestMapping("/list")
	public String findAll(Model model){
		List<Module> modules = moduleService.findAll();
		model.addAttribute("modules", modules);
		return "/sysadmin/module/jModuleList";
	}
	@RequestMapping("/stop")
	public String stop(@RequestParam(value="moduleId")String[] moduleIds){
		if (moduleIds!=null) {
			moduleService.changeState(0,moduleIds);
		}
		return "redirect:/sysadmin/module/list";
	}
	@RequestMapping("/start")
	public String start(@RequestParam(value="moduleId")String[] moduleIds){
		if (moduleIds!=null) {
			moduleService.changeState(1,moduleIds);
		}
		return "redirect:/sysadmin/module/list";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="moduleId",required=false)String[] moduleIds){
		if (moduleIds!=null) {
			moduleService.delete(moduleIds);
		}
		return "redirect:/sysadmin/module/list";
	}
	@RequestMapping("/tocreate")
	public String tocreate(Model model){
		//查询所有的模块信息 为了实现下拉列表 选取上级模块
		List<Module> modules = moduleService.findAll();
		model.addAttribute("modules", modules);
		return "/sysadmin/module/jModuleCreate";
	}
	@RequestMapping("/tosave")
	public String save(Module module){
		moduleService.save(module);
		return "redirect:/sysadmin/module/list";
	}
	@RequestMapping("/toview")
	public String toview(String moduleId,Model model){
		Module module = moduleService.findOne(moduleId);
		model.addAttribute("module", module);
		return "/sysadmin/module/jModuleView";
	}
	@RequestMapping("/toupdate")
	public String toupdate(String moduleId,Model model){
		Module module = moduleService.findOne(moduleId);
		model.addAttribute("module", module);
		//得到所有模块
		List<Module> modules = moduleService.findAll();
		model.addAttribute("modules", modules);
		return "/sysadmin/module/jModuleUpdate";
	}
	@RequestMapping("/update")
	public String update(Module module){
		moduleService.update(module);
		return "redirect:/sysadmin/module/list";
	}
}
