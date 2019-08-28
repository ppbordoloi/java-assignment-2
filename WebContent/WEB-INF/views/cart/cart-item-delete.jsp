<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Cart-item-delete</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<h2>Cart Item Delete:</h2>
	<form:form action="${ formSubmit }" method="post" modelAttribute="cartItemDetail">
		<form:hidden path="id" />
		<h5>Are you sure you want to remove "${cartItemDetail.productName}" from your cart ?</h5>
		<button type="submit">Delete</button>
		<div class="msg-error">${ errorMessage }</div>
	</form:form>


	<jsp:include page="../footer.jsp"/>
</body>
</html>