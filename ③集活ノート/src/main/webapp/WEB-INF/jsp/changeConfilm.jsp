<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.CompanyBeans,java.util.List"%>
<%
CompanyBeans comBeans = ((List<CompanyBeans>) session
		.getAttribute("comBeansList")).get(0);
String lastContact = (String)request.getAttribute("lastContact");
String status = (String)request.getAttribute("status");
String memo = (String)request.getAttribute("memo");
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
		<h2>
			【<%=comBeans.getCompanyName()%>】の登録情報を変更
		</h2>
	</div>
	<hr>
	<div class="IndexWrapper">
		<h3>以下の内容で登録してよろしいですか？</h3>
		<div class="FormWrapper">
			<form action="/syukatsu_note/ChangeRegistConfilm" method="post">
				<input type="hidden" name="lastContact" value="<%= lastContact %>"><br>
				【最終コンタクト】<br>
				<%= lastContact %><br>
				<input type="hidden" name="status" value="<%= status %>"><br>
				【ステータス】<br>
				<%= status %><br>
				<input type="hidden" name="memo" value="<%= memo %>"><br>
				【メモ】<br>
				<p class="PrintMemo"><%= memo %><br></p>
				<br><input type="submit" value="登録"><br>
			</form><br>
		</div>
	</div>
	<br>
	<hr>
	<div class="IndexWrapper">
		<input type="button" onclick="location.href='./'" value="戻る">
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>