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
	<tr>
		<td>部门名称：</td>
		<td>${dept.deptName }</td>
	</tr>
	<tr>
		<td>部门ID：</td>
		<td>${dept.deptId }</td>
	</tr>
	<tr>
		<td>上级部门：</td>
		<td>
		${dept.parentDept.deptName }
		</td>
	</tr>
	<tr>
		<td>部门状态：</td>
		<td>
		<c:if test="${dept.state==1 }">启用</c:if>
		<c:if test="${dept.state==0 }">停用</c:if>
		</td>
	</tr>

</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

