<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.*,java.util.ArrayList,java.util.List"%>
<%
String msg = (String) request.getAttribute("msg");
SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
UserBeans user = (UserBeans) session.getAttribute("user");
List<CompanyBeans> comList = (List<CompanyBeans>) session
		.getAttribute("comBeansList");
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
		<br> <b>ログイン中のユーザー：[<%= user.getUserName() %>]さん</b><br>
		<div class="IndexWrapper">
			<input type="button" onclick="location.href='./CompanyInfoRegist'"
				value="コンタクトした企業を登録する">
		</div>
	</div>
	<br>
	<hr>
	<div class="IndexWrapper">
		<h2>検索・並び替え</h2>
	</div>
	<div class="IndexWrapper">
		<form action="/syukatsu_note/Top" method="get">
			企業名で検索<br>
			<input type="text" name="search"> <br><br>
			リスト並び替え<br>
			<select name="sort" required="required">
			<option value="name">企業名</option>
			<option value="first">初回コンタクト</option>
			<option value="last">最終コンタクト</option>
			<option value="site">登録サイト</option>
			<option value="status">ステータス</option>
			<option value="memo">メモ</option>
			</select><br><br>
			<input type="radio" name="order" value="forward" checked="checked">正順
			<input type="radio" name="order" value="reverse">逆順<br>
			<br><input type="submit" value="GO">
		</form>
	</div>
	<br>
	<hr>
	<div class="comInfoWrapper">
		<h2>コンタクト企業一覧</h2>
		<%
			if (msg != null) {
			%>
		<%=msg%>
		<%
			} else {
			%>
		<table>
			<tr>
				<th class="no">NO</th>
				<th class="com">企業名</th>
				<th class="fContact">初回コンタクト</th>
				<th class="lContact">最終コンタクト</th>
				<th class="site">登録サイト</th>
				<th class="status">ステータス</th>
				<th class="memo">メモ</th>
				<th class="cDate">作成日</th>
			</tr>
			<%
				int num = 1;
				for (CompanyBeans cb : comList) {
				%>
			<tr>
				<td class="no"><%=num%></td>
				<td class="com"><a
					href="/syukatsu_note/ChangeRegist?userId=<%=user.getUserId()%>&companyName=<%=cb.getCompanyName()%>"><%=cb.getCompanyName()%></a></td>
				<td class="fContact"><%=f.format(cb.getFirstContact())%></td>
				<td class="lContact"><%=f.format(cb.getLastContact())%></td>
				<td class="site"><%=cb.getSite()%></td>
				<td class="status"><%=cb.getStatus()%></td>
				<td class="memo"><%=cb.getMemo()%></td>
				<td class="cDate"><%=f.format(cb.getCreateDate())%></td>
			</tr>
			<%
				num++;
				}
				%>
		</table>
		<%
		}
		%>
	</div>
	<br>
	<div class="IndexWrapper">
		<input type="button" onclick="location.href='./Top?logout=logout'" value="ログアウト">
	</div>
		 <%@ include file="../include/footer.jsp"%>
</body>
</html>