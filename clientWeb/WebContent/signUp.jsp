<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="C:\Users\sss-g\Documents\eclipse workspace\clientWeb\WebContent\style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body background="img.jpg"><br><br>
<div class="container">
  <h2>Sign Up</h2>
  <form class="form-horizontal" action="signup" method="POST">
  <div class="form-group">
      <label class="control-label col-sm-2" for="nom">Nom:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="nom" placeholder="votre nom" name="nom">
      </div>  
    </div>
    
     <div class="form-group">
      <label class="control-label col-sm-2" for="prenom">Prenom:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="prenom" placeholder="votre prenom" name="prenom">
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="email" placeholder="votre email" name="email">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="login">Login:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="login" placeholder="votre login" name="login">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="pwd" placeholder="votre mot de passe" name="pwd">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-primary">Submit</button>
      </div>
    </div>
  </form>
</div>
</body>
</html>