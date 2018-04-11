package cn.tedu.utils;

public class WebUtils {
	private WebUtils(){}
	/**
	 * ÅĞ¶ÏÄ³×Ö·û´®ÊÇ·ñÎª¿Õ£¨null»ò""£©
	 * @param str
	 * @return true ±íÊ¾×Ö·û´®Îª¿Õ
	 * */
	public static boolean isNull(String str){
		boolean res = str==null||"".equals(str);
		return res;
	}

}
