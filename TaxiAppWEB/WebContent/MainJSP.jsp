<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	background-color: #ADD8E6;
}

.main {
	color: #ffffff;
	text-align: center;
	padding-bottom: 100px;
	padding-top: 100px;
}

.nav-tabs li a {
	color: #777;
}

button {
	align-content: center;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Logo</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/TaxiAppWEB/Main" method="get">HOME</a></li>
				<li><a href="/TaxiAppWEB/Login" method="get">LOGIN</a></li>
				<li><a href="/TaxiAppWEB/SignUp" method="get">SIGNUP</a></li>
				<li><a href="/TaxiAppWEB/Contact" method="get">CONTACT</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="main">

		<h3>TaxiAppWEB</h3>
		<img src="jpg/mainPhoto.jpg" class="img-circle" alt="mainPhoto"
			width="350" height="350">
		<h3>I'm an adventurer</h3>
	</div>
	<div align="center">
		<form action="/TaxiAppWEB/Reservatin" method="get">
			<button type="submit" class="btn btn-lg btn-info">ZAKAZI
				VOZNJU</button>
		</form>
	</div>

</body>
</html>