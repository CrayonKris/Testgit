package cn.tedu.web.back;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.SaleInfo;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class DownloadSILServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.创建业务层对象
		OrderService os=BasicFactory.getFactory().
				getInstance(OrderService.class);
		List<SaleInfo> list=os.findSaleInfos();
		StringBuffer buffer=new StringBuffer("商品id,商品名称," +
				"销售数量\n");
		//遍历
		for (SaleInfo si : list) {
			buffer.append(si.getProd_id()).append(",")
			.append(si.getProd_name()).append(",")
			.append(si.getSale_num()).append("\n");
		}
		//
		String fname="sale_list"+getTimeStamp()+".csv";
		response.setHeader("Content-Disposition", "attachment;" +
				"filename="+fname);
		//处理乱码
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(buffer.toString());

	}
	private String getTimeStamp(){
		SimpleDateFormat sdf=new SimpleDateFormat();
		return sdf.format(new Date());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
