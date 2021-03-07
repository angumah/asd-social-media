package application;

import media.*;

import DAO.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	private UserDAO uDao;
	private User user;
	
	public void init() {
		final String url = getServletContext().getInitParameter("JDBC-URL");
		final String username = getServletContext().getInitParameter("JDBC-USERNAME");
		final String password = getServletContext().getInitParameter("JDBC-PASSWORD");
		
		uDao = new UserDAO(url, username, password);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		final String action = request.getServletPath();
		
		try {
			switch (action) {
				case "/add" :
				case "/edit": showEditForm(request, response); break;
				case "/success": viewUsers(request, response); break;
				case "/invalid": invalidLogin(request, response); break;
				case "/login": userLogin(request, response); break;
				case "/insert": insertUser(request, response); break;
				case "/submitLogin": login(request, response); break;
				case "/update": updateUser(request, response); break;
				case "/view": viewUser(request, response); break;
				case "/logout": logOut(request, response); break;
				case "/back": back(request, response); break;
				default : signUp(request, response); break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
	
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		Part filePart = request.getPart("uploadFile");
		InputStream in = filePart.getInputStream();
		
		byte[] bytes = in.readAllBytes();
		
		
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		long phone = Long.parseLong(request.getParameter("phone"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));
		int year = Integer.parseInt(request.getParameter("year"));
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String college = request.getParameter("college");
		String highschool = request.getParameter("highschool");
		
		uDao.insertUser(fName, lName, email, phone, username, password, month, day, year, city, state, zip, college, highschool, bytes );
		response.sendRedirect(request.getContextPath() + "/login");
	}
	private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = uDao.getUser(username);
		
		if(user != null) {
		
			if(user.getPassword().equals(password)) {
				response.sendRedirect(request.getContextPath() + "/success");
			} else {
				response.sendRedirect(request.getContextPath() + "/invalid");
			 }
		}
		 else {
			response.sendRedirect(request.getContextPath() + "/invalid");
		 }
		this.user = user;
		long time;
		Date loginDate = new Date();
		
		time = loginDate.getTime();
		
		Timestamp timestamp = new Timestamp(time);
		
		uDao.updateLogin(user, timestamp);
		

	}
	
	private void invalidLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("invalid.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void viewUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		List<User> users = uDao.getUsers();
		request.setAttribute("users", users);
	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("profiles.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		int id = user.getId();
		Timestamp ts = uDao.getLastEdit(id);
		
		
		
		String lastEdit = "Never Edited";
		
		if(ts != null) {
			lastEdit= ts.toGMTString();
		}
		request.setAttribute("lastEdit", lastEdit);
		
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("editForm.jsp");
		dispatcher.forward(request, response);

	}
	
	private void viewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
		User viewUser = uDao.getUser(id);
		
		Timestamp ts = uDao.getLastlogin(id);
		
		String lastLogin = ts.toGMTString();
		request.setAttribute("viewUser", viewUser);
		request.setAttribute("lastLogin", lastLogin);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewProfile.jsp");
		dispatcher.forward(request, response);
	}
	
	private void logOut(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		this.user = null;
		response.sendRedirect("login.jsp");
	}
	
	private void back(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		this.user = null;
		response.sendRedirect("profiles.jsp");
	}
	
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		long phone = Long.parseLong(request.getParameter("phone"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));
		int year = Integer.parseInt(request.getParameter("year"));
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String college = request.getParameter("college");
		String highschool = request.getParameter("highschool");
		
		user.setFName(fName);
		user.setLName(lName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setUsername(username);
		user.setPassword(password);
		user.setMonth(month);
		user.setDay(day);
		user.setYear(year);
		user.setCity(city);
		user.setState(state);
		user.setZip(zip);
		user.setColleg(college);
		user.setHighschool(highschool);
		
		Part filePart = request.getPart("uploadFile");
		
		
		if(filePart != null) {
			InputStream in = filePart.getInputStream();
			
			byte[] bytes = in.readAllBytes();
			String b64 = Base64.getEncoder().encodeToString(bytes);
			user.setPicture(b64);
			uDao.updatePicture(user);
		}
		long time;
		Date loginDate = new Date();
		
		time = loginDate.getTime();
		
		Timestamp timestamp = new Timestamp(time);
		
		uDao.updateUser(user);
		uDao.updateEdit(user, timestamp);
		response.sendRedirect(request.getContextPath() + "/success");
	}
}
