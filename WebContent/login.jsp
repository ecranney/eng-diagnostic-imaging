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
        <c:if test="${not empty sessionScope.userid}">
            <c:redirect url="/home"/>
        </c:if>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
				<span class="login100-form-title">Diagnostic Imaging
					Enterprise System</span>
                    <div class="login100-pic js-tilt" data-tilt>
                        <img src="resources/images/dies.png" alt="IMG">
                    </div>
                    <form action="login" method="post"
                          class="login100-form validate-form">
                        <div class="container">
                            <a href="#" data-toggle="tooltip"
                               title="username: admin, password: admin"> <i
                                    class="fa fa-question-circle"
                                    aria-hidden="true"
                                    aria-hidden="true"></i> info</a>
                        </div>
                        <br>
                        <div class="wrap-input100 validate-input"
                             data-validate="Valid email is required: ex@abc.xyz">
                            <input class="input100" type="text" name="username"
                                   placeholder="Username"> <span
                                class="focus-input100"></span>
                            <span class="symbol-input100"> <i
                                    class="fa fa-envelope"
                                    aria-hidden="true"></i>
						</span>
                        </div>
                        <div class="wrap-input100 validate-input"
                             data-validate="Password is required">
                            <input class="input100" type="password"
                                   name="password"
                                   placeholder="Password"> <span
                                class="focus-input100"></span>
                            <span class="symbol-input100"> <i class="fa fa-lock"
                                                              aria-hidden="true"></i>
						</span>
                        </div>
                        <div class="container-login100-form-btn">
                            <button type="submit" value="login"
                                    class="login100-form-btn">Login
                            </button>
                        </div>
                        <div class="text-center p-t-12">
                            <span class="txt1"> Forgot </span> <a class="txt2"
                                                                  href="#">
                            Username / Password? </a>
                        </div>
                        <div class="text-center p-t-136">
                            <a class="txt2" href="#"> Create your Account <i
                                    class="fa fa-long-arrow-right m-l-5"
                                    aria-hidden="true"></i>
                            </a>
                        </div>
                    </form>
                    <p class="botto-text">Designed by Evan and Shalitha</p>
                </div>
            </div>
        </div>
        <%@ include file="templates/footer.jsp" %>
        <script src="resources/js/popper.js"></script>
        <script src="resources/js/select2/select2.min.js"></script>
        <script src="resources/js/tilt/tilt.jquery.min.js"></script>
        <script src="resources/js/custom-login.js"></script>
        <script>
            $('.js-tilt').tilt({
                scale: 1.1
            })
        </script>
        <script type="text/javascript">
            <
            script >
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </body>
</html>