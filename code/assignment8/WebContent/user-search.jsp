<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Assignment 8</title>
</head>
<body>

    <c:set var = "user" scope = "session" value = "${searchHelper.getUser()}"/>
	<h1>Search user</h1>
	<p>
        <a href="new">Add New User</a>
	</p>
	<p>
		<a href="list">User list</a>
	</p>
	<p>
		<a href="search">Search user</a>
	</p>

	<form action="search" method="post">
	<p>
	<label for="username">Username</label><input type="text" name="username" value="<c:out value='${user != null ? user.getUsername() : \"\"}' />"><br>
	<label for="name">Name</label><input type="text" name="name" value="<c:out value='${user != null ? user.getName() : \"\"}' />"><br>
	<label for="bestfriend">Best Friend</label>
	<select name="bestfriend">
		<option value=""></option>
		<c:forEach var="uzer" items="${userHelper.getUsers()}">
			<c:if test="${!uzer.equals(user)}">
				<option
					value="${uzer.getUsername()}"
				>
					${uzer.getUsername()}
				</option>
			</c:if>
		</c:forEach>
	</select><br>
	<label for="country">Country</label><input type="text" name="country" value="<c:out value='${user != null && user.getAddress() != null ? user.getAddress().getCountry() : \"\"}' />"><br>
	<label for="city">City</label><input type="text" name="city" value="<c:out value='${user != null && user.getAddress() != null ? user.getAddress().getCity() : \"\"}' />"><br>
	<label for="street">Street</label><input type="text" name="street" value="<c:out value='${user != null && user.getAddress() != null ? user.getAddress().getStreet() : \"\"}' />"><br>
	</p>
	
	<button type="submit" name="command" value="auth.Login">
		Search
	</button>
	</form>
	
	<c:if test="${searchHelper.getUsers() != null}">
		<br/>
		<table border="1" cellpadding="5">
	        <caption><h2>Users</h2></caption>
	        <tr>
	            <th>Username</th>
	            <th>Password</th>
	            <th>Name</th>
	            <th>Best Friend</th>
	            <th>Country</th>
	            <th>City</th>
	            <th>Street</th>
	            <th>Actions</th>
	        </tr>
	        <c:forEach var="searchedUser" items="${searchHelper.getUsers()}">
	            <tr>
	                <td><c:out value="${searchedUser.getUsername()}" /></td>
	                <td><c:out value="${searchedUser.getPassword()}" /></td>
	                <td><c:out value="${searchedUser.getName()}" /></td>
	                <td><c:out value="${searchedUser.getBestFriend() != null ? searchedUser.getBestFriend().getUsername() : \"\"}" /></td>
	                <td><c:out value="${searchedUser.getAddress().getCountry()}" /></td>
	                <td><c:out value="${searchedUser.getAddress().getCity()}" /></td>
	                <td><c:out value="${searchedUser.getAddress().getStreet()}" /></td>
	                <td>
	                	<a href="edit?username=<c:out value='${searchedUser.getUsername()}' />">Edit</a>
	                	&nbsp;&nbsp;&nbsp;&nbsp;
	                	<c:if test="${!authHelper.getUsername().equals(searchedUser.getUsername())}">
	                		<a href="delete?username=<c:out value='${searchedUser.getUsername()}' />">Delete</a>  
	                	</c:if>                  	
	                </td>
	            </tr>
	        </c:forEach>
	    </table>
    </c:if> 
	
    <br/>
	<p>
		Go <a href="/assignment8/">back</a>.
	</p>
	<p>
		<a href="/assignment8/logout">Logout</a>.
	</p>
</body>
</html>