<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.CompanyBeans,java.util.List"%>
<%
CompanyBeans comBeans = ((List<CompanyBeans>) session
		.getAttribute("comBeansList")).get(0);
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
		<div class="FormWrapper">
			<form action="/syukatsu_note/ChangeRegistConfilm" method="get">
				最終コンタクト<br><input type="date" name="lastContact" max="date" required="required" autocomplete="on"><br>
				ステータス(現在：<%=comBeans.getStatus()%>)<br>
				<select name="status">
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
				</select><br> メモ（～100文字）<br>
				<textarea rows="5" cols="30" name="memo"><%=comBeans.getMemo()%></textarea>
				<br> <br>
				<input type="submit" value="変更内容確認">
			</form>
		</div>
	</div>
	<br>
	<hr>
	<div class="IndexWrapper">
		<input type="button" onclick="location.href='./'" value="戻る">
	</div>
	<br>
	<hr>
	<div class="IndexWrapper">
		<input type="button" onclick="location.href='./DeleteInfo?userId=<%= comBeans.getUserId() %>&companyName=<%= comBeans.getCompanyName() %>'" value="この企業を削除する">
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>