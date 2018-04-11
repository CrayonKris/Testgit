package cn.tedu.factory;

import cn.tedu.service.UserService;
import cn.tedu.utils.PropUtils;

public class UserServiceFactory {
	private UserServiceFactory(){}
	public static UserServiceFactory getFactory(){
		return new UserServiceFactory();
	}
	public static UserService getInstance(){
		//cn.tedu.service.impl.UserServiceImpl
		String val=PropUtils.getProperty("UserService");
		try {
			Class clz = Class.forName(val);
			return (UserService) clz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
