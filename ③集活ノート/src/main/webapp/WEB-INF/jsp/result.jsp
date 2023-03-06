<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String msg = (String)request.getAttribute("msg");
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css ">
<title>集活ノート</title>
</head>
<body>
	<div class="IndexWrapper">
		<h2><%= msg %></h2>
		<br> <input type="button" onclick="location.href='./Top'"
			value="TOP画面へ">
	</div>
</body>
</html>