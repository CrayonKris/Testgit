<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>修改模块</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="save"><a href="#" onclick="formSubmit('update','_self');this.blur();">更新</a></li>
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
	<td><input type="hidden" name="moduleId" value="${module.moduleId }"></td>
	</tr>
	<tr>
		<td>模块名称：</td>
		<td><input type="text" name="name" value="${module.name }" /></td>
	</tr>
	<tr>
		<td>序号：</td>
		<td><input type="text" name="orderNo" value="${module.orderNo }" /></td>
	</tr>
	<tr>
		<td>上级模块：</td>
		<td>
		<!-- 回显上级 -->
		<select name="parentmodule.moduleId">
		<option>--无上级--</option>
		<!-- 先获取页面返回来的部门列表 然后遍历 -->
		<c:forEach items="${modules }" var="m">
		
		<option value="${m.moduleId }"
		<c:if test="${m.moduleId==module.parentmodule.moduleId}">selected="selected"
		</c:if>>${m.name }</option>
		</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<td>类型：</td>
		<td>
		<select name="ctype">
		
		<option value="1" <c:if test="${module.ctype==1 }">selected="selected"
		</c:if>>主菜单</option>
		<option value="2" <c:if test="${module.ctype==2 }">selected="selected"
		</c:if>>侧拉菜单</option>
		<option value="3" <c:if test="${module.ctype==3 }">selected="selected"
		</c:if>>按钮</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>部门状态：</td>
		<td>
		<input type="radio" name="state" value="1" <c:if test="${module.state==1 }">checked="checked"</c:if> />启用
		<input type="radio" name="state" value="0"  <c:if test="${module.state==0 }">checked="checked"</c:if> />关闭
		</td>
	</tr>
	<tr>
		<td>模块介绍：</td>
		<td><input type="text" name="remark" value="${module.remark }" /></td>
	</tr>

</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

