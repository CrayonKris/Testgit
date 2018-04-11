package cn.tedu.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.tedu.entity.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class AutoLoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//1、判断当前是否登录，只有未登录才处理自动登录
		// 强制类型转换
		HttpServletRequest req = (HttpServletRequest) request;
		// 1.2获取session中的用户信息
		Object obj = req.getSession().getAttribute("loginUser");
		// 1.3判断是否为未登录
		if (obj == null) {
			// 2.获取所有cookie
			Cookie[] cks = req.getCookies();
			// 3.cks不为空时， 遍历
			if (cks != null) {
				// 遍历cks找到自动登录的cookie
				Cookie ck = null;
				for (int i = 0; i < cks.length; i++) {
					if ("autologin".equals(cks[i].getName())) {
						ck = cks[i];
						break;
					}
				}
				// 判断是否存在自动登录的cookie
				if (ck != null) {// 存在
					// 6.获取用户名和密码，并进行URL反向处理
					String up = URLDecoder.decode(ck.getValue(), "utf-8");
					// 7.进行拆分
					String username = up.split(",")[0];
					String password = up.split(",")[1];
					// 8.创建业务层对象
					UserService us = BasicFactory.getFactory().getInstance(
							UserService.class);
					// 9.调用登录的方法
					User user = us.login(username, password);
					// 10.用户名和密码正确实现登录
					if (user != null) {
						req.getSession().setAttribute("loginUser", user);
					}
				}

			}
		}
		// 放行
		chain.doFilter(request, response);

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
