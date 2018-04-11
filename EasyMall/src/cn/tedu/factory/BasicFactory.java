package cn.tedu.factory;

import cn.tedu.utils.PropUtils;

public class BasicFactory {
	private BasicFactory() {
	}

	public static BasicFactory getFactory() {
		return new BasicFactory();
	}

	// 初中
	/*
	 * public Object getInstance(String key){ String implStr =
	 * PropUtils.getProperty(key); try { Class clz = Class.forName(implStr);
	 * return clz.newInstance(); } catch (Exception e) { e.printStackTrace(); }
	 * return null; }
	 */
	// 高中
	/*
	 * public Object getInstance(Class infclz){
	 * 
	 * //UserDao.class->"UserDao" //getName()->"cn.tedu.dao.UserDao"
	 * //getSimpleName()->"UserDao" String intfName = infclz.getSimpleName();
	 * //intfName:UserDao //获取UserDao在config.properties文件中对应实现类的包名.类名 String
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
		// 获取UserDao在config.properties文件中对应实现类的包名.类名
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
