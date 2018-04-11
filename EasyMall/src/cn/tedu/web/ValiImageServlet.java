package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.utils.VerifyCode;

public class ValiImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//控制浏览器不要缓存验证码图片
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		//绘制一张验证码图片发送给浏览器
		VerifyCode vc=new VerifyCode();
		vc.drawImage(response.getOutputStream());
		
		//获取验证码文本
		String valistr=vc.getCode();
		//将验证码字符串保存到session中，以便在注册的servlet中验证使用
		System.out.println("code:"+valistr);
		request.getSession().setAttribute("code",valistr);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
