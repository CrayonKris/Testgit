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
		//���ܶ���
		String oid=request.getParameter("id");
		//2.����ҵ�����
		OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
		//3.����ҵ���ɾ�����������Ϣ�ķ���
		try {
			os.deleteOrderByOid(oid);
			response.getWriter().write("��ϲ��ɾ���ɹ�����");
		} catch (MsgException me) {
			response.getWriter().write(me.getMessage());
		}
		//�����Զ���ת
		response.setHeader("refresh", "3;url="+request.getContextPath()+"/OrderListServlet");
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
