<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	
	<form >
	销售信息查询:   排序方式：<select><option>销售日期</option></select> <input type="button" value="提交">
	<table>
		<tr>
			<th>ID</th>
			<th>商品</th>
			<th>单价</th>
			<th>数量</th>
			<th>总价</th>
			<th>销售日期</th>
			<th>销售员</th>
		</tr>
		
			<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<c:forEach items="${list }" var="li">
				<tr>
					<td>${li.id }</td>
					<td>${li.price }</td>
					<td>${li.quantity }</td>
					<td>${li.totalPrice }</td>
					<td>${li.saleDate }</td>
					<td>${li.userId }</td>
					<td>${li.productId }</td>
				</tr>
			</c:forEach>
			
			
	</table>
	</form>
</body>
</html>