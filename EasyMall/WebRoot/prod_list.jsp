<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link href="${app}/css/prodList.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="/_head.jsp" %>
	<div id="content">
		<div id="search_div">
			<form method="post" action="${app }/servlet/ProdListServlet">
				<span class="input_span">商品名：<input type="text" name="name" value="${name }" />
				</span> 
				<span class="input_span">商品种类：<input type="text"
					name="category" value="${cate }"/>
				</span> <span class="input_span">商品价格区间：<input type="text"
					name="minprice" value="${min }"/> - <input type="text" name="maxprice" value="${max }"/>
				</span> <input type="submit" value="查询">
			</form>
		</div>
		<div id="prod_content">
			<c:forEach items="${requestScope.list }" var="prod">
				<div id="prod_div">
				<a href="${app }/servlet/ProdInfoServlet?id=${prod.id}">
					<img src="${app }/servlet/ProdImgServlet?imgurl=${prod.imgurl}">
					</a>
					<div id="prod_name_div"><a href="${app }/servlet/ProdInfoServlet?id=${prod.id}">${prod.name }</a></div>
					<div id="prod_price_div">￥${prod.price }元</div>
					<div>
						<div id="gotocart_div">
							<a href="${app }/CartAddServlet?id=${prod.id}&buynum=1">加入购物车</a>
						</div>
						<div id="say_div">库存:${prod.pnum }</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div style="clear: both"></div>
	</div>
	<%@include file="/_foot.jsp" %>
</body>
</html>
