<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = (String)request.getAttribute("msg");
%>
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
		<% if(msg != null){ %>
			<%= msg %>
		<% } %>
		<form action="/syukatsu_note/SignUp" method="post">
			ユーザー名（10字以内）<br><input type="text" size="20" maxlength="15" name="userName"><br>
			パスワード（英数字のみ20字以内）<br><input type="password" name="password"><br>
			パスワード（確認用）<br><input type="password" name="pwConfilm"><br>
			<br><input type="submit" value="登録内容確認">
		</form>
	</div>
	<br>
</div>
<%@ include file="../include/footer.jsp"%>
</body>
</html>