package cn.tarena.ht.tool;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Utils {
	public static String getMd5(String password,String salt){
		//String pw="admin";
		
		//hashIterations 和稀泥的次数
		Md5Hash mh = new Md5Hash(password,salt,3);
		System.out.println(mh);
		return mh.toString();
	}
}
