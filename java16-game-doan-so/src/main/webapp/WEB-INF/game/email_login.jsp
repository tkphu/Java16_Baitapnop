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
	<div class="container">
		<h1 class="text-primary text-center">Đăng nhập email</h1>
		<form class="w-50"
			action="<%=request.getContextPath() + UrlConst.EMAIL_LOGIN%>"
			method="post" class="form-group">
			<label for="email">Email</label> 
			<input type="text"
				class="form-control" name="email" id="email"
				placeholder="abc@gmail.com"
				value='${lastEmail == null ? "" : lastEmail }'> 
			<label for="ePassword">Password</label> 
			<input type="password"
				class="form-control" name="ePassword" id="ePassword">
			<div class="form-group text-center">
				<label class="text text-danger" for="email password">${loginMessage}</label>
			</div>
			<input class="btn btn-primary mt-2" type="submit" value="Đăng nhập" />
		</form>
		<form class="w-50"
			action="<%=request.getContextPath() + UrlConst.EMAIL_REGISTER%>"
			method="get" class="form-group">
			<input class="btn btn-primary mt-2" type="submit"
				value="  Đăng kí   " />
		</form>
	</div>
</body>
</html>