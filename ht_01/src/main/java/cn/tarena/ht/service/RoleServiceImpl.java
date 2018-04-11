package cn.tarena.ht.service;

import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Role;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ModuleMapper moduleMapper;
	@Override
	public List<Role> findAll() {
		return roleMapper.findAll();
	}
	@Override
	public void delete(String[] roleIds) {
		roleMapper.delete(roleIds);
		roleMapper.deleteModuleRole(roleIds);
		roleMapper.deleteUserRole(roleIds);
	}
	
	@Override
	public void tosave(Role role) {
		String roleId = UUID.randomUUID().toString();
		role.setRoleId(roleId);
		roleMapper.tosave(role);
	}
	@Override
	public Role findOne(String roleId) {
		
		return roleMapper.findOne(roleId);
	}
	@Override
	public void update(Role role) { 
		roleMapper.update(role);
	}
	@Override
	public void saveRoleModule(String roleId, String[] moduleIds) {
		roleMapper.deleteModules(roleId);
		roleMapper.saveRoleModule(roleId,moduleIds);
	}
}
