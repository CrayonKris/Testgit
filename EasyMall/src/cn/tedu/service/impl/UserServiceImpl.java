package cn.tedu.service.impl;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.impl.UserDaoImpl;
import cn.tedu.entity.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.factory.UserDaoFactory;
import cn.tedu.service.UserService;

public class UserServiceImpl implements UserService {
	//private UserDao userDao=new UserDaoImpl();
	//��Сѧ��private UserDao userDao=UserDaoFactory.getFactory().getInstance();
	/*
	 * private UserDao userDao=(UserDao)BasicFactory.getFactory().
			getInstance("UserDao");
	 * */
	//����
	/*
	 * private UserDao userDao=(UserDao)BasicFactory.getFactory().
			getInstance(UserDao.class);
	 * */
	private UserDao userDao=BasicFactory.getFactory().
			getInstance(UserDao.class);
	public User login(String username,String password){
		return userDao.login(username,password);
	}
	public boolean unIsExit(String username) {
		return userDao.unIsExit(username.trim());
	}
	public boolean regist(User user) throws MsgException {
		if (userDao.unIsExit(user.getUsername())) {
			throw new MsgException("�û����Ѵ��ڣ���");
		}else{
			int row=userDao.regist(user);
			return row>0;
		}
	}	
}
