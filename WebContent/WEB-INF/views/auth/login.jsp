<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<h1>User Login</h1>
	<form:form action="${ loginTarget }" method="post" modelAttribute="login">
		<table>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" required="true" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input type="password" path="password" required="true" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">Login</button>
				</td>
			</tr>
		</table>
		<div style="color:red;">${ errorMessage }</div>
	</form:form>
	<jsp:include page="../footer.jsp"/>
</body>
</html>