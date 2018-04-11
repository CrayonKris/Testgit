package cn.tedu.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class CartAddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收参数
		String id=request.getParameter("id");
		int buynum=Integer.parseInt(request.getParameter("buynum"));
		//2.调业务层方法查询对应的商品对象
		ProdService ps=BasicFactory.getFactory().getInstance(ProdService.class);
		//调用业务层对象
		Product prod = ps.findProdById(id);
		//3.从session中获取cart对象
		Object cartObj=request.getSession().getAttribute("cart");
		//4.判断cartObj是否为null（无论是否为空执行后保证session中 存在购物车对应的map对象）
		Map<Product,Integer> cart =null;
		if (cartObj==null) {//“首次”购买商品---首次是购买时购物车是空的
			cart = new HashMap<Product,Integer>();
			request.getSession().setAttribute("cart", cart);
		}else{//非“首次”购买
			cart = (Map<Product,Integer>)cartObj;
		}
		//5.将prod和buynum保存到cart中
		if (cart.containsKey(prod)) {//当前商品在购物车存在
			cart.put(prod, cart.get(prod)+buynum);
		}else {//当前商品未购买过
			cart.put(prod, buynum);
		}
		//6.跳转到cart.jsp
		response.sendRedirect(request.getContextPath()+"/cart.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
