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
 * 处理用户请求
 * */
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* 控制浏览器不要缓存图片 */
		/* 绘制一张验证码图片发送给浏览器 */
		// 获取验证码文本
		// 1.处理响应正文和请求参数乱码
		//response.setContentType("text/html;charset=utf-8");
		//request.setCharacterEncoding("utf-8");
		// 2.获取数据请求参数（用户的注册信息）
		User user = new User();
		// 页面中的表单项name属性的值一定要和User类中属性名一样
		try {
			//TODO 
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		// 3.数据校验
		try {
			user.check();
		} catch (MsgException e1) {
			// 跳转到注册页面
			request.setAttribute("msg", e1.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
		}

		// -----判断验证码是否正确
		Object obj = request.getSession().getAttribute("code");
		if (obj == null || !user.getValistr().equals((String) obj)) {
			request.setAttribute("vali_msg", "验证码输入错误！！");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
		//如果转发之后后续没有代码执行用return
			return;
		}
		// -----判断重复提交
		// 1.从隐藏域中获取token
		String token1 = request.getParameter("token");
		// 2.从session中获取token
		Object tkObj = request.getSession().getAttribute("token");
		if (tkObj != null && token1.equals((String) tkObj)) {
			// 第一次添加
			request.getSession().removeAttribute("token");
			// request.getSession().setAttribute("token", "");
		} else {
			// 非第一次添加
			throw new RuntimeException("请不要重复提交！");
		}

		// 4.创建业务对象
		//UserService userService = new UserServiceImpl();
		/*（小学）UserService userService = UserServiceFactory.getFactory()
				.getInstance();*/
		//(初中)
		/*
		 * UserService userService = (UserService)BasicFactory.getFactory()
				.getInstance("UserService");
		 * */
		//高中
		/*
		 * UserService userService = (UserService)BasicFactory.getFactory()
				.getInstance(UserService.class);
		 * */
		 UserService userService = BasicFactory.getFactory()
				.getInstance(UserService.class);
		// 5.调用业务层的注册方法
			user.setPassword(MD5Utils.md5(user.getPassword()));
		try {
			boolean result = userService.regist(user);
			if (result) {//注册成功
				response.getWriter().write(
						"<h1 style='color:red;text-align:center'>"
								+ "恭喜您注册成功，3秒后会调到首页！！</h1>");
				response.setHeader("Refresh",
						"3;url=" + request.getContextPath() + "/index.jsp");
			} else {
				response.getWriter().write(
						"<h1 style='color:red;text-align:center'>"
								+ "系统错误，请重新注册！！</h1>");
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
