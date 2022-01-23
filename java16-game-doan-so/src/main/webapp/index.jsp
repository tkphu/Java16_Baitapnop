<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cybersoft.javabackend.gamedoanso.util.UrlConst"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container h-100 d-flex justify-content-center">
		<div class="row h-100 justify-content-center align-content-center d-flex">
			<form class="w-100"
				action="<%=request.getContextPath() + UrlConst.EMAIL_LOGIN%>"
				method="get" class="form-group">
				<input class="btn btn-primary mt-2" type="submit" value="Bắt đầu" />
			</form>
		</div>
	</div>
</body>
</html>