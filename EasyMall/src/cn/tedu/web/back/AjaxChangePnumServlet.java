package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class AjaxChangePnumServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收参数
		String id=request.getParameter("id");
		int pnum=Integer.parseInt(request.getParameter("newPnum"));
		//2.创建业务对象
		ProdService ps=BasicFactory.getFactory()
				.getInstance(ProdService.class);
		//3.调用业务修改
		boolean result = ps.changePnum(id,pnum);
		//4.输出结果
		response.getWriter().write(result+"");
	}

}
