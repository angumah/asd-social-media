<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<style type="text/css">
    	<%@ include file="css/login.css" %>
    </style>
<title>Log In</title>
</head>
<body>
    <div id='login_square'>
        <h1 id='title'>Log in</h1>
    </div>
    <div id='invalid_error'>
    	<p>Invalid username or password</p>
    </div>
    <c:forEach var="status" items="${status}">
    	<c:set value="${status}" var="status"></c:set>
	</c:forEach>
    <form action='submitLogin' method='post' id='login_form'>
        <label for='username' id='username_label'>Username</label>
        <input type='text' id='username' name='username'><br><br>
        <label for='password' id='password_label'>Password</label>
        <input type='password' id='password' name='password'><br><br>
        <input type="submit" value='submit' id='submit' name='submit'>
    </form>
    
    <a href="${pageContext.request.contextPath}/signup">Sign Up</a>

</body>
</html>