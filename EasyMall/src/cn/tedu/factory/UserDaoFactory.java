package cn.tedu.factory;

import cn.tedu.dao.UserDao;
import cn.tedu.utils.PropUtils;

public class UserDaoFactory {
	private UserDaoFactory(){}
	public static UserDaoFactory getFactory(){
		return new UserDaoFactory();
	}
	public static UserDao getInstance(){
		
		String val=PropUtils.getProperty("UserDao");
		try {
			Class clz=Class.forName(val);
			return (UserDao)clz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
