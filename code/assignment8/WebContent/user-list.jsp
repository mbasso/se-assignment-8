<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>User Management Application</title>
</head>
<body>
	<center>
		<h1>User Management</h1>
        <h2>
        	<a href="new">Add New User</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Users</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
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
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.getUsername()}" /></td>
                    <td><c:out value="${user.getPassword()}" /></td>
                    <td><c:out value="${user.getName()}" /></td>
                    <td><c:out value="${user.getBestFriend() != null ? user.getBestFriend().getUsername() : \"\"}" /></td>
                    <td><c:out value="${user.getAddress().getCountry()}" /></td>
                    <td><c:out value="${user.getAddress().getCity()}" /></td>
                    <td><c:out value="${user.getAddress().getStreet()}" /></td>
                    <td>
                    	<a href="edit?username=<c:out value='${user.getUsername()}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?username=<c:out value='${user.getUsername()}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
