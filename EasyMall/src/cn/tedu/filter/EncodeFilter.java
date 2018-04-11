package cn.tedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeFilter implements Filter {

	private String encode;

	public void init(FilterConfig config) throws ServletException {
		encode = config.getInitParameter("encode");

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html;charset=" + encode);
		chain.doFilter(new MyHttpSR((HttpServletRequest) request), response);

	}

	public void destroy() {

	}

	class MyHttpSR extends HttpServletRequestWrapper {

		private Map<String, String[]> map = null;
		// 被装饰的对象
		private HttpServletRequest request;

		public MyHttpSR(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		/**
		 * @return
		 */
		public Map<String, String[]> getParameterMap() {
			if (map == null) {
				// 判断提交方式是GET，POST,还是其他的五种
				if ("POST".equals(request.getMethod())) {
					try {
						// 处理乱码
						request.setCharacterEncoding(encode);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					map = request.getParameterMap();
					return map;
				} else if ("GET".equals(request.getMethod())) {
					// 获取提交的数据
					map = request.getParameterMap();
					// 遍历每一个表单项中的乱码
					for (Map.Entry<String, String[]> entry : map.entrySet()) {
						// 获取value值
						String[] vals = entry.getValue();
						// 遍历出数组中的乱码
						for (int i = 0; i < vals.length; i++) {
							try {
								vals[i] = new String(
										vals[i].getBytes("iso8859-1"), encode);
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					return map;
				} else {
					map = request.getParameterMap();
					return map;
				}
			} else {
				return map;
			}
		}

		@Override
		public String getParameter(String name) {
			String vals[] = getParameterValues(name);
			return vals != null ? vals[0] : "";
		}

		@Override
		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			return getParameterMap().get(name);
		}
	}

}
