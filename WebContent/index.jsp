<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="referrer" content="no-referrer">
   
   
	<title>项目仅供演示</title>
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
	.divcss5{width:800px}
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
// 第一步：页面渲染完之后，请求后台，获取学生数据，加载书籍信息
// 第二步：完成书籍查询功能
// 第三步：下拉加载更多数据的功能

var url = "<%=context %>/booksServlet"; // 请求获取数据的url

//一共有多少页数据
var totalPage = 1;
//每页显示多少条数据
var pageSize = 30;

var isLoading = false;

// 页面渲染完之中执行的代码
$(function(){
	// 绑定事件，监听滚动条下拉的动作
	bindScrollEvent();
	
	// 请求后台，加载书籍信息
	queryBook(1);
});

function queryForm(){
	
	// 绑定事件，监听滚动条下拉的动作
	bindScrollEvent();
	
	// 清空书籍的数据
	$("#BookDataBody").html("");
	
	// 清空书籍为空的提示信息
	$("#emptyInfo").html("");
	
	// 查询书籍
	queryBook(1);
}


//请求后台，加载书籍信息
function queryBook(pageNum){	
	
	if(pageNum > totalPage){
		$("#emptyInfo").html("没有更多的书籍数据了......");
		$(window).unbind("scroll");
	}
	if(isLoading){
		return;
	}else{
		isLoading = true; // 修改状态为正在加载数据
	}
	
	var bookName = $("#Book_name").val(); // 获取书籍姓名
	

	$("#loading").show();
	
	// 进行post请求
	$.post(url,{"pageSize":pageSize,"pageNum":pageNum, "bookName":bookName},function(data){
		// 加载书籍信息
		totalRecord = data.totalRecord;
		pageSize = data.pageSize;
		totalPage = data.totalPage; // 一共有多少页数据
		currentPage = data.currentPage; // 获取当前第几页数据
		var BookList = data.dataList; // 书籍记录信息
		console.log(totalPage)
		if(totalPage == 0){
			$("#emptyInfo").html("没有更多的书籍数据了......");
		}
		//加载书籍的数据
		showBookData(BookList);
		$("#totalRecord").html(totalRecord);
		$("#pageSize").html(pageSize);
		isLoading = false;
	},"json");
}

//加载学生的数据;
function showBookData(BookList){
	$("#loading").hide();
	var BookDataHtml = "";
	$.each(BookList,function(idx, obj){
		
			
		
			
				
		BookDataHtml +="<div class='col-xs-2 col-sm-6 col-md-4 col-lg-2'  style='width: 300px;'>"
					BookDataHtml +="<a  class='thumbnail' href='detail.jsp?id="+obj.id+"'>"
					BookDataHtml +="<img src="+obj.picture+"  style='width: 100%;height:260px; ' />"
					BookDataHtml +="<div class='caption'>"
						BookDataHtml +="<h3 style='overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp:1;'>" + obj.name + "</h3>"
						BookDataHtml +="<p style='overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp:1;'>" +"作者:"+ obj.author + "</p>"
						BookDataHtml +="<p>" +"价格:"+ obj.price + "</p>"
						BookDataHtml +="</div>"
							BookDataHtml +="</a>"
		
							
								BookDataHtml += "</div>" ;
		
		
	});
	$("#BookDataBody").append(BookDataHtml);

	
}

function bindScrollEvent(){
	// 添加滚动监听事件
	$(window).scroll( function() {
			var docHeight = $(document).height(); // 获取整个页面的高度
			var winHeight = $(window).height(); // 获取当前窗体的高度
			var winScrollHeight = $(window).scrollTop(); // 获取滚动条滚动的距离
			if(docHeight -30 <= winHeight + winScrollHeight){
				// 加载更多的书籍数据
				queryBook(currentPage+1);
			}
			
	} );
}

</script>
<body>
<li ><a href="LoginOutServlet">退出登录</a></li>
	<div>
  <h1>书籍展示</h1>
			
    <font color="red">${errorMsg }</font>
		</div>
		<div>
		
			<form action="#" id="bookForm"  
				method="post">
			
					
                        <div class="flex-vertical-center-height">
                   
                    <div style="margin-left:20px;" class="col-md-7  col-xs-6 input-group">
                    
                        <input name="bookName" type="text" placeholder="图书名称" value="" 
                        id="Book_name" class="form-control input-sm">
                        <span class="input-group-btn">
                            <input class="btn btn-search" type="button" value="搜索"  onclick="queryForm()">
                        </span>
                    </div>
                   
                </div>
			</form>
 <span  class="pagination_count" style="line-height: 84px;"> 共有<a id="totalRecord"></a>条记录 | 每页<a id="pageSize"></a>条</span>
		</div>
	 <center>	
		<!-- 后台返回结果不为空 -->
			<table >
				
				<tbody id="BookDataBody">
				</tbody>
			</table>
				<img id="loading"  style="display:none;" alt="loading...." src="images/loading.gif">
				<div id="emptyInfo"></div>
			<br>
	</div>
	</center>
</body>
</html>
