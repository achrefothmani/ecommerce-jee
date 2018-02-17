<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panier</title>
</head>
<body background="img.jpg"><br><br>
<div class="container" id="formPanier">
	<form action="panier" method="post"><br>
		<div class="panel panel-success">
			<div class="panel-heading">Votre Panier</div>
			<div class="panel-body">
			<table  class="table table-hover">
				<thead>
				<tr> 
				<th>Designation</th>
				<th>Description</th>
				<th>Prix</th> 
				<th>Quantite</th>
				<th>Selection</th> 
				<th>Photo</th>
				<th>Panier</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${panierP.getTemporalItems()}" var="pp">
					<tr>
					<td>${pp.value.designation}</td>
					<td>${pp.value.desc}</td>
					<td>${pp.value.prix}</td>
					<td>${pp.value.qte}</td>
					<td>${pp.value.selection}</td>
					<td><img alt="INVALIDE" src="data:image/jpeg;base64,${pp.value.photo}"/></td>
					<td><form action="panier" method="GET"><a href="${pageContext.request.contextPath}/panier?id=${pp.value.idProduit}&action=supprimer"><button type="button" class="btn btn-danger" name="ajouter" value="${pp.value.idProduit}">Supprimer</button></a></form></td>									
					</tr>
					</c:forEach>
				</tbody>
				</table>			
			</div>
		</div>	
	</form>
</div>
</body>
</html>