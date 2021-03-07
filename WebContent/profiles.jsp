<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<style type="text/css">
    	<%@ include file="css/profiles.css" %>
    </style>
<title>Media</title>
</head>
<body>
	<a href='${pageContext.request.contextPath}/logout'>Logout</a>
	<a id ='edit' href='${pageContext.request.contextPath}/edit'>Edit</a>
    <div id='profileDiv'>
    	<table>
    	<c:forEach var="user" items="${users}">
    		<tr>
    			<td><img src='data:image/jpg;base64,${user.picture}'></td>
    			<td id='user'><a href='${pageContext.request.contextPath}/view?id=${user.id}'><c:out value="${user.username}"></c:out></a></td>
    			<td id='followers'><c:out value="${user.followers}"></c:out> </td>
    		</tr>
    	</c:forEach>
    	</table>
    </div>
   
</body>
</html>