<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>DIES Login</title>

    <link rel="apple-touch-icon" sizes="57x57"
          href="resources/images/favicon/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="180x180"
          href="resources/images/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32"
          href="resources/images/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16"
          href="resources/images/favicon/favicon-16x16.png">
    <link rel="manifest" href="resources/images/favicon/site.webmanifest">
    <link rel="mask-icon" href="resources/images/favicon/safari-pinned-tab.svg"
         >
    <meta name="msapplication-TileColor" content="#da532c">
    <meta name="theme-color" content="#ffffff">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="resources/styles/bootstrap/bootstrap.min.css"
          rel="stylesheet" id="bootstrap-css">
    <script src="resources/js/bootstrap/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css"
          href="resources/styles/login-util.css">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/login-main.css">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/select2/select2.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/animate/animate.css">
    <link rel="stylesheet" type="text/css"
          href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <span class="login100-form-title">Are you sure you want to logout?</span>

            <div class="container-login100-form-btn">
                <form action="logout" method="post">
                    <input type="submit" name="logout"
                           class="login100-form-btn" value="Logout">
                </form>
            </div>

            <div class="container-login100-form-btn">
                <form action="home" method="post">
                    <input type="submit" name="home"
                           class="login100-form-btn" value="Cancel">
                </form>
            </div>
        </div>
    </div>

</div>

<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/popper.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/select2/select2.min.js"></script>
<script src="resources/js/tilt/tilt.jquery.min.js"></script>
<script>
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<script src="resources/js/custom.js"></script>
</body>
</html>