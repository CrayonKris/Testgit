<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>部门列表</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="back"><a href="#" onclick="window.history.back();">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    新增部门
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr><td><input name="userId" value="${user.userId } " type="hidden" ></td></tr>
	<tr>
	
		<td>用户名：</td>
		<td>${user.username }</td>
		<td>密码：</td>
		<td>${user.password }</td>
	</tr>
	<tr>
		<td>姓名：</td>
		<td>${user.userInfo.name }</td>
		<td>身份证号：</td>
		<td>${user.userInfo.cardNo }</td>
	</tr>
	<tr>
		<td>性别：</td>
		<td>${user.userInfo.gender }</td>
		<td>工资：</td>
		<td>${user.userInfo.salary }</td>
	</tr>
	 	<tr>
		<td>入职日期：</td>
		
		<td><fmt:formatDate value="${user.userInfo.joinDate }" pattern="yyyy-MM-dd"/></td>
		<td>生日：</td>
		<td><fmt:formatDate value="${user.userInfo.birthday }" pattern="yyyy-MM-dd"/></td>
	</tr>
	</tr>
	 	<tr>
		<td>岗位描述：</td>
		<td>${user.userInfo.station }</td>
		<td>用户级别：</td>
		<td>	<select name="userInfo.userLevel">
		
		<option value="4" <c:if test="${user.userInfo.userLevel==4 }">selected="selected"</c:if> >-普通员工-</option>
		<option value="3" <c:if test="${user.userInfo.userLevel==3 }">selected="selected"</c:if> >-部门经理-</option>
		<option value="2" <c:if test="${user.userInfo.userLevel==2 }">selected="selected"</c:if> >-副总-</option>
		<option value="1" <c:if test="${user.userInfo.userLevel==1 }">selected="selected"</c:if> >-总经理-</option>
		<option value="0" <c:if test="${user.userInfo.userLevel==0 }">selected="selected"</c:if> >-超级管理员-</option>
		</select></td>
	</tr>
	
	<tr>
		<td>所属部门：</td>
		<td>
		${user.dept.deptName }
		</td>
		<td>直属领导：</td>
		<td>
		${user.userInfo.manager.name }
		</td>
	</tr>
	<tr>
		<td>员工状态：</td>
		
		<td>
		<c:if test="${user.state==1 }">启用</c:if>
		<c:if test="${user.state==0 }">关闭</c:if>
		</td>
		
	</tr>
	
	<tr>
		<td>备注信息：</td>
		<td colspan="3">
		<textarea    name="userInfo.remark"  style="width:100%;height:100px">${user.userInfo.remark }</textarea>
 		</td>
	</tr>
	
	

</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

