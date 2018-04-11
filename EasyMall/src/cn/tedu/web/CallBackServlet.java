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
		//���յ�����֧��ƽ̨���صĲ���
		String p1_MerId=request.getParameter("p1_MerId");
		String r0_Cmd=request.getParameter("r0_Cmd");
		String r1_Code=request.getParameter("r1_Code");
		//�ױ�֧��ƽ̨�ϵĽ��׵���ˮ��
		String r2_TrxId=request.getParameter("r2_TrxId");
		String r3_Amt=request.getParameter("r3_Amt");
		String r4_Cur=request.getParameter("r4_Cur");
		String r5_Pid=request.getParameter("r5_Pid");
		//����ID
		String r6_Order=request.getParameter("r6_Order");
		String r7_Uid=request.getParameter("r7_Uid");
		String r8_MP=request.getParameter("r8_MP");
		//���׽���������ͣ�1.��ʾ�ض��� 2��ʾ��Ե�ͨѶ
		String r9_BType=request.getParameter("r9_BType");
		String rb_BankId=request.getParameter("rb_BankId");
		String ro_BankOrderId=request.getParameter("ro_BankOrderId");
		String rq_CardNo=request.getParameter("rq_CardNo");
		String ru_Trxtime=request.getParameter("ru_Trxtime");
		String hmac=request.getParameter("hmac");
		//��֤�����Ƿ񱻴۸�
		boolean result=PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, PropPayUtils.getProperty("keyValue"));
		if (!result) {
			System.out.println("���ݱ��۸��ˣ���");
		} else {//δ���۸�
			//�ж��ض�������Ļ��ǵ�Ե�ͨ�Ź����ģ�����
			if ("1".equals(r9_BType)) {
				//Ϊ���ܿ���֧��״̬�ĸı�Ч������ʽ����ʱ�ܽ�����Ե�֪ͨ��Ҫɾ��
				OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
				//�޸Ķ�����֧��״̬
				os.changePaystate(r6_Order,1);
				//�ض��������
				response.getWriter().write("����֧�������ѱ�����֧�������ȴ���һ����֪ͨ");
			}else if("2".equals(r9_BType)&&"1".equals(r1_Code)){
				//��Ե�֪ͨ
				OrderService os=BasicFactory.getFactory().getInstance(OrderService.class);
				//�޸Ķ�����֧��״̬
				os.changePaystate(r6_Order,1);
				//��Ӧ��������֧��ƽ̨success
				response.getWriter().write("success");
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
