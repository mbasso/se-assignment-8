<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Assignment 8</title>
</head>
<body>

	<h1>
		<c:if test="${user != null}">
			Update user
		</c:if>
		<c:if test="${user == null}">
			New user
		</c:if>
	</h1>
	<p>
        <a href="new">Add New User</a>
	</p>
	<p>
		<a href="list">User list</a>
	</p>
	
	<c:if test="${user != null}">
		<form action="edit" method="post">
   	</c:if>
    <c:if test="${user == null}">
    	<form action="new" method="post">
    </c:if>
	<p>
	<label for="username">Username</label><input type="text" name="username" value="<c:out value='${user.getUsername()}' />" <c:if test="${user != null}"><c:out value="readonly='true'"/></c:if>><br>
	<label for="password">Password</label><input type="password" name="password" value="<c:out value='${user.getPassword()}' />"><br>
	<label for="name">Name</label><input type="text" name="name" value="<c:out value='${user.getName()}' />"><br>
	<label for="country">Country</label><input type="text" name="country" value="<c:out value='${user.getAddress().getCountry()}' />"><br>
	<label for="city">City</label><input type="text" name="city" value="<c:out value='${user.getAddress().getCity()}' />"><br>
	<label for="street">Street</label><input type="text" name="street" value="<c:out value='${user.getAddress().getStreet()}' />"><br>
	</p>
	
	<button type="submit" name="command" value="auth.Login">
		<c:if test="${user != null}">
			Update
		</c:if>
		<c:if test="${user == null}">
			Add
		</c:if>
	</button>
	</form>
	
    <br/>
	<p>
		Go <a href="/assignment8/">back</a>.
	</p>
	<p>
		<a href="/assignment8/logout">Logout</a>.
	</p>
</body>
</html>