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

	// ctrl+shift+O：导入用到的类型
	// Ctrl+Shift+Enter：在光标所在的行插入一个空行
	// Shift+Enter：在光标所在行的下一行插入一个空行
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public User login(String username, String password) {
		try {
			// A1创建query对象
			//QueryRunner qr = new QueryRunner(DaoUtils.getPool());
			// A2编写sql语句
			String sql = "select * from user where username=?"
					+ " and password=?";
			// A3执行查询操作，并返回user类对象
			return DaoUtils.query(sql, 
					new BeanHandler<User>(User.class),
					username,password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 8.不存在则返回null
		return null;
	}

	public boolean unIsExit(String username) {
		try {
			
			// 1.创建QueryRunner对象
			//QueryRunner qr = new QueryRunner(DaoUtils.getPool());
			// 2.编写sql语句
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
			 * ") "+"values(?,?,?,?)"; ps=conn.prepareStatement(sql); //设置参数
			 * ps.setString(1, user.getUsername()); ps.setString(2,
			 * user.getPassword()); ps.setString(3, user.getNickname());
			 * ps.setString(4, user.getEmail()); //执行语句 return
			 * ps.executeUpdate();
			 */

			// A1创建query对象
			QueryRunner qr = new QueryRunner(DaoUtils.getPool());
			// A2.编写sql语句
			String sql = "insert into user(username,password,nickname,email"
					+ ",role) " + "values(?,?,?,?,?)";
			// A3.执行语句并返回影响的行数
			return qr.update(sql, user.getUsername(), user.getPassword(),
					user.getNickname(), user.getEmail(),"user");
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
