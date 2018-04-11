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
		//1.判断用户是否登录
		//1.1 从session中获取loginUser
		Object userObj=request.getSession().getAttribute("loginUser");
		if (userObj==null) {//未登录
			request.setAttribute("msg", "添加订单请先登录！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//2.从session中获取cart判断是否为null
		//2.1
		Object cartObj = request.getSession().getAttribute("cart");
		//2.2判断cartObj是否为null
		if (cartObj==null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
		//3.创建order类的对象,并为属性赋值
		Order order=new Order();
		//3.2为对象属性赋值
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setPaystate(0);//0表达未支付，1表示已支付
		order.setReceiverinfo(request.getParameter("receiverinfo"));
		order.setUser_id(((User)userObj).getId());
		//4.便利购物车中的商品信息，封装list<OrderItem>,和计算money
		//4.1定义double类型的money
		double money=0;
		//4.2定义集合List<OrderItem>
		List<OrderItem> oiList =new ArrayList<OrderItem>();
		//4.3强制类型转换
		Map<Product,Integer> cart =(Map<Product,Integer>)cartObj;
		for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
			//4.5创建orderItem对象
			OrderItem item =new OrderItem();
			//4.6为item属性赋值
			item.setOrder_id(order.getId());
			item.setProduct_id(entry.getKey().getId());
			item.setBuynum(entry.getValue());
			//4.7价格小计
			money+=entry.getKey().getPrice()*entry.getValue();
			//4.8 item 添加到list
			oiList.add(item);
		}
		//5.为order设置money属性的值
		order.setMoney(money);
		//6.调用业务层添加订单方法
		OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
		try {
			//6.2
			os.addOrder(order,oiList);
			//7.添加成功
			//7.1
			cart.clear();
			request.getSession().removeAttribute("cart");
			//7.2
			response.getWriter().write("订单添加成功！3秒后跳转！");
			response.setHeader("Refresh", "3;url="+request.getContextPath()+"/OrderListServlet");
		} catch (Exception e) {
			//7添加失败
			//7.1
			request.setAttribute("msg", e.getMessage());
			//7.2回购物车
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
