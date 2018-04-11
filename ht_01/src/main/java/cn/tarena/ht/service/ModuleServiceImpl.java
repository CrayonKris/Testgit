package cn.tarena.ht.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Module;
@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleMapper moduleMapper;
	public List<Module> findAll(){
		return moduleMapper.findAll();
	}
	@Override
	public void changeState(int i, String[] moduleIds) {
		moduleMapper.changeState(i,moduleIds);
		
	}
	@Override
	public void delete(String[] moduleIds) {
		moduleMapper.delete(moduleIds);
		moduleMapper.deleteRoleModule(moduleIds);
	}
	@Override
	public void save(Module module) {
		String moduleId = UUID.randomUUID().toString();
		module.setModuleId(moduleId);
		moduleMapper.save(module);
	}
	@Override
	public Module findOne(String moduleId) {

		return moduleMapper.findOne(moduleId);
	}
	@Override
	public void update(Module module) {
		moduleMapper.update(module);
	}
	@Override
	public List<String> findModuleByRoleId(String roleId) {
		
		return moduleMapper.findModuleByRoleId(roleId);
	}
}
