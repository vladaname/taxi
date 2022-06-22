<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ukupna Zarada</title>
</head>
<body>
	<c:choose> //grananje
		<c:when test="${korisnik == null }">
			<p>Korisnik nije ulogovan</p>

		</c:when>

		<c:otherwise>
			<p>Ulogovain korisnik je: ${korisnik.ime }</p>

		</c:otherwise>
	</c:choose>
	Ukupna zarada je:
	<p>${ukupnaZarada }</p>



	</form>

</body>
</html>