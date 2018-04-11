package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;
import cn.tedu.utils.DaoUtils;

public class OrderDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接受订单
		String oid=request.getParameter("id");
		//2.创建业务对象
		OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
		//3.调用业务层删除订单相关信息的方法
		try {
			os.deleteOrderByOid(oid);
			response.getWriter().write("恭喜您删除成功！！");
		} catch (MsgException me) {
			response.getWriter().write(me.getMessage());
		}
		//设置自动跳转
		response.setHeader("refresh", "3;url="+request.getContextPath()+"/OrderListServlet");
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
