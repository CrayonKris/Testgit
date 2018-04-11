package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class ProdListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收参数
		String nameStr =request.getParameter("name");
		String cateStr = request.getParameter("category");
		String minpriceStr = request.getParameter("minprice");
		String maxpriceStr = request.getParameter("maxprice");
		//2.参数为null的处理
		//2.1定义四个变量，分别给默认值
		String name="";
		String cate="";
		Double min=null;
		Double max=null;
		//2.2处理为null
		//String :判断接收参数有内容去掉空格后赋值
		if (nameStr!=null&&!"".equals(nameStr)) {
			name=nameStr.trim();
		}
		if (cateStr!=null&&!"".equals(cateStr)) {
			cate=cateStr.trim();
		}
		//Double :判断接收参数有内容，去掉空格类型转换后再赋值
		if (minpriceStr!=null&&!"".equals(minpriceStr)) {
			min=Double.parseDouble(minpriceStr);
		}
		if (maxpriceStr!=null&&!"".equals(maxpriceStr)) {
			max=Double.parseDouble(maxpriceStr);
		}
		//3.创建业务层对象
		ProdService ps = BasicFactory.getFactory()
				.getInstance(ProdService.class);
		//4.调用根据查询条件查全部符合条件的商品
		List<Product> list = ps.findAllByKey(name,cate,min,max);
		//5.将list保存到request作用域中
		request.setAttribute("list", list);
		//6.将四个参数保存到request->实现查询条件的回显
		request.setAttribute("name", name);
		request.setAttribute("cate", cate);
		request.setAttribute("min", min);
		request.setAttribute("max", max);
		//7.转发到pord_list.jsp中
		request.getRequestDispatcher("/prod_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
