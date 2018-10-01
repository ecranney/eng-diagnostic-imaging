<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="content" fragment="true"%>
<%@ attribute name="scripts" fragment="true"%>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mtform" tagdir="/WEB-INF/tags/forms"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>
<mt:header />
<mt:favicon />
<link href="resources/styles/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" media="screen"
	href="resources/styles/datetimepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/form-util.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/form-main.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/animsition/animsition.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/select2/select2.min.css">
</head>
<body>
	<jsp:invoke fragment="content"></jsp:invoke>
	<mt:footer />
	<mtform:formfooter />
	<jsp:invoke fragment="scripts"></jsp:invoke>
</body>
</html>

