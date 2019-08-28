<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Cart Billing</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<h2>My Cart:</h2>
		<table class="zdp-data-grid">
			<thead>
				<tr>
					<th>Id</th>
					<th>Product Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="totalAmount" value="0" scope="page" />
				<c:if test="${empty cartItemList}">
		            <tr><td colspan="5">No cart item available!</td></tr>
			    </c:if>
				<c:if test="${not empty cartItemList}">
		        <c:forEach var="cartItem" items="${cartItemList}">
		            <tr>
		            	<td>${cartItem.id}</td>
		            	<td>${cartItem.productName}</td>
		            	<td>${cartItem.productPrice}</td>
		            	<td>${cartItem.quantity}</td>
		            	<td>${cartItem.amount}</td>
		            </tr>
		            <c:set var="totalAmount" value="${totalAmount + cartItem.amount}" scope="page"/>
		        </c:forEach>
			    </c:if>
				<c:if test="${not empty cartItemList}">
		            <tr>
		            	<td colspan="4" style="text-align:right">Total</td>
		            	<td><c:out value="${totalAmount}" /></td>
		            </tr>
			    </c:if>
			</tbody>
		</table>
		<form action="cart-billing-done" method="post"><button type="submit">Confirm</button></form>
	<jsp:include page="../footer.jsp"/>
</body>
</html>