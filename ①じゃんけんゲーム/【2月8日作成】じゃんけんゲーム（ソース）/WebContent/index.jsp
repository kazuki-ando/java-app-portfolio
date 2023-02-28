<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>じゃんけんゲーム</title>
</head>
<body>
	<h1>じゃんけんゲーム</h1>
		<p>ユーザー名を決めてね</p>
		<form action="/jyanken_game/JyankenResult" method="get">
			<input type="text" name="name" /><br>
			<input type="submit" value="決定" /><br>
		</form>
</body>
</html>