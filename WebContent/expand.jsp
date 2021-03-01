<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<head>
	<meta name="referrer" content="no-referrer">
		<meta charset="utf-8">
		<title>bb</title>
		<link rel="stylesheet" type="text/css" href="css/fangdajing.css"/>
		
	</head>
	<%String t=request.getParameter("picture"); %>
	<body>
		<div class="zoom" id="zoom">
			<div class="small" id="small">
				<div class="float1" id="float"></div>
				<img src="<%=t%>" alt="小人物" title="小人物" class="smallimg" width="300px" height="450px" id="smallimg">
			</div>
			<div class="big" id="big">
				<img src="<%=t%>" alt="大人物" width="900px" height="1350px" id="bigimg" class="bigimg">
			</div>
		</div>
	</body>
	<script type="text/javascript" charset="utf-8">
		var smallimg = document.getElementById("smallimg");
		var small = document.getElementById("small");
		var zoom = document.getElementById("zoom");
		var bigimg = document.getElementById("bigimg");
		var float1 = document.getElementById("float");
		var big = document.getElementById("big");
		var imgx = smallimg.offsetWidth;// 	返回元素的宽度，包括边框和填充，但不是边距
		var imgy = smallimg.offsetHeight;//返回任何一个元素的高度包括边框和填充，但不是边距
		var imgleft = smallimg.offsetLeft;//返回当前元素的相对水平偏移位置的偏移
		var imgtop = smallimg.offsetTop;//返回当前元素的相对垂直偏移位置的偏移
		var floatx = float1.offsetWidth;
		var floaty = float1.offsetHeight;
		small.onmousemove = function(e) {
			let fx = e.pageX;//获取坐标的x值
			let fy = e.pageY;//获取坐标的y值
			let minx = imgleft + floatx / 2;//透明层最小的x值
			let maxx = imgleft + imgx - floatx / 2;//透明层最大的x值
			let miny = imgtop + floaty / 2;//透明层最小的y值
			let maxy = imgtop + imgy - floaty / 2;//透明层最大的y值
			fx = judgex(fx, minx, maxx);//获取x值
			fy = judgey(fy, miny, maxy);//获取y值
			setParameter(fx, fy, 3);//设置值
		}
		small.onmouseover = function() {//显示
			float1.style.display = "block";
			float1.style.cursor = 'move';
			big.style.display = "block";

		}
		small.onmouseout = function() {//消失
			float1.style.display = "none";
			big.style.display = "none";
		}

		function setParameter(a, b, c) {//设置相应偏移量
			float1.style.left = a + "px";
			float1.style.top = b + "px";
			bigimg.style.left = -c * a + "px";
			bigimg.style.top = -c * b + "px";
		}

		function judgex(via, min, max) {//判断坐标x值属于哪个范围,并返回相应的值
			let temp = 0;
			if ((via - min) <= 0) {
				temp = imgleft;
			} else if ((via - max) >= 0) {
				temp = imgx - floatx + imgleft;
			} else {
				temp = via - floatx / 2;
			}
			return temp;
		}

		function judgey(via, min, max) {//判断坐标y值属于哪个范围,并返回相应的值
			let temp = 0;
			if ((via - min) <= 0) {
				temp = imgtop;
			} else if (via - max >= 0) {
				temp = imgy - floaty + imgtop;
			} else {
				temp = via - floaty / 2;
			}
			return temp;
		}
	</script>
</html>