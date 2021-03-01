
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ page import="service.OrderDetailService" %>
 <%@ page import="entity.OrderDetail" %>

<html>
<head>
<style type="text/css">
.bg{
height: 750px;
	width: 100%;
	background: url(bg.jpg);
	overflow: hidden;
	background-attachment: fixed;
}
.bt{
color: red;
font-size:16px;
}
</style>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Lowin</title>
	<link rel="stylesheet" href="css/auth.css">
</head>

<body>
	<div class="lowin">
		<div class="lowin-brand">
			<img src="kodinger.jpg" alt="logo">
		</div>
		<div class="lowin-wrapper">
			<div class="lowin-box lowin-login">
				<div class="lowin-box-inner">
					  <h1>订单详情</h1>
 
  <%OrderDetailService orderDetailService=new OrderDetailService();
	OrderDetail book=new OrderDetail();
	book=orderDetailService.showOrderDetail(Integer.parseInt(request.getParameter("id")));
  
	 if(book!=null)
     {
%>
<td width="70%" valign="top">
<table>
<tr>
<td rowspan="4"><img id='previewImg' src="<%=book.getPicture()%>" width="200" height="160"/></td>


</tr>

<tr>
<td>书籍编号：<%=book.getBookid()%></td>
</tr> 
<tr>
<td><B>书籍名称：<%=book.getName()%></B></td> 
</tr>
<tr>
<td>价格：￥<%=book.getPrice()%></td>
</tr> 
<tr>
<td>数量：<%=book.getBuynum()%></td>
</tr>
<tr>
<td>流水号：<%=book.getId()%></td>
</tr> 
<tr>
<td>总价：￥<%=book.getTotalprice()%></td>
</tr> 
<tr>
<td>提交时间：<%=book.getDate()%></td>
</tr> 

<tr>

</td>
<%
}
%>
				</div>
			</div>

		
	
		<footer class="lowin-footer">
			
		</footer>
	</div>
</body>
</html>