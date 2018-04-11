package cn.tedu.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.tedu.entity.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.factory.UserServiceFactory;
import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;
import cn.tedu.utils.MD5Utils;

/**
 * �����û�����
 * */
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* �����������Ҫ����ͼƬ */
		/* ����һ����֤��ͼƬ���͸������ */
		// ��ȡ��֤���ı�
		// 1.������Ӧ���ĺ������������
		//response.setContentType("text/html;charset=utf-8");
		//request.setCharacterEncoding("utf-8");
		// 2.��ȡ��������������û���ע����Ϣ��
		User user = new User();
		// ҳ���еı���name���Ե�ֵһ��Ҫ��User����������һ��
		try {
			//TODO 
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		// 3.����У��
		try {
			user.check();
		} catch (MsgException e1) {
			// ��ת��ע��ҳ��
			request.setAttribute("msg", e1.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
		}

		// -----�ж���֤���Ƿ���ȷ
		Object obj = request.getSession().getAttribute("code");
		if (obj == null || !user.getValistr().equals((String) obj)) {
			request.setAttribute("vali_msg", "��֤��������󣡣�");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
		//���ת��֮�����û�д���ִ����return
			return;
		}
		// -----�ж��ظ��ύ
		// 1.���������л�ȡtoken
		String token1 = request.getParameter("token");
		// 2.��session�л�ȡtoken
		Object tkObj = request.getSession().getAttribute("token");
		if (tkObj != null && token1.equals((String) tkObj)) {
			// ��һ�����
			request.getSession().removeAttribute("token");
			// request.getSession().setAttribute("token", "");
		} else {
			// �ǵ�һ�����
			throw new RuntimeException("�벻Ҫ�ظ��ύ��");
		}

		// 4.����ҵ�����
		//UserService userService = new UserServiceImpl();
		/*��Сѧ��UserService userService = UserServiceFactory.getFactory()
				.getInstance();*/
		//(����)
		/*
		 * UserService userService = (UserService)BasicFactory.getFactory()
				.getInstance("UserService");
		 * */
		//����
		/*
		 * UserService userService = (UserService)BasicFactory.getFactory()
				.getInstance(UserService.class);
		 * */
		 UserService userService = BasicFactory.getFactory()
				.getInstance(UserService.class);
		// 5.����ҵ����ע�᷽��
			user.setPassword(MD5Utils.md5(user.getPassword()));
		try {
			boolean result = userService.regist(user);
			if (result) {//ע��ɹ�
				response.getWriter().write(
						"<h1 style='color:red;text-align:center'>"
								+ "��ϲ��ע��ɹ���3���������ҳ����</h1>");
				response.setHeader("Refresh",
						"3;url=" + request.getContextPath() + "/index.jsp");
			} else {
				response.getWriter().write(
						"<h1 style='color:red;text-align:center'>"
								+ "ϵͳ����������ע�ᣡ��</h1>");
				response.setHeader("Refresh",
						"3;url=" + request.getContextPath() + "/regist.jsp");
			}
		} catch (MsgException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
