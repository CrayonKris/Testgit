package cn.tedu.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.tedu.entity.User;

public class RoleFilter implements Filter {

	private List<String> userList;
	private List<String> adminList;
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		String uri=req.getRequestURI();
		if (userList.contains(uri)||adminList.contains(uri)){
			//需要权限
			Object userObj=req.getSession().getAttribute("loginUser");
			if (userObj==null) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				String role=((User)userObj).getRole();
				if ("user".equals(role)&&userList.contains(uri)) {
					chain.doFilter(request, response);
				}else if("admin".equals(role)&&adminList.contains(uri)){
					chain.doFilter(request, response);
				}else{
					response.getWriter().write("权限不足！！");
				}
			}
		}else{
			//不需要权限
			chain.doFilter(request,response);
		}
	}

	public void init(FilterConfig config) throws ServletException {

		userList=new ArrayList<String>();
		adminList=new ArrayList<String>();
		try {
		String path=config.getServletContext().getRealPath("/WEB-INF/user.txt");
		BufferedReader read = new BufferedReader(new FileReader(path));
			String line=null;
			while ((line=read.readLine())!=null) {
				userList.add(line);
			}
			path=config.getServletContext().getRealPath("/WEB-INF/admin.txt");
			read=new BufferedReader(new FileReader(path));
			while ((line=read.readLine())!=null) {
				adminList.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
