<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List,model.ThreadPosts"%>
<%
int threadId = (Integer) session.getAttribute("threadId");
String threadName = (String) session.getAttribute("threadName");
String msg = (String) request.getAttribute("msg");
List<ThreadPosts> posts = (ArrayList<ThreadPosts>) session.getAttribute("postsList");
SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css ">
<title>なんちゃって掲示板</title>
</head>
<body>
<%@ include file="../include/header.jsp" %>
	<div>
		<h2>スレッド名：<%=threadName%></h2>
	</div>
	<div>
	<form action="/keijiban/Posts" method="get">
	<input type="hidden" name="threadId" value="<%= threadId %>">
	<input type="hidden" name="threadName" value="<%= threadName %>">
	<input type="submit" value="更新">
	</form>
	</div>
	<br>
	<div>
		<form action="/keijiban/Posts" method="post">
			<input type="hidden" name="threadId" value="<%= threadId %>">
			名前<br>
			<input type="text" size="15" name="posterName" value="匿名"><br>
			投稿内容<br>
			<input type="text" size="70" name="postContent"><br>
			<br> <input type="submit" value="新規投稿">
		</form>
	</div>
	<div>
		<h3>投稿一覧</h3>
		<%if (msg != null) {%>
		<p><%=msg%></p>
		<%
		} else {
		%>
		<%for (ThreadPosts post : posts) {%>
		<table>
			<thead>
				<tr>
					<th colspan="1">投稿NO</th>
					<th colspan="2">投稿者</th>
					<th colspan="3">投稿日時</th>
					<th colspan="4">投稿内容</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="1"><%=post.getPost_id()%></td>
					<td colspan="2"><%=post.getPoster_name()%></td>
					<td colspan="3"><%=f.format(post.getPost_date())%></td>
					<td colspan="4"><%=post.getPost_contet()%></td>
				</tr>
			</tbody>
		</table>
		<%
		}
		%>
		<%
		}
		%>

	</div>
	<br>
	<input type="button" onclick="location.href='Top'" value="Topへ戻る">
</body>
</html>