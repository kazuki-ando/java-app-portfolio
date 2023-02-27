<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List,java.util.ArrayList,model.KeijibanThread"%>
<%
String msg = (String)request.getAttribute("msg");
List<KeijibanThread> ktList = new ArrayList<>();
ktList = (List<KeijibanThread>) session.getAttribute("ktList");
SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日");
%>
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
		<form action="/keijiban/Top" method="post">
		<input type="submit" value="新規スレッド作成">
		<input type="hidden" name="createThread" value="createThread">
		</form>
	</div>
	<div>
		<h2>スレッド一覧</h2>
		<%if(msg != null){%>
		<p><%= msg %></p>
		<% }else{%>
		<%for(KeijibanThread t: ktList){ %>
		<table>
			<thead>
				<tr>
					<th colspan="1">スレッド番号</th>
					<th colspan="2">スレッド名</th>
					<th colspan="3">作成者</th>
					<th colspan="4">作成日</th>
					<th colspan="5">投稿数</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="1"><%= t.getThreadId() %></td>
					<td colspan="2">
					<a href="Posts?threadId=<%= t.getThreadId() %>"><%= t.getThreadName() %></a>
					</td>
					<td colspan="3"><%= t.getCreaterName() %></td>
					<td colspan="4"><%= f.format(t.getCreateDate()) %></td>
					<td colspan="5"><%= t.getPostCount() %></td>
					</tr>
			</tbody>
		</table>
		<br>
			<%} %>
		<%} %>

	</div>
</body>
</html>