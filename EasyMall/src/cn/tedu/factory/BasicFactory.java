package cn.tedu.factory;

import cn.tedu.utils.PropUtils;

public class BasicFactory {
	private BasicFactory() {
	}

	public static BasicFactory getFactory() {
		return new BasicFactory();
	}

	// ����
	/*
	 * public Object getInstance(String key){ String implStr =
	 * PropUtils.getProperty(key); try { Class clz = Class.forName(implStr);
	 * return clz.newInstance(); } catch (Exception e) { e.printStackTrace(); }
	 * return null; }
	 */
	// ����
	/*
	 * public Object getInstance(Class infclz){
	 * 
	 * //UserDao.class->"UserDao" //getName()->"cn.tedu.dao.UserDao"
	 * //getSimpleName()->"UserDao" String intfName = infclz.getSimpleName();
	 * //intfName:UserDao //��ȡUserDao��config.properties�ļ��ж�Ӧʵ����İ���.���� String
	 * className = PropUtils.getProperty(intfName); try { Class
	 * clz=Class.forName(className); return clz.newInstance(); } catch
	 * (Exception e) { e.printStackTrace(); } return null; }
	 */
	//
	public <T> T getInstance(Class<T> infclz) {

		// UserDao.class->"UserDao"
		// getName()->"cn.tedu.dao.UserDao"
		// getSimpleName()->"UserDao"
		String intfName = infclz.getSimpleName();
		// intfName:UserDao
		// ��ȡUserDao��config.properties�ļ��ж�Ӧʵ����İ���.����
		String className = PropUtils.getProperty(intfName);
		try {
			Class clz = Class.forName(className);
			return (T) clz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
