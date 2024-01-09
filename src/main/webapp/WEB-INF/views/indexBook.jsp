<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
	<main class="container">
		<div class="row my-5">
			<div class="col-md-6">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>JANコード</th>
							<th>ISBNコード</th>
							<th>書籍名称</th>
							<th>書籍名称カナ</th>
							<th>価格</th>
							<th>発行日</th>
							<th>登録日時</th>
							<th>更新日</th>
						</tr>
					</thead>
					<%
					ArrayList<HashMap<String, String>> rows = (ArrayList<HashMap<String, String>>) request.getAttribute("rows");
					if (rows != null) {
					%>
					<%
					for (HashMap<String, String> columns : rows) {
					%>
					<tr
						onclick="location.href='editBook?JAN_CD=<%=columns.get("JAN_CD")%>'"
						style="cursor: pointer;">
						<td style="white-space: nowrap"><%=columns.get("JAN_CD")%></td>
						<td style="white-space: nowrap"><%=columns.get("ISBN_CD")%></td>
						<td style="white-space: nowrap"><%=columns.get("BOOK_NM")%></td>
						<td style="white-space: nowrap"><%=columns.get("BOOK_KANA")%></td>
						<td style="white-space: nowrap">¥ <%=String.format("%,d", Integer.parseInt(columns.get("PRICE")))%></td>
						<td style="white-space: nowrap"><%=columns.get("ISSUE_DATE")%></td>
						<td style="white-space: nowrap"><%=columns.get("CREATE_DATETIME")%></td>
						<td style="white-space: nowrap">
							<%
							String upDateTime = columns.get("UPDATE_DATETIME");
							if (upDateTime != null) {
							%> <%=columns.get("UPDATE_DATETIME")%> <%
 }
 %>
						</td>
					</tr>
					<%
					}
					}
					%>
				</table>
			</div>
		</div>
	</main>
</body>
</html>
