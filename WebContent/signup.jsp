<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
    <title>Social-Media</title>
    <style type="text/css">
    	<%@ include file="css/signup.css" %>
    </style>
</head>
	<body>
		<h1 id='title'>Sign Up</h1>
		
        <form action='insert' method='post' id='signUpForm'  enctype="multipart/form-data">
        
        	<input id='uploadFile' type='file' name='uploadFile'><br>
          	<label>Profile</label><br><br>
          	
            <label class='left' id='fName_label' for='fName'>First Name</label>
            <input class='left' type="text" id='fName' name='fName'>

            <label class='middle' id='lName_label' for='lName'>Last Name</label>
            <input class='middle' type="text" id='lName' name='lName'>
            
            <label class='right' id='email_label' for='email'>Email</label>
            <input class='right' type="text" id='email' name='email'><br><br>

            <label class='left' id='phone_label' for='phone'>Phone Number</label>
            <input  class='left'type="text" id='phone' name='phone' min='1000000000' max ='9999999999'>

            <label class='middle' id='username_label' for='username'>Username</label>
            <input class='middle' type="text" id='username' name='username'>

            <label class='right' id='password_label' for='password'>Password</label>
            <input class='right' type="text" id='password' name='password'><br><br>

            <label class='bigLabel' id='birthdate_label' for='month day year'>Date of Birth</label><br><br>

            <label class='left' id='month_label' for='month'>Month</label>
            <input class='left' type="text" name='month' id='month'>

            <label class='middle' id='day_label' for='day'>Day</label>
            <input class='middle' type="text" id='day' name='day'>

            <label class='right' id='year_label' for='year'>Year</label>
            <input class='right' type="text" id='year' name='year'><br><br>

            <label class='bigLabel' id='address_label' for='city state zip'>Address</label><br><br>

            <label class='left' id='city_label' for='city'>City</label>
            <input class='left' type="text" id='city' name='city'>

            <label class='middle' id='state_label' for='state'>State</label>
            <input class='middle' type="text" id='state' name='state'>

            <label class='right' id='zip_label' for='zip'>Zip</label>
            <input class='right' type="text" id='zip' name='zip'><br><br>

            <label class='bigLabel' id='education_label' for='college highschool'>Education</label><br><br>

            <label class='left' id='college_label' for='college'>College</label>
            <input class='left' type="text" id='college' name='college'>

            <label class='right' id='highschool_label' for='highschool'>Highschool</label>
            <input class='right' type="text" id='highschool' name='highschool'>

            <input value='signup' type='submit' id='submit'>
        </form>
        <a id='loginButton' href="${pageContext.request.contextPath}/login">Login</a>
	</body>
</html>