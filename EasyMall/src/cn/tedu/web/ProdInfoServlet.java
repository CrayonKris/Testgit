package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class ProdInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收参数
		String id=request.getParameter("id");
		//2.创建业务层对象
		ProdService ps=BasicFactory.getFactory().getInstance(ProdService.class);
		//3.调用根据ID查询商品详情的方法
		Product prod=ps.findProdById(id);
		//4.将prod保存到request中
		request.setAttribute("prod", prod);
		//5.转到prod_list.jsp中
		request.getRequestDispatcher("/prod_info.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
