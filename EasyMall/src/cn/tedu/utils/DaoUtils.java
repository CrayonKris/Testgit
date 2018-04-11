package cn.tedu.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import cn.tedu.entity.Product;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 数据连接和关闭额的工具类
 * */
public class DaoUtils {
	private static ComboPooledDataSource pool = new ComboPooledDataSource();
	private DaoUtils(){}
	public static ComboPooledDataSource getPool(){
		return pool;
	}
	public static Connection getConn() throws SQLException{
		return pool.getConnection();
	}
	/**
	 * 查询方法（既可以查一条数据，也可以查多条数据）
	 * @param sql: sql语句
	 * @param rsh: 查一条数据 new BeanHandler<T>(T.class)
	 * @param params:查询条件的查询
	 * @return 查询一条 返回 T t;查询多条返回List<T>
	 * @throws SQLException
	 * */
	public static <T> T query(String sql,ResultSetHandler<T> rsh,Object...params) 
		throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1.获取数据库连接
			conn=getConn();
			//2.预编译sql，并返回ps
			ps=conn.prepareStatement(sql);
			//3.为占位符赋值
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			//4.执行查询操作，返回结果集
			rs=ps.executeQuery();
			//5.rs->T t List<T> 返回对象
			return rsh.handle(rs);
		} catch (SQLException e) {
			throw e;
		}finally{
			DaoUtils.close(conn, ps, rs);
		}
	
	}
	/**
	 * 添加修改删除都调用该方法
	 * @param:sql:sql 语句
	 * @param:params:为占位符赋值的参数(对象数组)
	 * @return 影响的行数
	 * @throws SQLException 
	 * */
	public static int update(String sql,Object...params) throws SQLException{
		//声明数据库连接对象
		Connection conn=null;
		PreparedStatement ps=null;
		try {
		//2.获取数据库连接
		conn=getConn();
		//3.预编译sql语句，返回ps对象
		ps=conn.prepareStatement(sql);
		//4.为占位符赋值
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i+1, params[i]);
		}
		//5.执行操作（添加、修改，删除），并返回影响的行数
		return ps.executeUpdate();
		} catch (SQLException e) {
			//6.继续抛出异常
			throw e;
		}finally{
			//7.关闭数据库连接对象
			DaoUtils.close(conn, ps, null);
		}
	}
	
	/**
	 * 关闭资源
	 * */
	public static void close(Connection conn,Statement stat,ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				rs=null;
			}
		}
		if (stat!=null) {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				stat=null;
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				conn=null;
			}
		}
	}
	public static int update(Connection conn, String sql, Object...params) throws SQLException {
		//声明数据库连接对象
				PreparedStatement ps=null;
				try {
				//2.获取数据库连接
				//3.预编译sql语句，返回ps对象
				ps=conn.prepareStatement(sql);
				//4.为占位符赋值
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
				//5.执行操作（添加、修改，删除），并返回影响的行数
				return ps.executeUpdate();
				} catch (SQLException e) {
					//6.继续抛出异常
					throw e;
				}finally{
					//7.关闭数据库连接对象
					DaoUtils.close(null, ps, null);
				}
	}
	public static <T>T query(Connection conn, String sql,
			ResultSetHandler<T> rsh,Object...params) throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1.获取数据库连接
			//2.预编译sql，并返回ps
			ps=conn.prepareStatement(sql);
			//3.为占位符赋值
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			//4.执行查询操作，返回结果集
			rs=ps.executeQuery();
			//5.rs->T t List<T> 返回对象
			return rsh.handle(rs);
		} catch (SQLException e) {
			throw e;
		}finally{
			DaoUtils.close(null, ps, rs);
		}
	}

}
