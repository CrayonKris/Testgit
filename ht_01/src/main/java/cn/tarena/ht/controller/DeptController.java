package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.service.DeptService;

@Controller
@RequestMapping("/sysadmin/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	@RequestMapping("/list")
	public String deptList(Model model){
		List<Dept> depts = deptService.findAll(); 
		model.addAttribute("deptList", depts);
		return "/sysadmin/dept/jDeptList";
	}
	/*//单个启用或停止==============
	@RequestMapping("/sysadmin/dept/stop")
	public String startAction(String deptId){
		deptService.changeState(0,deptId);
		return "redirect:/sysadmin/dept/list";
		
	}
	@RequestMapping("/sysadmin/dept/start")
	public String stopAction(String deptId){
		deptService.changeState(1, deptId);
		return "redirect:/sysadmin/dept/list";
	}*/
	//批量启用和停止
	@RequestMapping("/stop")
	public String startAction(@RequestParam(value="deptId",required=false)String[] deptIds){
		if(deptIds!=null){
			deptService.changeState(0, deptIds);
		}
		return "redirect:/sysadmin/dept/list";
		
	}
	@RequestMapping("/start")
	public String stopAction(@RequestParam(value="deptId",required=false)String[] deptIds){
		if (deptIds!=null) {
			deptService.changeState(1, deptIds);
		}
		return "redirect:/sysadmin/dept/list";
	}
	//删除部门
	@RequestMapping("/delete")
	public String deleteDepts(@RequestParam(value="deptId",required=false)String[] deptIds){
		if (deptIds!=null) {
			deptService.deleteDepts(deptIds);
		}
		return "redirect:/sysadmin/dept/list";
	}
	//跳转新增部门页面
	@RequestMapping("/tocreate")
	public String toCreate(Model model){
		//给页面提供上级部门的信息 为了做下拉列表
		List<Dept> depts = deptService.findAll();
		model.addAttribute("deptList", depts);
		return "/sysadmin/dept/jDeptCreate";
	}
	//拦截保存请求
	@RequestMapping("/tosave")
	public String toSave(Dept dept){
		deptService.saveDept(dept);
		return "redirect:/sysadmin/dept/list";
	}
	@RequestMapping("/toupdate")
	public String toUpdate(String deptId,Model model){
		Dept dept = deptService.findOne(deptId);
		model.addAttribute("dept", dept);
		List<Dept> depts = deptService.findAll();
		model.addAttribute("depts", depts);
		return "/sysadmin/dept/jDeptUpdate";
	}
	@RequestMapping("/update")
	public String update(Dept dept){
		deptService.update(dept);
		return "redirect:/sysadmin/dept/list";
	}
	@RequestMapping("/toview")
	public String toView(String deptId,Model model){
		Dept dept = deptService.findOne(deptId);
		model.addAttribute("dept",dept);
		return "/sysadmin/dept/jDeptView";
	}
}