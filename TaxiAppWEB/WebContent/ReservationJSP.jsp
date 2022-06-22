<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="well">Booking reservation Form</h1>
		<div class="col-lg-12 well">
			<div class="row">
				<form  action="/TaxiAppWEB/Reservatin" method="post">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Phone Number</label> <input type="text" name="tel"
									placeholder="Enter Phone Number Here.." class="form-control">
							</div>
							<div class="col-sm-6 form-group">
								<label>Email Address</label> <input type="text" name="email"
									placeholder="Enter Email Address Here.." class="form-control">
							</div>
							<div class="form-group">
								<label>Pick-up Address</label> <input type="text"
									name="adresaPolazak" placeholder="Enter Pick up Address Here.."
									rows="3" class="form-control">
							</div>
							<div class="form-group">
								<label>Drop-off Address</label> <input type="text"
									name="adresaDolazak"
									placeholder="Enter drop off Address Here.." rows="3"
									class="form-control">
							</div>
							<div class="form-group">
								<label></label> <input type="hidden"
									name="id_korisnik" class="form-control">
							</div>
							<div class="form-group">
								<label></label> <input type="hidden"
									name="id_voznja" class="form-control">
							</div>
							
<!-- 							<div class="form-group"> -->
<!-- 								<input type="hidden" name="idKorisnik" value="korisnik.idKorisnik"> -->
<!-- 							</div> -->
						</div>
					</div>
				
					<button type="submit" class="btn btn-lg btn-info">Book</button>
					
			</div>
			<p>${korisnik }</p>
			</form>
			<p>${message }</p>
		</div>
	</div>
	</div>

</body>
</html>