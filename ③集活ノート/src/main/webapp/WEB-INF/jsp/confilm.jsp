<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.UserBeans" %>
<%
String esc = "********";
%>
<jsp:useBean id="user" class="beans.UserBeans" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css ">
<title>集活ノート</title>
</head>
<body>
<%@ include file="../include/header.jsp" %>
<div class="IndexWrapper">
	<div class="FormWrapper">
		<p>新規登録を行います。よろしいですか？</p>
		<form action="/syukatsu_note/Confilm" method="post">
			ユーザー名：<jsp:getProperty property="userName" name="user"/><br>
			パスワード：<%= esc %>
			<br>
			<br><input type="submit" value="新規登録">
		</form>
	</div>
	<br>
</div>
<%@ include file="../include/footer.jsp"%>
</body>
</html>