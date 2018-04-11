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
		//1.�ж��û��Ƿ��¼
		//1.1��session�л�ȡ��¼�û���Ϣ
		Object userObj=request.getSession().getAttribute("loginUser");
		//2.�ж��Ƿ��¼
		if (userObj==null) {//δ��¼
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		//2.��½�ˣ���ȡID
		int userId=((User)userObj).getId();
		//3.����ҵ�����
		OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
		//3.2����ҵ���ѯ����
		List<OrderInfo> list=os.findOrderInfosByUid(userId);
		//4.����ѯ������浽request��
		request.setAttribute("list", list);
		//5.ת���������б�ҳ��
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
