<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>product-edit</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<h2>Product Edit:</h2>
	<form:form action="${ formSubmit }" method="post" modelAttribute="product">
		<table>
			<tr>
				<td>ID:</td>
				<td><form:hidden path="id" />${product.id}</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><form:input path="price" /></td>
			</tr>
			<tr>
				<td>Stock:</td>
				<td><form:input path="stock" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">Update</button>
				</td>
			</tr>
		</table>
		<div class="msg-error">${ errorMessage }</div>
	</form:form>


	<jsp:include page="../footer.jsp"/>
</body>
</html>