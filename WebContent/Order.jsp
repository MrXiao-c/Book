<%@page import="util.Pager"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="dao.booksDAO"%>
 <%@ page import="entity.books" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
  <head>
   <meta name="referrer" content="no-referrer">
   
   
	<title>Bootstrap 实例 - 按钮大小</title>
	   <link type="text/css" rel="stylesheet" href="css/style1.css" />
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script> 
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	   hr{
	    border-color:FF7F00; 
	   }
	   div{
	      float:left;
	      margin: 10px;
	   }
	   div dd{
	      margin:0px;
	      font-size:10pt;
	   }
	   div dd.dd_name
	   {
	      color:blue;
	   }
	   div dd.dd_city
	   {
	      color:#000;
	   }
	   .input-group-btn {
    position: relative;
    font-size: 0;
    white-space: nowrap;
}
.divcss5-b{margin:5 20px} 
.btn-search {
    color: #ffffff;
    padding: 4px 8px 4px 8px;
    background-color: #8BACA1;
    border-radius: 5px;
    border-radius: 0;
    padding-left: 10px;
    padding-right: 10px;
}
	</style>
	
  </head>
  <%
	// 获取请求的上下文
	String context = request.getContextPath();
%>
  <link href="css/pagination.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">

// 点击分页按钮以后触发的动作
function handlePaginationClick(new_page_index, pagination_container) {
    $("#orderForm").attr("action", "<%=context %>/OrderServlet?pageNum=" + (new_page_index+1));
    $("#orderForm").submit();
    return false;
}

$(function(){
	$("#News-Pagination").pagination(${result.totalRecord}, {
        items_per_page:${result.pageSize}, // 每页显示多少条记录
        current_page:${result.currentPage} - 1, // 当前显示第几页数据
        num_display_entries:8, // 分页显示的条目数
        next_text:"下一页",
        prev_text:"上一页",
        num_edge_entries:2, // 连接分页主体，显示的条目数
        callback:handlePaginationClick
	});
	
	
	
});
function delcfm() {
    if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
    }
}
</script>
  <body>
  <div>
  <h1>订单展示</h1>
			
    <font color="red">${errorMsg }</font>
		</div>
		<div>
		
			<form action="OrderServlet" id="orderForm"
				method="post">
			
					
                        <div class="flex-vertical-center-height">
                   
                    <div style="margin-left:20px;" class="col-md-7  col-xs-6 input-group">
                    
                        <input name="orderName" type="text" placeholder="图书名称" value="${orderName }" 
                        id="stu_name" class="form-control input-sm">
                        <span class="input-group-btn">
                            <input class="btn btn-search" type="submit" value="搜索">
                        </span>
                    </div>
                   
                </div>
			</form>
		</div>
    
    <hr>
  
    
    
  
      <tr>
        <td>
         <div style="margin-left: 100px; margin-top: 100px;">
		
		<c:if test="${fn:length(result.dataList) eq 0}">
			<span>查询的结果不存在</span>
		</c:if>
		<!-- 后台返回结果不为空 -->
		<c:if test="${fn:length(result.dataList) gt 0}">
			<!-- 大于0 -->
 </div>
			 <div id="shopping">
   
   <form action="" method="">		
			<table>
				<tr>
					<th>订单编号</th>
					<th>书籍</th>
					<th>书籍编号</th>
					<th>书籍名称</th>
					<th>购买数量</th>
					<th>价格</th>
					<th>操作</th>
				</tr>
          
           <c:forEach items="${result.dataList}" var="order">
          <tr name="products" id="product_id_1">
           <td class="number"><a href="Orderdetail.jsp?id=<c:out value="${order.bookid }"></c:out>"> <dd class="dd_name"><c:out value="${order.id}"></c:out></dd></a></td>
               <td class="thumb"> <a href="detail.jsp?id=<c:out value="${order.bookid }"></c:out>"><img src="<c:out value="${order.picture }"></c:out>" width="120" height="90" border="1"/></a></td>
    
    
    <td class="number"><c:out value="${order.bookid}"></c:out></td>
     <td class="number"><c:out value="${order.name}"></c:out></td>
    <td class="price" id="price_id_1">
    <span><c:out value="${order.buynum}"></c:out></span>
    </td>
    <td class="price" id="price_id_1">
     <span>￥ <c:out value="${order.price }"></c:out></span>
      </td> 
       <td class="delete">
					  <a href="CartServlet?action=deleteorder&id=${order.id }" onclick="delcfm();">删除</a>					                  
					</td>
        
          <!-- 商品循环结束 -->
          
        </c:forEach>
       
        </table>
			<br>
				
		</c:if>
	</div>
        </td>
      </tr>
     
    </table>
    <div id="News-Pagination"></div>
    </center>
  
				
				

                
                
  </body>
</html>
