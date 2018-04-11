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
	<li id="update"><a href="#" onclick="formSubmit('update','_self');this.blur();">修改</a></li>
	<li id="back"><a href="#" onclick="window.history.back();">返回</a></li>

</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    更新部门
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr>
		<td>部门名称：</td>
		<td><input type="text" name="deptName" value="${dept.deptName }" /></td>
	</tr>
	<tr>
		<td>部门ID：</td>
		<td><input type="text" name="deptId" value="${dept.deptId }" /></td>
	</tr>
	<tr>
		<td>上级部门：</td>
		<td>
		<select name="parentDept.deptId">
		<option>-无上级-</option>
		<!-- 先获取页面返回来的部门列表 然后遍历 -->
		<c:forEach items="${depts }" var="d">
		<!-- 判断当前遍历的部门id是否和我所查看的部门id的上级相等 -->
		
			<option value="${d.deptId }" <c:if test="${dept.parentDept.deptId== d.deptId }">
		selected="selected" </c:if> >${d.deptName } </option>
		</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<td>部门状态：</td>
			<!-- 判断当前部门状态是0还是1 来决定那个radio 选中 -->
		<td><input type="radio" name="state" value="1" <c:if test="${dept.state==1 }">checked="checked"</c:if> />启用
		<input type="radio" name="state"  value="0" <c:if test="${dept.state==0 }">checked="checked"</c:if> />关闭
		</td>
	</tr>

</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

