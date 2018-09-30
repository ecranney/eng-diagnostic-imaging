<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>404</title>
        <%@ include file="templates/header.jsp" %>          
    </head>
    <body>
        <div class="page-wrap d-flex flex-row align-items-center">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-12 text-center">
                        <span class="display-1 d-block">Holla!!!</span>
                        <div class="mb-4 lead">You need to check your login
                                               details
                        </div>
                        <a href="/home" class="btn btn-link">Back to Home</a>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="templates/footer.jsp" %> 
    </body>
</html>