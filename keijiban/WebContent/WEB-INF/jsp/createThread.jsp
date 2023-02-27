<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css ">
<title>掲示板</title>
</head>
<body>
<%@ include file="../include/header.jsp" %>
<h1>新規スレッド作成</h1>
<div class="formWrapper">
	<% if(errorMsg != null){ %>
		<p><font style="color: red;"><%= errorMsg %></font></p>
	<%} %>
		<div class="form">
			<form action="/keijiban/CreateThread" method="get">
				スレッド名<br> <input type="text" name="threadName"><br>
				作成者名<br> <input type="text" name="createrName"><br>
				<br><input type="button" onclick="history.back()" value="戻る">
				<input type="submit" value="確認">
			</form>
		</div>
	</div>

</body>
</html>