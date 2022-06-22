<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zarada</title>
</head>
<body>

	<form action="/TaxiAppWEB/ZaradaPoIdKorisnik" method="post">
	Unesite ime korisnika: <input type="text" name="ime"><br>
	Unesite prezime korisnika: <input type="text" name="prezime"><br>
	<input type="hidden" name="idK" value="${idK }"><br>
	<button class="btn btn-success active btn-xs" type="submit" id="prikazi">Zavrsi</button>
	Ukupan racun: <p>$ukupan_racun }</p><br>
	</form>
	
	
</body>
</html>