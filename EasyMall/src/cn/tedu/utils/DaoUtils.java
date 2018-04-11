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
 * �������Ӻ͹رն�Ĺ�����
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
	 * ��ѯ�������ȿ��Բ�һ�����ݣ�Ҳ���Բ�������ݣ�
	 * @param sql: sql���
	 * @param rsh: ��һ������ new BeanHandler<T>(T.class)
	 * @param params:��ѯ�����Ĳ�ѯ
	 * @return ��ѯһ�� ���� T t;��ѯ��������List<T>
	 * @throws SQLException
	 * */
	public static <T> T query(String sql,ResultSetHandler<T> rsh,Object...params) 
		throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1.��ȡ���ݿ�����
			conn=getConn();
			//2.Ԥ����sql��������ps
			ps=conn.prepareStatement(sql);
			//3.Ϊռλ����ֵ
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			//4.ִ�в�ѯ���������ؽ����
			rs=ps.executeQuery();
			//5.rs->T t List<T> ���ض���
			return rsh.handle(rs);
		} catch (SQLException e) {
			throw e;
		}finally{
			DaoUtils.close(conn, ps, rs);
		}
	
	}
	/**
	 * ����޸�ɾ�������ø÷���
	 * @param:sql:sql ���
	 * @param:params:Ϊռλ����ֵ�Ĳ���(��������)
	 * @return Ӱ�������
	 * @throws SQLException 
	 * */
	public static int update(String sql,Object...params) throws SQLException{
		//�������ݿ����Ӷ���
		Connection conn=null;
		PreparedStatement ps=null;
		try {
		//2.��ȡ���ݿ�����
		conn=getConn();
		//3.Ԥ����sql��䣬����ps����
		ps=conn.prepareStatement(sql);
		//4.Ϊռλ����ֵ
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i+1, params[i]);
		}
		//5.ִ�в�������ӡ��޸ģ�ɾ������������Ӱ�������
		return ps.executeUpdate();
		} catch (SQLException e) {
			//6.�����׳��쳣
			throw e;
		}finally{
			//7.�ر����ݿ����Ӷ���
			DaoUtils.close(conn, ps, null);
		}
	}
	
	/**
	 * �ر���Դ
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
		//�������ݿ����Ӷ���
				PreparedStatement ps=null;
				try {
				//2.��ȡ���ݿ�����
				//3.Ԥ����sql��䣬����ps����
				ps=conn.prepareStatement(sql);
				//4.Ϊռλ����ֵ
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
				//5.ִ�в�������ӡ��޸ģ�ɾ������������Ӱ�������
				return ps.executeUpdate();
				} catch (SQLException e) {
					//6.�����׳��쳣
					throw e;
				}finally{
					//7.�ر����ݿ����Ӷ���
					DaoUtils.close(null, ps, null);
				}
	}
	public static <T>T query(Connection conn, String sql,
			ResultSetHandler<T> rsh,Object...params) throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1.��ȡ���ݿ�����
			//2.Ԥ����sql��������ps
			ps=conn.prepareStatement(sql);
			//3.Ϊռλ����ֵ
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			//4.ִ�в�ѯ���������ؽ����
			rs=ps.executeQuery();
			//5.rs->T t List<T> ���ض���
			return rsh.handle(rs);
		} catch (SQLException e) {
			throw e;
		}finally{
			DaoUtils.close(null, ps, rs);
		}
	}

}
