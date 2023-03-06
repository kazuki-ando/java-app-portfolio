<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.CompanyBeans,java.util.List"%>
<%
int userId = (int)request.getAttribute("userId");
String companyName = (String)request.getAttribute("companyName");
String firstContact = (String)request.getAttribute("firstContact");
String lastContact = (String)request.getAttribute("lastContact");
String site = (String)request.getAttribute("site");
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
	<div class="IndexWrapper">
		<h3>以下の内容で新規登録してよろしいですか？</h3>
		<div class="FormWrapper">
			<form action="/syukatsu_note/CompanyInfoRegistConfilm" method="post">
				<input type="hidden" name="userId" value="<%= userId %>">
				<input type="hidden" name="companyName" value="<%= companyName %>">
				<input type="hidden" name="firstContact" value="<%= firstContact %>">
				<input type="hidden" name="lastContact" value="<%= lastContact %>">
				<input type="hidden" name="site" value="<%= site %>">
				<input type="hidden" name="status" value="<%= status %>">
				<input type="hidden" name="memo" value="<%= memo %>">
				【企業名】<br>
				<%= companyName %><br>
				【初回コンタクト】<br>
				<%= firstContact %><br>
				【最終コンタクト】<br>
				<%= lastContact %><br>
				【登録サイト】<br>
				<%= site %><br>
				【ステータス】<br>
				<%= status %><br>
				【メモ】<br>
				<p class="PrintMemo"><%= memo %><br></p>
				<br><input type="submit" value="企業情報を登録"><br>
			</form><br>
		</div>
	</div>
	<br>
	<hr>
	<div class="IndexWrapper">
		<input type="button" onclick="location.href='./Top'" value="戻る">
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>