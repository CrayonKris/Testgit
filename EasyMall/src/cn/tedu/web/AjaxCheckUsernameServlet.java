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
		//1.������Ӧ���ĺ������������
		//response.setContentType("text/html;charset=utf-8");
		//request.setCharacterEncoding("utf-8");
		//2.��ȡ�û��������ܲ�����
		String username = request.getParameter("username");
		//3.����ҵ������
		//��Сѧ��UserService userService = new UserServiceImpl();
		UserService userService = UserServiceFactory.getFactory()
						.getInstance();
		//4.����ҵ���ķ�������û����Ƿ����
		boolean res = userService.unIsExit(username.trim());
		//5.���ݲ�ѯ���Ƿ���ڵĽ��������Ӧ
		if (res) {
			response.getWriter().write("<font style='color:red'>�û����Ѿ����ڣ���</font>");
		}else{
			response.getWriter().write("<font style='color:red'>��ϲ���û�������ʹ�ã�</font>");
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
			if (rs.next()) {//�û�������
				response.getWriter().write("<font style='color:red'>�û����Ѿ����ڣ���</font>");
			}else{
				response.getWriter().write("<font style='color:red'>��ϲ���û�������ʹ�ã�</font>");
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
