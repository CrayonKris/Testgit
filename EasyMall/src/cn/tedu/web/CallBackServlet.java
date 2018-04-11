package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;
import cn.tedu.utils.PaymentUtil;
import cn.tedu.utils.PropPayUtils;

public class CallBackServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收第三方支付平台传回的参数
		String p1_MerId=request.getParameter("p1_MerId");
		String r0_Cmd=request.getParameter("r0_Cmd");
		String r1_Code=request.getParameter("r1_Code");
		//易宝支付平台上的交易的流水号
		String r2_TrxId=request.getParameter("r2_TrxId");
		String r3_Amt=request.getParameter("r3_Amt");
		String r4_Cur=request.getParameter("r4_Cur");
		String r5_Pid=request.getParameter("r5_Pid");
		//订单ID
		String r6_Order=request.getParameter("r6_Order");
		String r7_Uid=request.getParameter("r7_Uid");
		String r8_MP=request.getParameter("r8_MP");
		//交易结果返回类型：1.表示重定向 2表示点对点通讯
		String r9_BType=request.getParameter("r9_BType");
		String rb_BankId=request.getParameter("rb_BankId");
		String ro_BankOrderId=request.getParameter("ro_BankOrderId");
		String rq_CardNo=request.getParameter("rq_CardNo");
		String ru_Trxtime=request.getParameter("ru_Trxtime");
		String hmac=request.getParameter("hmac");
		//验证数据是否被篡改
		boolean result=PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, PropPayUtils.getProperty("keyValue"));
		if (!result) {
			System.out.println("数据被篡改了！！");
		} else {//未被篡改
			//判断重定向过来的还是点对点通信过来的？？？
			if ("1".equals(r9_BType)) {
				//为了能看到支付状态的改变效果，正式部署时能进到点对点通知，要删掉
				OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
				//修改订单的支付状态
				os.changePaystate(r6_Order,1);
				//重定向过来的
				response.getWriter().write("您的支付请求已被受理，支付结果需等待进一步的通知");
			}else if("2".equals(r9_BType)&&"1".equals(r1_Code)){
				//点对点通知
				OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
				//修改订单的支付状态
				os.changePaystate(r6_Order,1);
				//响应给第三方支付平台success
				response.getWriter().write("success");
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
