package cn.tedu.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.tedu.entity.User;
import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;
import cn.tedu.utils.MD5Utils;


public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.���ý��ղ����ı����ʽ
		//request.setCharacterEncoding("utf-8");
		// ��ȡ��¼��Ϣ
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remname = request.getParameter("remname");

		// A2.����ҵ������
		UserService userService = new UserServiceImpl();
		password=MD5Utils.md5(password);
		// A3.����ҵ���ķ���
		User user = userService.login(username, password);
		// A4.�жϵ�½�Ƿ�ɹ�
		if (user == null) {
			// A5��½ʧ�ܣ���ʾ�û������������->login.jsjp
			request.setAttribute("msg", "�û������������");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			// A6��¼�ɹ�
			// �ж��Ƿ���Ҫ��ס�û���
			if ("true".equals(remname)) {
				// ���û�������URL����֮���ٴ���cookie��
				Cookie cookie = new Cookie("remname", URLEncoder.encode(
						username, "utf-8"));
				// ���������ʱ��
				cookie.setMaxAge(3600 * 24 * 30);
				// ����cookie��·��
				cookie.setPath(request.getContextPath() + "/");
				// ��cookie��ӵ���Ӧ��
				response.addCookie(cookie);

			} else {// ȡ����ס�û���(ɾ��cookie)
				Cookie cookie = new Cookie("remname", "");
				cookie.setMaxAge(0);// ����ɾ��cookie
				cookie.setPath(request.getContextPath() + "/");
				// ��cookie��ӵ���Ӧ��
				response.addCookie(cookie);
			}
			//30���Զ���¼
			if ("true".equals(request.getParameter("autologin"))) {
				//����cookie����---��������
				Cookie atlCk = new Cookie("autologin",URLEncoder.encode(username
						+","+password,"utf-8"));
				atlCk.setPath("/");//
				atlCk.setMaxAge(2592000);//30*24*3600
				response.addCookie(atlCk);
			}
			// A7.���е�¼���������û���Ϣ�����session�У�
			request.getSession().setAttribute("loginUser", user);
			
			// ��¼�ɹ�����ת ����ҳ����
			//response.sendRedirect(request.getContextPath()+ "/index.jsp");
			request.getRequestDispatcher("/index.jsp").forward(request, response);

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);

	}
}