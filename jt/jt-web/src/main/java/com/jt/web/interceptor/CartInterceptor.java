package com.jt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.CookieUtils;
import com.jt.web.pojo.User;
import com.jt.web.threadLocal.UserThreadLocal;
/**
 * 拦截器继承接口类 拦截符合扫描规则的controller请求
 * @author Administrator
 *
 */
public class CartInterceptor implements HandlerInterceptor {
	@Autowired
	private HttpClientService httpClientService;
	
	private static final ObjectMapper MAPPER =new ObjectMapper();
	/*
	 * 前拦截操作
	 * 转发到真正的controller之前的操作
	 * 返回值是布尔类型
	 * 如果返回TRUE拦截器放行  返回false不放行 刷新页面，重定向，转发
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*步骤
		 * 1.从cookie中获取ticket
		 * 2.去访问sso系统获取redies的user的json
		 * 3.json转化成对象user
		 * 4.userId的共享数据
		 * 5.使用UserThreadLocal自定义的threadLocal的子类 专门控制user对象的安全
		 */
		String cookieName="JT_TICKET";
		String ticket=CookieUtils.getCookieValue(request, cookieName);
		if (StringUtils.isNotEmpty(ticket)) {
			String url="http://sso.jt.com/user/query/"+ticket;
			String jsonData=httpClientService.doGet(url, "utf-8");
			JsonNode userJsonNode=MAPPER.readTree(jsonData);
			String curUserJson=userJsonNode.get("data").asText();
			User user = MAPPER.readValue(curUserJson, User.class);
			UserThreadLocal.set(user);
			return true;//表示拦截器放行
		}
		//没有登录信息的时候
		UserThreadLocal.set(null);
		//转发到登录页面
		response.sendRedirect("/user/login.html");
		return false;
	}
	/*
	 * 后拦截操作
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	/*
	 * 完成后拦截
	 * model比如可以对model里的所有key做拦截，修改
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
