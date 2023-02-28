<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="servlet.JyankenResult"%>
<%
String chand = (String) request.getAttribute("chand");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>じゃんけんゲーム</title>
</head>
<body>
	<h1>じゃんけんゲーム</h1>
	<h2>
		<strong>${user.getName()}</strong>さん、手を選んでね：
	</h2>
	<form action="/jyanken_game/JyankenResult" method="post">
		<input type="radio" name="phand" value="0">ぐー<br> <input
			type="radio" name="phand" value="1">ちょき<br> <input
			type="radio" name="phand" value="2">ぱー<br> <input
			type="submit" value="決定">
	</form>
	<br>
	<c:if test="${result != null}">
		<p>コンピュータの手：<%=chand%></p>
		<p>結果：${result}</p>
		<h2>これまでの結果</h2>
		<p>
			勝ち：
			<c:out value="${user.getWin()}" />
			負け：
			<c:out value="${user.getLose()}" />
			引き分け：
			<c:out value="${user.getDraw()}" />
		</p>
	</c:if>
</body>
</html>