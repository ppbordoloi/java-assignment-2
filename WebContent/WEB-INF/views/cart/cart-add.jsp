<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add to cart</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<h2>Add to cart:</h2>

	<form:form action="${ formSubmit }" method="post" modelAttribute="cartItem">
		<form:hidden path="id" />
		<form:hidden path="userId" />
		<form:hidden path="productId" />

		<h5>Add "${productName}" to cart ?</h5>
		Quantity: <form:input path="quantity" type="number" min="1" max="${stock}"/>
		<button type="submit">Add to Cart</button>
	</form:form>

	<jsp:include page="../footer.jsp"/>
</body>
</html>