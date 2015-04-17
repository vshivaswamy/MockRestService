<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h1>My company Users</h1>
<table border="1">
	<tr>
		<th>User Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Email</th>
		<th>Phone</th>
	</tr>
	<c:forEach items="${users}" var="user">
		<tr>
			<td><c:out value="${user.id}" /></td>
			<td><c:out value="${user.firstName}" /></td>
			<td><c:out value="${user.lastName}" /></td>
			<td><c:out value="${user.email}" /></td>
			<td><c:out value="${user.phone}" /></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>