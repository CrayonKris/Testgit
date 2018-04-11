package cn.tedu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.factory.UserServiceFactory;
import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;

public class AjaxCheckUsernameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.处理响应正文和请求参数乱码
		//response.setContentType("text/html;charset=utf-8");
		//request.setCharacterEncoding("utf-8");
		//2.获取用户名（接受参数）
		String username = request.getParameter("username");
		//3.创建业务层对象
		//（小学）UserService userService = new UserServiceImpl();
		UserService userService = UserServiceFactory.getFactory()
						.getInstance();
		//4.调用业务层的方法检查用户名是否存在
		boolean res = userService.unIsExit(username.trim());
		//5.根据查询的是否存在的结果作出响应
		if (res) {
			response.getWriter().write("<font style='color:red'>用户名已经存在！！</font>");
		}else{
			response.getWriter().write("<font style='color:red'>恭喜，用户名可以使用！</font>");
		}
		
		/*
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		ComboPooledDataSource pool=new ComboPooledDataSource();
		try {
			conn=pool.getConnection();
			String sql="select * from user where username=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			
			rs=ps.executeQuery();
			if (rs.next()) {//用户名存在
				response.getWriter().write("<font style='color:red'>用户名已经存在！！</font>");
			}else{
				response.getWriter().write("<font style='color:red'>恭喜，用户名可以使用！</font>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DaoUtils.close(conn, ps, rs);
		}*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
