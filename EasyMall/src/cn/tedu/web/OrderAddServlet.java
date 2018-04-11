package cn.tedu.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;
import cn.tedu.entity.Product;
import cn.tedu.entity.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderAddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.�ж��û��Ƿ��¼
		//1.1 ��session�л�ȡloginUser
		Object userObj=request.getSession().getAttribute("loginUser");
		if (userObj==null) {//δ��¼
			request.setAttribute("msg", "��Ӷ������ȵ�¼��");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//2.��session�л�ȡcart�ж��Ƿ�Ϊnull
		//2.1
		Object cartObj = request.getSession().getAttribute("cart");
		//2.2�ж�cartObj�Ƿ�Ϊnull
		if (cartObj==null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
		//3.����order��Ķ���,��Ϊ���Ը�ֵ
		Order order=new Order();
		//3.2Ϊ�������Ը�ֵ
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setPaystate(0);//0���δ֧����1��ʾ��֧��
		order.setReceiverinfo(request.getParameter("receiverinfo"));
		order.setUser_id(((User)userObj).getId());
		//4.�������ﳵ�е���Ʒ��Ϣ����װlist<OrderItem>,�ͼ���money
		//4.1����double���͵�money
		double money=0;
		//4.2���弯��List<OrderItem>
		List<OrderItem> oiList =new ArrayList<OrderItem>();
		//4.3ǿ������ת��
		Map<Product,Integer> cart =(Map<Product,Integer>)cartObj;
		for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
			//4.5����orderItem����
			OrderItem item =new OrderItem();
			//4.6Ϊitem���Ը�ֵ
			item.setOrder_id(order.getId());
			item.setProduct_id(entry.getKey().getId());
			item.setBuynum(entry.getValue());
			//4.7�۸�С��
			money+=entry.getKey().getPrice()*entry.getValue();
			//4.8 item ��ӵ�list
			oiList.add(item);
		}
		//5.Ϊorder����money���Ե�ֵ
		order.setMoney(money);
		//6.����ҵ�����Ӷ�������
		OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
		try {
			//6.2
			os.addOrder(order,oiList);
			//7.��ӳɹ�
			//7.1
			cart.clear();
			request.getSession().removeAttribute("cart");
			//7.2
			response.getWriter().write("������ӳɹ���3�����ת��");
			response.setHeader("Refresh", "3;url="+request.getContextPath()+"/OrderListServlet");
		} catch (Exception e) {
			//7���ʧ��
			//7.1
			request.setAttribute("msg", e.getMessage());
			//7.2�ع��ﳵ
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
