<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.UserBeans,java.util.Date"%>
<%
String msg = (String)request.getAttribute("msg");
SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
String date = f.format(new Date());
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
	<%@ include file="../include/header.jsp"%>
	<br>
	<div class="IndexWrapper">
	<% if(msg != null){%>
		<%= msg %>
	<% } %>
		<div class="FormWrapper">
			ログイン中のユーザー：<jsp:getProperty property="userName" name="user" />さん<br>
			<p>登録する企業の情報を入力してください</p>
			<form action="/syukatsu_note/CompanyInfoRegist" method="post">
			<input type="hidden" name="userId" value="<jsp:getProperty property="userId" name="user"/>">
			企業名<br><input type="text" name="companyName"><br>
			初回コンタクト<br><input type="date" name="firstContact" max="date" required="required" autocomplete="on"><br>
			最終コンタクト<br><input type="date" name="lastContact" max="date" required="required" autocomplete="on"><br>
			登録サイト<br><input type="text" name="site" required="required"><br>
			ステータス<br><select name="status">
			<option value="0">書類審査中</option>
			<option value="1">1次面接日程調整中</option>
			<option value="2">1次面接結果待ち</option>
			<option value="3">2次面接日程調整中</option>
			<option value="4">2次面接結果待ち</option>
			<option value="5">最終面接日程調整中</option>
			<option value="6">合否結果結果待ち</option>
			<option value="7">書類落選</option>
			<option value="8">不採用通知</option>
			<option value="9">辞退</option>
			</select><br>
			メモ（～100文字）<br><textarea rows="5" cols="30" name="memo" placeholder="担当者名や印象、URLなど"></textarea><br>
			<br><input type="submit" value="新規登録">
			</form>
		</div>
			<br><input type="button" onclick="location.href='./Top'" value="戻る">
		<br>
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>