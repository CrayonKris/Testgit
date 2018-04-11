package cn.tedu.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T> {
	//把rs中的数据->T t 或者 List list
	T handle(ResultSet rs) throws SQLException;
	
}
