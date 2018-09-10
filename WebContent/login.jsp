<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link href="resources/styles/bootstrap.min.css"
          rel="stylesheet" id="bootstrap-css">
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h1 class="form-heading">DIES</h1>
    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <h2>Login</h2>
                <p>Please enter your username and password</p>
            </div>
            <form action="loginconfirm" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="inputEmail"
                           placeholder="Username" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control"
                           id="inputPassword"
                           placeholder="Password" name="password">
                </div>
                <div class="forgot">
                    <a href="reset.html">Forgot password?</a>
                </div>
                <button type="submit" value="login" class="btn btn-primary">
                    Login
                </button>
            </form>
        </div>
        <p class="botto-text">Designed by Evan Cranney & Shalitha Weerakoon
            Karunatilleke</p>
    </div>
</div>
</body>
</html>