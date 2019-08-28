<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Session Expires</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<h1>Your login session had expired!!!</h1>
	<a href="${ loginUrl }">Please relogin</a>
	<jsp:include page="../footer.jsp"/>
</body>
</html>