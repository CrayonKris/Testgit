package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.OrderInfo;
import cn.tedu.entity.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.判断用户是否登录
		//1.1从session中获取登录用户信息
		Object userObj=request.getSession().getAttribute("loginUser");
		//2.判断是否登录
		if (userObj==null) {//未登录
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		//2.登陆了，获取ID
		int userId=((User)userObj).getId();
		//3.创建业务对象
		OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
		//3.2调用业务查询方法
		List<OrderInfo> list=os.findOrderInfosByUid(userId);
		//4.将查询结果保存到request中
		request.setAttribute("list", list);
		//5.转发到订单列表页面
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
