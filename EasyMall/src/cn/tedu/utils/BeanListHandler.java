package cn.tedu.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements ResultSetHandler<List<T>> {

	private Class<T> type;
	public BeanListHandler(Class<T> type) {
		this.type = type;
	}
	/*public List<T> handle(ResultSet rs) throws SQLException {
		List<T> list=new ArrayList<T>();
		try {
		while(rs.next()){
			try {
				T t=type.newInstance();
				BeanInfo binfo=Introspector.getBeanInfo(type);
				PropertyDescriptor[] pds=binfo.getPropertyDescriptors();
				for (int i = 0; i < pds.length; i++) {
					PropertyDescriptor pd=pds[i];
					String name=pd.getName();
					Method md=pd.getWriteMethod();
					try {
						md.invoke(t, rs.getObject(name));
					} catch (SQLException e) {
						continue;
					}
				}
				list.add(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/
	public List<T> handle(ResultSet rs) throws SQLException {
		List<T> list =new ArrayList<T>();
		try {
			while(rs.next()){
				
					T t=type.newInstance();
					BeanInfo binfo=Introspector.getBeanInfo(type);
					PropertyDescriptor[] pds=binfo.getPropertyDescriptors();
					for (int i = 0; i < pds.length; i++) {
						PropertyDescriptor pd=pds[i];
						String name=pd.getName();
						Method mtd = pd.getWriteMethod();
						try {
							Object obj=null;
							if (pd.getPropertyType()==int.class) {
								obj=rs.getInt(name);
							} else {
								obj=rs.getObject(name);
							}
							mtd.invoke(t,obj);
						} catch (SQLException e) {
							continue;
						}
					}
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		}
