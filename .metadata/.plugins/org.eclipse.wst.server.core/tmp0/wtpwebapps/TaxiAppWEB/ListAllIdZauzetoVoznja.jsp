<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

  
<style>
body {
	background-color: #ADD8E6;
}

.custab {
	border: 1px solid #ccc;
	padding: 5px;
	margin: 5% 0;
	box-shadow: 3px 3px 2px #ccc;
	transition: 0.5s;
	width: 100%;
}

.custab:hover {
	box-shadow: 3px 3px 0px transparent;
	transition: 0.5s;
}
.thead{
	background: white;
}

p.message {
	text-align: center;
	font-family: Ariel;
}

h3 {
	text-align: center;
	font: ariel;
	font-weight: bold;
	color: navy;
}
</style>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
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

	<h3>Zauzete voznje</h3>

	<p id="message">${message }</p>
	<div class="container">
		<div class="row col-md-12 custyle">
			<table class="table table-striped custab">
				<thead class="thead">
					<tr>
						<th>ID</th>
						<th>Ime</th>
						<th>Prezime</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="zauzeteVoznje" items="${ListaZauzetihVoznji }">
						<tr>
							<td>${zauzeteVoznje.idVoznja }</td>
							<td>${zauzeteVoznje.ime }</td>
							<td>${zauzeteVoznje.prezime }</td>
							<td>
								<form action="/TaxiAppWEB/ListAllIdZauzetoVoznja" method="post">
									<input type="hidden" name="idVoznja"
										value="${zauzeteVoznje.idVoznja }" />
									<button class="btn btn-success active btn-md" id="slobodno"
										type="submit">Slobodno</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
		<h1>${zarada }</h1>

</body>
</html>