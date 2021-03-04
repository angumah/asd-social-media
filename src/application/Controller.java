package application;

import media.*;
import DAO.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import java.util.List;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
				case "/success": showUserAccount(request, response); break;
				case "/invalid": invalidLogin(request, response); break;
				case "/login": userLogin(request, response); break;
				case "/insert": insertUser(request, response); break;
				case "/submitLogin": login(request, response); break;
				case "/update": updateUser(request, response); break;
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
		
		uDao.insertUser(fName, lName, email, phone, username, password, month, day, year, city, state, zip, college, highschool, in);
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

	}
	
	private void invalidLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("invalid.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showUserAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BufferedImage picture = user.getImage();
		request.setAttribute("user", user);
		request.setAttribute("picture", picture);
		RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("editForm.jsp");
		dispatcher.forward(request, response);

	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		Part filePart = request.getPart("uploadFile");
		InputStream in = filePart.getInputStream();
		
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
		
		user.setPicture(in);
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
	
	
		uDao.updateUser(user);
		response.sendRedirect(request.getContextPath() + "/success");
	}
}
