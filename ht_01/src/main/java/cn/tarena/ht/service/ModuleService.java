package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Module;

public interface ModuleService {
	public List<Module> findAll();

	public void changeState(int i, String[] moduleIds);

	public void delete(String[] moduleIds);

	public void save(Module module);

	public Module findOne(String moduleId);

	public void update(Module module);

	public List<String> findModuleByRoleId(String roleId);
}
