<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>My Cart</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<h2>My Cart:</h2>
		<table class="zdp-data-grid">
			<thead>
				<tr>
					<th>Id</th>
					<th>Product Id</th>
					<th>Product Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Amount</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="totalAmount" value="0" scope="page" />
				<c:if test="${empty cartItemList}">
		            <tr><td colspan="7">No cart item available!</td></tr>
			    </c:if>
				<c:if test="${not empty cartItemList}">
		        <c:forEach var="cartItem" items="${cartItemList}">
		            <tr>
		            	<td>${cartItem.id}</td>
		            	<td>${cartItem.productId}</td>
		            	<td>${cartItem.productName}</td>
		            	<td>${cartItem.productPrice}</td>
		            	<td>${cartItem.quantity}</td>
		            	<td>${cartItem.amount}</td>
		            	<td>
		            		<a href="cart-delete-item-${cartItem.id}">Delete from cart</a> |
		            		<a href="cart-add-item-${cartItem.productId}">Edit</a>
		            	</td>
		            </tr>
		            <c:set var="totalAmount" value="${totalAmount + cartItem.amount}" scope="page"/>
		        </c:forEach>
			    </c:if>
				<c:if test="${not empty cartItemList}">
		            <tr>
		            	<td colspan="5" style="text-align:right">Total</td>
		            	<td><c:out value="${totalAmount}" /></td>
		            	<td><form action="cart-billing-confirm" method="post"><button type="submit">Proceed to Billing</button></form></td>
		            </tr>
			    </c:if>
			</tbody>
		</table>
	<jsp:include page="../footer.jsp"/>
</body>
</html>