<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
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
		<div class="row justify-content-center my-5">
			<div class="col-md-6">
				<form action="editBook" method="post" class="my-5">
					<div class="mb-3">
						<label for="janCode" class="form-label"><strong>JANコード</strong></label>
						<input type="text" class="form-control"
							style="padding: 10px; border: none;" id="janCode" name="JAN_CD"
							value="<%=request.getAttribute("JAN_CD")%>">
					</div>

					<div class="mb-3">
						<label for="isbnCode" class="form-label"><strong>ISBNコード</strong>
						</label> <input type="text" class="form-control"
							style="padding: 10px; border: none;" id="isbnCode" name="ISBN_CD"
							value="<%=request.getAttribute("ISBN_CD")%>">
					</div>

					<div class="mb-3">
						<label for="bookName" class="form-label"><strong>書籍名称</strong></label>
						<input type="text" class="form-control"
							style="padding: 10px; width: 500px; border: none;" id="bookName"
							aria-describedby="bookNameHelp" name="BOOK_NM"
							value="<%=request.getAttribute("BOOK_NM")%>">
						<div id="bookNameHelp" class="form-text"></div>

					</div>
					<div class="mb-3">
						<label for="bookNameRead" class="form-label"><strong>書籍名称カナ</strong></label>
						<input type="text" class="form-control"
							style="padding: 10px; width: 500px; border: none;"
							id="bookNameRead" aria-describedby="bookNameReadHelp"
							name="BOOK_KANA" value="<%=request.getAttribute("BOOK_KANA")%>">
						<div id="bookNameReadHelp" class="form-text"></div>
					</div>
					<div class="mb-3">
						<label for="price" class="form-label"><strong>価格</strong></label>
						<input type="text" class="form-control"
							style="padding: 10px; border: none;" id="price"
							aria-describedby="priceHelp" name="PRICE"
							value="¥<%=String.format("%,d", Integer.parseInt(String.valueOf(request.getAttribute("PRICE"))))%>">
						<div id="priceHelp" class="form-text"></div>
					</div>
					<div class="mb-3">
						<label for="issueDate" class="form-label"><strong>発行日</strong></label>
						<input type="date" class="form-control"
							style="padding: 10px; border: none;" id="issueDate"
							aria-describedby="issueDateHelp" name="ISSUE_DATE"
							value="<%=request.getAttribute("ISSUE_DATE")%>">
						<div id="issueDateHelp" class="form-text"></div>
					</div>
					<div class="mb-3">
						<label for="createDateTime" class="form-label"><strong>登録日時</strong></label>
						<input type="datetime-local" class="form-control"
							style="padding: 10px; border: none;" id="createDateTime"
							aria-describedby="createDateTimeHelp" name="CREATE_DATETIME"
							value="<%=request.getAttribute("CREATE_DATETIME")%>">
						<div id="createDateTimeHelp" class="form-text"></div>
					</div>
					<div class="mb-3">
						<label for="updateDateTime" class="form-label"><strong>更新日時</strong></label>
						<input type="datetime-local" class="form-control"
							style="padding: 10px; border: none;" id="updateDateTime"
							aria-describedby="updateDateTimeHelp" name="UPDATE_DATETIME"
							value="<%=request.getAttribute("UPDATE_DATETIME")%>" readonly>
						<div id="emupdateDateTimeHelpailHelp" class="form-text"></div>
					</div>
					<div class="d-grid gap-2 d-md-block">
						<button type="submit" class="btn btn-primary" type="button"
							style="cursor: pointer;">更新</button>
				</form>
				<button type="submit" class="btn btn-secondary" type="button">
					<a href="indexBook" style="color: white; text-decoration: none;">戻る</a>
				</button>
			</div>
		</div>
		</div>
	</main>
</body>
</html>