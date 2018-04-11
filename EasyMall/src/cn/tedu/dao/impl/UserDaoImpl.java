package cn.tedu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;
import cn.tedu.utils.BeanHandler;
import cn.tedu.utils.DaoUtils;


public class UserDaoImpl implements UserDao {

	// ctrl+shift+O�������õ�������
	// Ctrl+Shift+Enter���ڹ�����ڵ��в���һ������
	// Shift+Enter���ڹ�������е���һ�в���һ������
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public User login(String username, String password) {
		try {
			// A1����query����
			//QueryRunner qr = new QueryRunner(DaoUtils.getPool());
			// A2��дsql���
			String sql = "select * from user where username=?"
					+ " and password=?";
			// A3ִ�в�ѯ������������user�����
			return DaoUtils.query(sql, 
					new BeanHandler<User>(User.class),
					username,password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 8.�������򷵻�null
		return null;
	}

	public boolean unIsExit(String username) {
		try {
			
			// 1.����QueryRunner����
			//QueryRunner qr = new QueryRunner(DaoUtils.getPool());
			// 2.��дsql���
			String sql = "select * from user where username=?";
			//return DaoUtils.query(sql, new BeanListHandler<User>(User.class), username) != null;
			return DaoUtils.query(sql, new BeanHandler<User>(User.class), username) != null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int regist(User user) {
		try {
			/*
			 * conn=DaoUtils.getConn(); 
			 * String sql="insert into user(username,password,nickname,email" +
			 * ") "+"values(?,?,?,?)"; ps=conn.prepareStatement(sql); //���ò���
			 * ps.setString(1, user.getUsername()); ps.setString(2,
			 * user.getPassword()); ps.setString(3, user.getNickname());
			 * ps.setString(4, user.getEmail()); //ִ����� return
			 * ps.executeUpdate();
			 */

			// A1����query����
			QueryRunner qr = new QueryRunner(DaoUtils.getPool());
			// A2.��дsql���
			String sql = "insert into user(username,password,nickname,email"
					+ ",role) " + "values(?,?,?,?,?)";
			// A3.ִ����䲢����Ӱ�������
			return qr.update(sql, user.getUsername(), user.getPassword(),
					user.getNickname(), user.getEmail(),"user");
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
