package cn.tedu.dao;

import cn.tedu.entity.User;

public interface UserDao {
	/**
	 * 根据用户名和密码查询对应的用户信息，存在则返回对应的用户信息，不存在则返回null
	 * @param username 用户名
	 * @param password 密码
	 * @return 存在则返回User类对象，反之返回null
	 * */

	User login(String username, String password);
	/**
	 * 判断用户名是否存在
	 * @param string 
	 * */
	boolean unIsExit(String string);
	/**
	 * 影响的行数
	 * */
	int regist(User user);

}
