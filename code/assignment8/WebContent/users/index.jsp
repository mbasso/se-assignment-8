<%@ page import="view.auth.AuthHelper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Authentication Example</title>

<style>
	table{
	  	border-collapse: collapse;
	}
	table td {
		border: 1px solid #AAA;
		padding: 5px;
	}
</style>
</head>

<body>
	<h2>Users list</h2>
	<c:choose>
		<c:when test="${users != null && users.size() > 0}">
			<table>
				<thead>
					<tr>
						<td>Username</td>
						<td>Password</td>
						<td>Name</td>
						<td>Email</td>
						<td>City</td>
						<td>Role</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="item">
						<tr>
							<td><c:out value = "${item.getUsername()}"/></td>
							<td><c:out value = "${item.getPassword()}"/></td>
							<td><c:out value = "${item.getName()}"/></td>
							<td><c:out value = "${item.getEmail()}"/></td>
							<td><c:out value = "${item.getCity()}"/></td>
							<td><c:out value = "${item.getRole()}"/></td>
						</tr>
				    </c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			No users registered.
		</c:otherwise>
	</c:choose>
	
	<p>
		Go <a href="/assignment8/">back</a>.
	</p>	
</body>
</html>