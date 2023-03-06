<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.CompanyBeans,java.util.List"%>
<%
int userId = (int)request.getAttribute("userId");
String companyName = (String)request.getAttribute("companyName");
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
	<%@ include file="../include/header.jsp"%>
	<br>
	<div class="IndexWrapper">
		<h3>以下の企業情報を削除してよろしいですか？</h3>
		<div class="FormWrapper">
			<form action="/syukatsu_note/DeleteInfo" method="post">
				<input type="hidden" name="userId" value="<%= userId %>">
				<input type="hidden" name="companyName" value="<%= companyName %>">
				【企業名】<br>
				<%= companyName %><br>
				<br><input type="submit" value="削除"><br>
			</form><br>
		</div>
	</div>
	<br>
	<hr>
	<div class="IndexWrapper">
		<input type="button" onclick="location.href='./ChangeRegist'" value="戻る">
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>