package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.mapper.UserInfoMapper;
import cn.tarena.ht.mapper.UserMapper;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.tool.MD5Utils;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	@Autowired
	private UserInfoMapper userInfomapper;
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<User> findAll() {
		return mapper.findAll();
	}
	@Override
	public void changeState(int i, String[] userIds) {
		mapper.changeState(i,userIds);
	}
	@Override
	public void delete(String[] userIds) {
		if (userIds!=null) {
			userInfomapper.delete(userIds);
			roleMapper.deleteByUserIds(userIds);
			mapper.delete(userIds);
		}
	}
	@Override
	public User findOne(String userId) {
		
		return mapper.findOne(userId);
	}
	@Override
	public void saveUser(User user) {
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		
		user.getUserInfo().setUserInfoId(userId);
		user.setPassword(MD5Utils.getMd5(user.getPassword(), user.getUsername()));
		Date date = new Date();
		user.setCreateTime(date);
		user.getUserInfo().setJoinDate(date);
		mapper.saveUser(user);
		userInfomapper.saveUserInfo(user.getUserInfo());
	}
	@Override
	public void update(User user) {
		mapper.update(user);
		user.getUserInfo().setUserInfoId(user.getUserId());
		userInfomapper.update(user.getUserInfo());
	}
	@Override
	public void saveUserAndRole(String userId, String[] roleIds) {
		mapper.deleteRoles(userId);
		if (roleIds.length>0) {
			mapper.saveUserAndRole(userId,roleIds);
		}
	}
	@Override
	public List<String> findRoels(String userId) {
		return mapper.findRoles(userId);
	}
	@Override
	public User login(String username, String password) {
		return mapper.login(username,password);
	}
	@Override
	public void deleteUserDept(String[] deptIds) {
		mapper.deleteUserDept(deptIds);
	}
	
}
