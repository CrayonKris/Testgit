package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class BackProdDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收参数
		String id=request.getParameter("id");
		//2.创建业务对象
		ProdService ps = BasicFactory.getFactory()
				.getInstance(ProdService.class);
		//3.调用删除方法
		boolean result = ps.deleteProdById(id);
		//4.根据返回结果设置提示信息
		if(result){
			response.getWriter().write("删除成功！！");
		}else{
			response.getWriter().write("删除失败！！");
		}
		//5.设置跳转
		response.setHeader("refresh", "3;url="+
				request.getContextPath()+"/back/BackProductListServlet");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
