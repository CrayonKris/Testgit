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
		//���ղ���
		String id=request.getParameter("id");
		int pnum=Integer.parseInt(request.getParameter("newPnum"));
		//2.����ҵ�����
		ProdService ps=BasicFactory.getFactory()
				.getInstance(ProdService.class);
		//3.����ҵ���޸�
		boolean result = ps.changePnum(id,pnum);
		//4.������
		response.getWriter().write(result+"");
	}

}
