package cn.tarena.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.DeptMapper;
import cn.tarena.ht.pojo.Dept;
@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper mapper;
	@Autowired
	private UserService userMapper;
	@Override
	public List<Dept> findAll() {
		return mapper.findAll();
	}
	@Override
	public void changeState(int i, String[] deptIds) {
		mapper.changeState(i, deptIds);
	}
	@Override
	public void deleteDepts(String[] deptIds) {
		userMapper.deleteUserDept(deptIds);
		mapper.deleteDepts(deptIds);
	}
	@Override
	public void saveDept(Dept dept) {
		mapper.saveDept(dept);
	}
	@Override
	public Dept findOne(String id) {
		return mapper.findOne(id);
	}
	@Override
	public void update(Dept dept) {
		mapper.update(dept);
	}
}
