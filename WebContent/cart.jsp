<%@page import="service.CartService"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="entity.Carts" %>
<%@ page import="entity.books" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>My JSP 'cart.jsp' starting page</title>
 	

    <link type="text/css" rel="stylesheet" href="css/style1.css" />
    <script language="javascript">
	    function delcfm() {
	        if (!confirm("确认要删除？")) {
	            window.event.returnValue = false;
	        }
	    }
   </script>
  </head>
  
  <body>
   <h1>我的购物车</h1>
   <a href="index.jsp">首页</a> >> <a href="index.jsp">商品列表</a>
   <hr> 
   <div id="shopping">
   <form action="" method="">		
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品单价</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<%
					//首先判断session中是否有购物车对象
						   
				%>
				<!-- 循环的开始 -->
				     <%
				     	
				     		         CartService cartService=new CartService();
				     		         List list=cartService.showgoods();
				     		         double t=cartService.totalPrice();
				     		        if (list.size()>0&&list!=null){
				     		           
				     		            Carts pf;

				     		            for (int i=0;i<list.size();i++){
				     		                pf =new Carts();
				     		                pf=(Carts) list.get(i);
				     		       
				     %> 
				<tr name="products" id="product_id_1">
					<td class="thumb"><img src="<%=pf.getPicture()%>" /><a href="detail.jsp?id=<%=pf.getBookid()%>"><%=pf.getName()%></a></td>
					<td class="number"><%=pf.getPrice() %></td>
					<td class="price" id="price_id_1">
						<span><%=pf.getTotalPrice() %></span>
						<input type="hidden" value="" />
					</td>
					<td class="number">
                     	<%=pf.getBuynum()%>					
					</td>                        
                    <td class="delete">
					  <a href="CartServlet?action=delete&id=<%=pf.getId()%>" onclick="delcfm();">删除</a>					                  
					</td>
				</tr>
				     <% 
				         }
				     		        }
				     %>
				<!--循环的结束-->
				
			</table>
			<div class="total"><span id="total">总计：<%=t%>￥</span></div>
			
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>
  </body>
</html>
