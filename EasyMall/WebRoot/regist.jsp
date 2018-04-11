<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册EasyMall</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="<%= request.getContextPath()  %>/css/regist.css"/>
		<script src="<%= request.getContextPath()  %>/js/regist.js"></script>
		<script src="<%= request.getContextPath()  %>/js/jquery-1.4.2.js"></script>
		<script>
			/*利用AJAX校验用户名是否存在*/
			function checkUsername(thisObj){
			//1.获取用户名
			var username = thisObj.value;
			//2.检查用户名是否为空
			if ($.trim(username)=="") {
				setMsg("username","用户名不能为空！");
				return;
			}
			//3.检查用户名是否存在
			$("#username_msg").load(
			"<%= request.getContextPath() %>/servlet/AjaxCheckUsernameServlet",
			{"username" : username}
			);
			}
		</script>
	</head>
	<body>
		<form onsubmit="return checkForm()" 
		action="<%= request.getContextPath()  %>/servlet/RegistServlet" method="POST">
			<%//生成令牌
			String token = UUID.randomUUID().toString();
			//将token保存到session中
			session.setAttribute("token", token);
			 %>
			 <!-- 将生成的令牌通过隐藏域传给servlet -->
			 <input type = "hidden" name="token" value="<%= token%>">
			<h1>欢迎注册EasyMall</h1>
			<table>
				<tr>
					<td style="text-align:center;color:red" colspan=2>
					<!-- 如果校验失败，在此处获取提示消息提示错误原因 -->
					<%=
						request.getAttribute("msg")==null?"":
						request.getAttribute("msg")
					
					 %>
					</td>
				</tr>
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input type="text" name="username" onblur="checkUsername(this)" value="
						<%= request.getParameter("username")==null?"":
						request.getParameter("username")%>"/>
						<span id="username_msg"></span>
					</td>
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" name="password" 
						onblur="checkNull('password','密码不能为空')"
						value="<%= request.getParameter("password")==null?"":
						request.getParameter("password")%>"/>
						<span></span>

					</td>
				</tr>
				<tr>
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" name="password2" 
						onblur="checkPassword('password','两次密码不一致！')"
						value="<%= request.getParameter("password2")==null?"":
						request.getParameter("password2")%>"/>
						<span></span>

					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" name="nickname" 
						onblur="checkNull('nickname','昵称不能为空')"
						value="
						<%= request.getParameter("nicknanme")==null?"":
						request.getParameter("nickname")%>"/>
						<span></span>

					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" name="email" 
						onblur="checkNull('email','邮箱不能为空！！')"
						value="<%= request.getParameter("email")==null?"":
						request.getParameter("email")%>"
						/>
						<span></span>

					</td>
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input type="text" name="valistr" 
						onblur="checkNull('valistr','验证码不能为空')"/>
						<img onclick="changeImage(this)" src="<%= request.getContextPath() %>/servlet/ValiImageServlet" 
						width="" height="" alt="" />
						<span>
						<%= request.getAttribute("vali_msg")==null?"":
						(String)request.getAttribute("vali_msg")%></span>
					</td>
					<script>
						/*点击图片换一张*/
						function changeImage(thisobj){
						thisobj.src="<%= request.getContextPath() %>/servlet/ValiImageServlet?a="
						+new Date().getTime();
						}
					</script>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
