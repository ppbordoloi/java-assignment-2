<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>product-list</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<h2>Product List:</h2>
	<c:if test="${currentUser.isAdmin}">
		<a href="product-add">Add New Product</a>
	</c:if>

		<table class="zdp-data-grid">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Price</th>
					<th>Stock</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty productList}">
		            <tr><td colspan="5">No Product available!</td></tr>
			    </c:if>
				<c:if test="${not empty productList}">
		        <c:forEach var="product" items="${productList}">
		            <tr>
		            	<td>${product.id}</td>
		            	<td>${product.name}</td>
		            	<td>${product.price}</td>
		            	<td>${product.stock}</td>
		            	<td>
		            		<a href="cart-add-item-${product.id}">Add to cart</a>
							<c:if test="${currentUser.isAdmin == true}">
		            			| <a href="product-edit-${product.id}">Edit</a> | <a href="product-delete-${product.id}">Delete</a>
						    </c:if>
		            	</td>
		            </tr>
		        </c:forEach>
			    </c:if>
			</tbody>
		</table>
	<jsp:include page="../footer.jsp"/>
</body>
</html>