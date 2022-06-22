<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zavrsi voznju</title>
</head>
<body>
	<script type="text/javascript">
	function myFunction(val) {
//		  alert("The input value has changed. The new value is: " + val);
		  var cenaStart = document.getElementById('cenaStart').textContent //kako dolaziim do cenaStart? odakle kupim?
		  var cenaKm = document.getElementById('cenaKm').textContent  //kako dolaziim do cenaKm? odakle kupim?
// 		  alert("Cena star: " + cenaStart)
// 		  alert("Cena km: " + cenaKm)
		  var konacnaCena = parseInt(cenaStart) + (parseInt(cenaKm) * parseInt(val))
//		  alert("Konacna cena: " + konacnaCena)
		  document.getElementById('ukupnaCena').textContent = konacnaCena
		  
		}
	</script>
	<p>${message }</p>
	<form action="/TaxiAppWEB/ZavrsiVoznju" method="post">
		<input type="hidden" name="idVoznja" value="${idVoznja }"> 
		<input type="number" name="km" value="0.0" onchange="myFunction(this.value)">
		<button class="btn btn-success active btn-xs" type="submit" id="zakazi">Zavrsi</button>
	</form>
	
	Cena start:
	<label id="cenaStart">${aktuelniCenovnik.cenaStart }</label> 
	Cena po km:
	<label id="cenaKm">${aktuelniCenovnik.cena }</label>
	<br>
	Ukupna Cena:
		<label id="ukupnaCena">0</label>
	



</body>
</html>