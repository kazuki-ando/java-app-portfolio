<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String threadName = (String)session.getAttribute("threadName");
String createrName = (String)session.getAttribute("createrName");
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
		<div class="form">
			<form action="/keijiban/CreateThread" method="post">
				<p>【スレッド名】：</p><b><%= threadName %></b> <br>
				<p>【作成者名】：</p><b><%= createrName %></b> <br>
				<br><input type="button" onclick="history.back()" value="戻る">
				<input type="submit" value="スレッド作成">
			</form>
		</div>
	</div>


</body>
</html>