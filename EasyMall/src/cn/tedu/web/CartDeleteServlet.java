package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;

public class CartDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收参数
		String id=request.getParameter("id");
		//从session中获取cart
		Object cartObj = request.getSession().getAttribute("cart");
		//3.判断cartObj是否为null
		if (cartObj==null) {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else {//不为null
			//4.执行删除商品的操作
			Map<Product,Integer> cart = (Map<Product,Integer>)cartObj;
			Product prod=new Product();
			prod.setId(id);
			cart.remove(prod);
			if(cart.size()<=0){
				request.getSession().removeAttribute("cart");
			}
			//5.跳转到cart.jsp
			response.sendRedirect(request.getContextPath()+"/cart.jsp");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
