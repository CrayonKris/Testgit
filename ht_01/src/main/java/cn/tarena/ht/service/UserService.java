package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.User;

public interface UserService {
	public List<User> findAll();

	public void changeState(int i, String[] userIds);

	public void delete(String[] userIds);

	public User findOne(String userId);

	public void saveUser(User user);

	public void update(User user);

	public void saveUserAndRole(String userId, String[] roleIds);

	public List<String> findRoels(String userId);

	public User login(String username, String password);

	public void deleteUserDept(String[] deptIds);

}
