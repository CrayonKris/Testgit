package cn.tedu.dao;

import cn.tedu.entity.User;

public interface UserDao {
	/**
	 * �����û����������ѯ��Ӧ���û���Ϣ�������򷵻ض�Ӧ���û���Ϣ���������򷵻�null
	 * @param username �û���
	 * @param password ����
	 * @return �����򷵻�User����󣬷�֮����null
	 * */

	User login(String username, String password);
	/**
	 * �ж��û����Ƿ����
	 * @param string 
	 * */
	boolean unIsExit(String string);
	/**
	 * Ӱ�������
	 * */
	int regist(User user);

}
