<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="dao.booksDAO"%>
 <%@ page import="entity.books" %>
 <%
 	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="referrer" content="no-referrer">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
<script type="text/javascript" src="js/lhgcore.js"></script>
 <script type="text/javascript" src="js/lhgdialog.js"></script>
 <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
 <script language="javascript">
	    function delcfm() {
	        if (!confirm("确认要删除？")) {
	            window.event.returnValue = false;
	        }
	    }
   </script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
        function selflog_show(id)
       { 
          var num =  document.getElementById("number").value; 
          J.dialog.get({id: 'haoyue_creat',title: '购物成功',width: 600,height:400, link: '<%=path%>/CartServlet?id='+ id + '&num=' + num+ '&action=add',cover : true});
	}
	function add() {
		var num = parseInt(document.getElementById("number").value);
		if (num < 100) {
			document.getElementById("number").value = ++num;
		}
	}
	function sub() {
		var num = parseInt(document.getElementById("number").value);
		if (num > 1) {
			document.getElementById("number").value = --num;
		}
	}
	function selflog_buy(id)
    { 
		 if (!confirm("确认要购买？")) {
	            window.event.returnValue = false;
	        }else{
	        	 var num =  document.getElementById("number").value; 
	             J.dialog.get({id: 'haoyue_creat',title: '购买成功,以生成订单',width: 600,height:400, link: '<%=path%>/CartServlet?id='+ id + '&num=' + num+ '&action=buy',cover : true});
		        }
      
	}
/* $(function(){

var la=$("#large");
la.hide();
$("#previewImg").mousemove(function(e)){
	la.css({
		top:e.pageY
		left:e.pageX
		}).html('<img src="images/001jpg" />').show
}).mouseout(function)(){
		la.hide()
	});
		}); */
	
</script>
<title>书籍详情页面</title>
</head>
<h1>书籍详情</h1>
<hr>
<center>
	<table width="750" height="60" cellpadding="0" border="0">
		<tr>
			
			<%
							booksDAO bookDAO = new booksDAO();
											System.out.println(request.getParameter("id"));
										             books book = bookDAO.getItemsById(Integer.parseInt(request.getParameter("id")));
										             
										             if(book!=null)
										             {
						%>
          <td width="70%" valign="top">
             <table>
               <tr>
                 <td rowspan="4"><a href="expand.jsp?picture=<%=book.getPicture()%>"><img id='previewImg' src="<%=book.getPicture()%>" width="200" height="160"/></a></td>
                 <div id='large'></div>
               </tr>
               <tr>
                 <td><B><%=book.getName()%></B></td> 
               </tr>
               <tr>
                 <td>作者：<%=book.getAuthor()%></td>
               </tr>
               <tr>
                 <td>价格：￥<%=book.getPrice()%></td>
               </tr> 
          
               <tr>
                 <td>购买数量：<span id="sub" onClick="sub();">-</span><input type="text" id="number" name="number" value="1" size="2"/><span id="add" onClick="add();">+</span></td>
               </tr> 
             </table>
             <div id="cart">
               <a href="javascript:selflog_buy(<%=book.getId()%>)"><button type="button" class="btn btn-primary btn-lg ">购买</button></a><a href="javascript:selflog_show(<%=book.getId()%>)"><button type="button" class="btn btn-primary btn-lg ">加入购物车</button></a><a href="CartServlet?action=show"><button type="button" class="btn btn-primary btn-lg ">查看购物车</button></a>
               	
             </div>
             <a href="Order.jsp">查看订单</a>
    <a href="index.jsp">返回主页</a> 
          </td>
          <%
          	}
          %>
          <%
          	String list ="";
                        //从客户端获得Cookies集合
                        Cookie[] cookies = request.getCookies();
                        //遍历这个Cookies集合
                        if(cookies!=null&&cookies.length>0)
                        {
          	              for(Cookie c:cookies)
          	              {
          	                  if(c.getName().equals("ListViewCookie"))
          	                  {
          	                     list = c.getValue();
          	                  }
          	              }
          	          }
                        
          	  String newItem = request.getParameter("id")+"#";
                        if (list.contains(newItem)) {
                      	  // 去重
                      	  list = list.replaceAll(newItem, "");
                        }
                        list+=newItem;
                        //如果浏览记录超过1000条，清零.
                        String[] arr = list.split("#");
                        if(arr!=null&&arr.length>0)
                        {
                            if(arr.length>=1000)
                            {
                                list="";
                            }
                        }
                        Cookie cookie = new Cookie("ListViewCookie",list);
                        response.addCookie(cookie);
          %>
          <!-- 浏览过的商品 -->
          <td width="30%" bgcolor="#EEE" align="center">
             <br>
             <b>您浏览过的商品</b><br>
             <!-- 循环开始 -->
             <%
             	ArrayList<books> booklist = bookDAO.getViewList(list);
                             if(booklist!=null&&booklist.size()>0 )
                             {
                                System.out.println("itemlist.size="+booklist.size());
                                for(books i:booklist)
                                {
             %>
             <div>
             <dl>
               <dt>
                 <a href="detail.jsp?id=<%=i.getId()%>"><img src="<%=i.getPicture() %>" width="120" height="90" border="1"/></a>
               </dt>
               <dd class="dd_name"><%=i.getName() %></dd> 
               <dd class="dd_city">作者:<%=i.getAuthor()%>&nbsp;&nbsp;价格:￥ <%=i.getPrice() %> </dd> 
             </dl> 
             </div>
             <% 
                   }
                }
             %>
             <!-- 循环结束 -->
          </td>
        </tr>
      </table>
    </center>
   
  </body>
</html>