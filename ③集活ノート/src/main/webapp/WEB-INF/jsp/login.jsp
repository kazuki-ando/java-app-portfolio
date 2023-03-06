<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String msg = (String) request.getAttribute("msg");
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css ">
<title>集活ノート</title>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<main>
	<div class="IndexWrapper">
		<div class="FormWrapper">
			<%
			if (msg != null) {
			%>
			<%=msg%>
			<%
			}
			%>
			<form action="/syukatsu_note/Login" method="post">
				ユーザー名<br>
				<input type="text" size="20" maxlength="15" name="userName"><br>
				パスワード<br>
				<input type="password" name="password"><br> <br>
				<input type="submit" value="ログイン">
			</form>
		</div>
		<br>
		<p>＊未登録の方はこちら</p>
		<div class="FormWrapper">
			<form action="/syukatsu_note/Login" method="get">
				<input type="hidden" name="signUp" value="signUp"> <input
					type="submit" value="新規登録">
			</form>
		</div>
	</div>
	</main>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>