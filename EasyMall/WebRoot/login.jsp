<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${app}/css/login.css"/>
		<title>EasyMall欢迎您登陆</title>
		
		<script type="text/javascript" src="${app }/js/jquery-1.4.2.js"></script>
		<script type="text/javascript" >
		$(function(){
		//获取用户名对应的输入框
		$ipt=$("input[name=username]")[0];
		//转码
		var unval=decodeURI($ipt.val());
		//修改输入框的值
		$ipt.val(unval);
		});
		/* js的写法完成转码 */
		/* window.onload=function(){
		var ipt=document.getElementsByName("username")[0];
		var val=decodeURI(ipt.value);
		ipt.value=val;
		
		} */
		
		</script>
		
	</head>
	<body>
		<h1>欢迎登陆EasyMall</h1>
		<form action="${app}/servlet/LoginServlet" method="POST">
			<table>
				<!-- 当用户登录时，获取请求中带过来的用户名
				并赋值给用户名输入框完成记住用户名的操作 -->
				
				<%-- <%
				Cookie[] cs=request.getCookies();
				String username="";
				if(cs!=null){
					for(Cookie c:cs){
					if("remname".equals(c.getName())){
					username=c.getValue();
					//对用户名进行url解码
					username=URLDecoder.decode(username, "utf-8");
						}
					}
				}
				 %> --%>
				 <tr>
				 	<td colspan="2" style="color:red;text-align:center">
				 	${msg }
				 	</td>
				 </tr>
				<tr>
					<td class="tdx">用户名：</td>
					<td><input type="text" name="username"
					value="${cookie.remname.value }"/></td>
				</tr>
				<tr>
					<td class="tdx">密&nbsp;&nbsp;码：</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="remname" value="true"
						${empty cookie.remname?"":"checked='checked'" }
						/>记住用户名
						<input type="checkbox" name="autologin" value="true"/>30天内自动登陆
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="登 陆"/>
					</td>
				</tr>
			</table>
		</form>		
	</body>
</html>

