<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.javabackend.gamedoanso.util.UrlConst"%>
<%@ page import="java.time.LocalDateTime"%>
<!doctype html>
<html>
<head>
<title>User Information</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<span style="text-align: center;">
		<h1>User Information</h1>
		<h2>${email.email}</h1>
	</span>
	<div class="container h-100 d-flex justify-content-center align-content-center">
		<table class="table table-striped">
			<thead>
				<th>Point</th>
				<th>Timestamp</th>
				<th>Record ID</th>
			</thead>
			<tbody>
				<c:forEach var="userGame" items="${userInformation}"
				varStatus="loop">
					<tr>
						<td>${userGame.point}</td>
						<td>${userGame.isFinished ? userGame.finishDate : "Not Finished"}</td>
						<td>${userGame.id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="container h-100 d-flex justify-content-center align-content-center">
		<form class="col-12"
		action="<%=request.getContextPath() + UrlConst.GAME_PLAY%>"
		method="get" class="form-group">
			<button type="submit" class="btn btn-primary">Chơi game</button>
		</form>
	</div>
	<div class="container h-100 d-flex justify-content-center align-content-center"
	style="margin-top: 20px;">
		<form class="col-12"
		action="<%=request.getContextPath() + UrlConst.USER_INFORMATION%>"
		method="post" class="form-group">
			<button type="submit" class="btn btn-primary">Đăng xuất</button>
		</form>
	</div>
	<div class="container h-100 d-flex justify-content-center align-content-center"
	style="margin-top: 20px;">
		<form class="col-12"
		action="<%=request.getContextPath() + UrlConst.GAME_RANKING%>"
		method="get" class="form-group">
			<button type="submit" class="btn btn-primary">Bảng xếp hạng</button>
		</form>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>