package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;

public class CartEditServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收参数
		String id= request.getParameter("id");
		int buynum=Integer.parseInt(request.getParameter("newBuyNum"));
		//2.从session获取cat
		Object cartObj=request.getSession().getAttribute("cart");
		//3.判断catObj是否为null
		if (cartObj==null) {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else{//不为空说明session还有效
			//4.1强制类型转换
			Map<Product,Integer> cart =(Map<Product,Integer>)cartObj;
			//4.2创建product对象
			Product prod=new Product();
			prod.setId(id);
			//5.执行修改操作
			cart.put(prod, buynum);
			//6.跳转到cart.jsp
			response.sendRedirect(request.getContextPath()+"/cart.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
