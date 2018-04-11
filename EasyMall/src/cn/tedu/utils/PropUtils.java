package cn.tedu.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {
	//创建properties类对象
	private static Properties prop=new Properties();
	//将构造方法私有化（单利模式）目的，让整个项目只有一个对象
	private PropUtils(){};
	static{//类加载时只加载一次
		//获取文件所在路径名（路径+文件名称+后缀名称）
		//TODO 类加载器
		String path=PropUtils.class.getClassLoader().
				getResource("conf.properties").getPath();
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProp(){
		return prop;
	}
	public static String getProperty(String key){
		return prop.getProperty(key);
	}

}
