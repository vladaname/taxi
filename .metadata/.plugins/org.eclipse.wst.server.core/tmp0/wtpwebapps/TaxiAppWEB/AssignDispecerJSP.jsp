<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign</title>

<style>
body {
	background-color: yelow;
}

table {
	width: 100%;
	padding: 10px;
	font-size: 100%;
	font-family: arial, sans-serif;
	border-collapse: collapse;
}

.intro {
	color: blue;
}

td, th {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>

</head>
<body>

	<p>Pregled slobodnih vozaca</p>

	<h2>${poruka }</h2>
	<h2>${message }</h2>

	<table>
		<thead>

			<tr>
				<th>Adresa Cilj</th>
				<th>Status voznje</th>
				<th>Odaberi auto</th>
				<th>Aktivnost</th>

			</tr>
		</thead>
		<tbody class="intro">
			<c:forEach var="voznja" items="${listaSlobodneVoznja}">
				<tr>
					<td>${voznja.adresaCilj }</td>
					<td>${voznja.statusVoznje }</td>

					<form action="/TaxiAppWEB/AssignDispecer" method="post">
						<td><select name="id_korisnik">
								<c:forEach var="vp" items="${listaSlobodnihVozaca}">
									<option value="${vp.id_korisnik }">${vp.ime}
										${vp.prezime}</option>
								</c:forEach>
						</select></td>
						<td><input type="hidden" name="id_voznja"
							value="${voznja.idVoznja }">
							<button class="btn btn-success active btn-xs" type="submit"
								id="zakazi">Zakazi</button>
					</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>