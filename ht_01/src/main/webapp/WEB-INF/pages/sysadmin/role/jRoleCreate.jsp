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
	<li id="save"><a href="#" onclick="formSubmit('tosave','_self');this.blur();">保存</a></li>
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
		<td>
		角色名称：</td>
		<td><input type="text" name="name" /></td>
	</tr>
	<tr>
		<td>角色序号：</td>
		<td><input type="text" name="orderNo" /></td>
	</tr>
	<tr>
		<td>角色描述：</td>
		<td>
		<input type="text" name="remarks">
		</td>
	</tr>
	
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

