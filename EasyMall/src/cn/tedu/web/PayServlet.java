package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Order;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;
import cn.tedu.utils.PaymentUtil;
import cn.tedu.utils.PropPayUtils;

public class PayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//准备参数
		String p0_Cmd="Buy";//业务类型
		String p1_MerId=PropPayUtils.getProperty("p1_MerId");//商户编号
		String p2_Order=request.getParameter("orderid");
		//订单金额---测试时使用
		String p3_Amt="0.01";
		//正式使用
		/*OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
		Order order=os.findOrderByOid(p2_Order);
		String p3_Amt=order.getMoney()+"";*/
		String p4_Cur = "CNY";//RMB
		String p5_Pid="";//商品名称
		String p6_Pcat="";
		String p7_Pdesc="";
		//回调的url地址
		String p8_Url=PropPayUtils.getProperty("responseURL");
		String p9_SAF="";//收货地址
		String pa_MP="";
		//支付通道编码
		String pd_FrpId=request.getParameter("pd_FrpId");
		String pr_NeedResponse="1";
		//签名数据
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, 
				p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, 
				pa_MP, pd_FrpId, pr_NeedResponse, PropPayUtils.getProperty("keyValue"));
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat", p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);
		//3.转发
		request.getRequestDispatcher("/confirm.jsp").forward(request, response);
		
		
	}

}
