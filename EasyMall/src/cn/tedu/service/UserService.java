package cn.tedu.service;

import cn.tedu.entity.User;
import cn.tedu.exception.MsgException;

public interface UserService {

	/**业务层根据用户名和密码实现登录，如果根据用户和密码
	 * 能够查询出用户信息则返回该用户的信息，反之返回null
	 * @param username：用户名
	 * @param password：密码
	 * @return 存在则返回User user，不存在则返回null
	 */
	public User login(String username,String password);
	/**
	 * 判断用户名在user表中是否存在
	 * @param username
	 * */
	public boolean unIsExit(String username);
	/**注册用户
	 * @param user：User类的对象（封装了用户在注册页面提交
	 * 的信息)
	 * @return true:表示注册成功，false表示注册失败
	 * @throws MsgException 用户名已存在抛出异常
	 */
	public boolean regist(User user) throws MsgException;
}
