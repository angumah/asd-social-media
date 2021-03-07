<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<style type="text/css">
    	<%@ include file="css/view.css" %>
    </style>
<title>Media</title>
</head>
<body>
	<a href='${pageContext.request.contextPath}/success'>Home</a>
    

    <div id='contentDiv'>
    <c:if test="${viewUser != null }">
        <img src='data:image/jpg;base64,${viewUser.picture}'>
    	<p><c:out value="${viewUser.username }"></c:out></p>
    	<p><c:out value="${viewUser.FName }"></c:out> <c:out value="${user.LName }"></c:out></p>
    	<p>Followers : <c:out value="${viewUser.followers }"></c:out></p>
    	<p>Birthday : <c:out value="${viewUser.month }"></c:out>/<c:out value="${viewUser.day }">/<c:out value="${viewUser.year }"></c:out></c:out></p>
    	<p>Address : <c:out value="${viewUser.city }"></c:out>, <c:out value="${viewUser.state }"></c:out>, <c:out value="${viewUser.zip }"></c:out></p>
    	<p>Highschool : <c:out value="${viewUser.highSchool }"></c:out></p>
    	<p>College : <c:out value="${viewUser.college }"></c:out></p>
    	<p>Phone : <c:out value="${viewUser.phone}"></c:out></p>
    </c:if>
    	<p>Last logged in : <c:out value="${lastLogin}"></c:out> </p>
    </div>
   
</body>
</html>