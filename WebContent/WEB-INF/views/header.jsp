<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-header">
	<c:if test="${currentUser == null}">
		<a href="<%= request.getContextPath() %>">Shopping Cart Application:</a>
	</c:if>
	<c:if test="${currentUser != null}">
		Welcome ${currentUser.name}
		<div style="float:right"><a href="<%= request.getContextPath() %>/logout" style="color:#000 !important">Logout</a></div>
	</c:if>
</div>

<div class="page-content">
<c:if test="${currentUser != null}">
<a href="<%= request.getContextPath() %>/product-list">Product List</a> | <a href="<%= request.getContextPath() %>/my-cart">My Cart</a>
</c:if>
