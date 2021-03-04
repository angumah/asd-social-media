<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="media.User"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<style type="text/css">
    	<%@ include file="css/success.css" %>
    </style>
<title>Media</title>
</head>
<body>
    <div id='pictureDiv'>
        <img src=''>
    </div>

    <div id='contentDiv'>
    <c:if test="${user != null }">
    	<p><c:out value="${user.username }"></c:out></p>
    	<p><c:out value="${user.FName }"></c:out> <c:out value="${user.LName }"></c:out></p>
    	<p><c:out value="${user.modFollowers }"></c:out></p>
    </c:if>
        <a href='${pageContext.request.contextPath}/edit'>Edit</a>
    </div>
   
</body>
</html>