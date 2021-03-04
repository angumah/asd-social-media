package DAO;

import media.User;


import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import java.io.InputStream;


public class UserDAO {
	private final String url;
	private final String username;
	private final String password;
	
	public UserDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public User getUser(int id) throws SQLException {
		User user = null;
		final String sql = "SELECT * FROM profiles WHERE user_id = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			String fName = rs.getString("fName");
			String lName = rs.getString("lName");
			String email = rs.getString("email");
			long phone = rs.getLong("phone_number");
			String username = rs.getString("username");
			String password = rs.getString("password");
			int month = rs.getInt("month");
			int day = rs.getInt("day");
			int year = rs.getInt("year");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String zip = rs.getString("zip");
			String college = rs.getString("colege");
			String highSchool = rs.getString("high_school");
			InputStream picture = rs.getBinaryStream("picture");
			boolean isPublic;
			if(rs.getString("status").equals("public")) {
				isPublic = true;
			} else {
				isPublic = false;
			}
			int followers = rs.getInt("followers");
	
			user = new User(id, fName, lName, email, phone, username, password, month, day, year, city, state, zip, college, highSchool, followers, isPublic, picture);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return user;
	}
	
	public User getUser(String username) throws SQLException {
		User user = null;
		final String sql = "SELECT * FROM profiles WHERE username = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, username);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			int id = rs.getInt("user_id");
			String fName = rs.getString("fName");
			String lName = rs.getString("lName");
			String email = rs.getString("email");
			long phone = rs.getLong("phone_number");
			String password = rs.getString("password");
			int month = rs.getInt("month");
			int day = rs.getInt("day");
			int year = rs.getInt("year");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String zip = rs.getString("zip");
			String college = rs.getString("college");
			String highSchool = rs.getString("high_school");
			InputStream picture = rs.getBinaryStream("picture");
			boolean isPublic;
			if(rs.getString("status").equals("public")) {
				isPublic = true;
			} else {
				isPublic = false;
			}
			int followers = rs.getInt("followers");
			
			user = new User(id, fName, lName, email, phone, username, password, month, day, year, city, state, zip, college, highSchool, followers, isPublic, picture);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return user;
	}
	
	public List<User> getUsers() throws SQLException{
		final String sql = "SELECT * FROM profiles";
		List<User> users = new ArrayList<>();
		Connection conn = getConnection();
		Statement stmt  = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt("user_id");
			String fName = rs.getString("fName");
			String lName = rs.getString("lName");
			String email = rs.getString("email");
			long phone = rs.getLong("phone_number");
			String username = rs.getString("username");
			String password = rs.getString("password");
			int month = rs.getInt("month");
			int day = rs.getInt("day");
			int year = rs.getInt("year");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String zip = rs.getString("zip");
			String college = rs.getString("colege");
			String highSchool = rs.getString("high_school");
			InputStream picture = rs.getBinaryStream("picture");
			boolean isPublic;
			if(rs.getString("status").equals("public")) {
				isPublic = true;
			} else {
				isPublic = false;
			}
			int followers = rs.getInt("followers");
			
			users.add(new User(id, fName, lName, email, phone, username, password, month, day, year, city, state, zip, college, highSchool, followers, isPublic, picture));
		}
		rs.close();
		stmt.close();
		conn.close();
		
		return users;
	}
	
	public boolean insertUser(String fName, String lName, String email, long phone, String username, String password, int month, 
			int day, int year, String city, String state, String zip, String college, String highSchool, InputStream picture) throws SQLException {
		
		final String sql = "INSERT INTO profiles (fName, lName, email, phone_number, username, password, month, day, year, city, state, zip, college, high_school, followers, status, picture) " +
							"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, fName);
		pstmt.setString(2, lName);
		pstmt.setString(3, email);
		pstmt.setLong(4, phone);
		pstmt.setString(5, username);
		pstmt.setString(6, password);
		pstmt.setInt(7, month);
		pstmt.setInt(8, day);
		pstmt.setInt(9, year);
		pstmt.setString(10, city);
		pstmt.setString(11, state);
		pstmt.setString(12, zip);
		pstmt.setString(13, college);
		pstmt.setString(14, highSchool);
		pstmt.setInt(15, 0);
		pstmt.setString(16, "public");
		pstmt.setBlob(17, picture);
		
		int affected = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
		return affected == 1;
	}
	
	public boolean updateUser(User user) throws SQLException{
		final String sql = "UPDATE profiles SET fName = ?, lName = ?, email = ?, phone_number = ?, username = ?, password = ?, month = ?, day = ?, year = ?, city = ?, state = ?, zip = ?, college = ?, high_school = ?, followers = ?, status = ?, picture = ? "
				+ "WHERE user_id = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, user.getFName());
		pstmt.setString(2, user.getLName());
		pstmt.setString(3, user.getEmail());
		pstmt.setLong(4, user.getPhone());
		pstmt.setString(5, user.getUsername());
		pstmt.setString(6, user.getPassword());
		pstmt.setInt(7, user.getMonth());
		pstmt.setInt(8, user.getDay());
		pstmt.setInt(9, user.getYear());
		pstmt.setString(10, user.getCity());
		pstmt.setString(11, user.getState());
		pstmt.setString(12, user.getZip());
		pstmt.setString(13, user.getCollege());
		pstmt.setString(14, user.getHighSchool());
		pstmt.setInt(15, user.getFollowers());
		pstmt.setString(16, user.getStatus());
		pstmt.setBlob(17, user.getPicture());
		pstmt.setInt(18, user.getId());
		int affected = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return affected == 1;
		
	}
	
	public boolean deleteUser(User user) throws SQLException{
		final String sql = "DELETE FROM profiles WHERE user_id = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, user.getId());
		
		int affected = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return affected == 1;
	}
	
	
	
	private Connection getConnection() throws SQLException {
		final String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(url, username, password);
	}
}
