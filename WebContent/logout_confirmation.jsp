<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>DIES Login</title>
        <%@ include file="templates/header.jsp" %>  
        <link rel="stylesheet" type="text/css"
              href="resources/styles/login-util.css">
        <link rel="stylesheet" type="text/css"
              href="resources/styles/login-main.css">
        <link rel="stylesheet" type="text/css"
              href="resources/styles/select2/select2.min.css">
        <link rel="stylesheet" type="text/css"
              href="resources/styles/css-hamburgers/hamburgers.min.css">
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
        <%@ include file="templates/footer.jsp" %> 
        <script src="resources/js/popper.js"></script>
        <script src="resources/js/select2/select2.min.js"></script>
        <script src="resources/js/tilt/tilt.jquery.min.js"></script>
        <script src="resources/js/custom.js"></script>
        <script>
            $('.js-tilt').tilt({
                scale: 1.1
            })
        </script>
    </body>
</html>