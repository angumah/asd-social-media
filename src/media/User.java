package media;

import java.io.InputStream;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class User {
	private boolean isPublic;
	private int id;
	private String fName;
	private String lName;
	private String email;
	private long phone;
	private String username;
	private String password;
	private int month;
	private int day;
	private int year;
	private String city;
	private String state;
	private String zip;
	private String college;
	private String highSchool;
	private int followers;
	
	private InputStream picture;
	
	
	
	public User(int id, String fName, String lName, String email, long phone, String username, String password, int month, 
			int day, int year, String city, String state, String zip, String college, String highSchool, int followers, boolean isPublic, InputStream picture) {
		
		this.id = id;
		this.isPublic = isPublic;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.month = month;
		this.day = day;
		this.year = year;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.college = college;
		this.highSchool = highSchool;
		this.followers = followers;
		this.picture = picture;
	}
	
	
	//get data methods
	public int getId() {
		return this.id;
	}
	
	public String getFName() {
		return this.fName;
	}
	public String getLName() {
		return this.lName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public long getPhone() {
		return this.phone;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getDay() {
		return this.day;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getZip() {
		return this.zip;
	}
	
	public String getCollege() {
		return this.college;
	}
	
	public String getHighSchool() {
		return this.highSchool;
	}
	
	public int getFollowers() {
		return this.followers;
	}
	
	
	public String getStatus() {
		if(isPublic) {
			return "public";
		} 
		return "private";
	}
	
	public InputStream getPicture() {
		return this.picture;
	}
	
	public BufferedImage getImage() throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		 
        byte[] buffer = new byte[1024];
        int len;
 
        // read bytes from the input stream and store them in buffer
        while ((len = this.picture.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
        }
 
        byte[] ba = os.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(ba);
        BufferedImage picture = ImageIO.read(bis);
        ImageIO.write(picture, "jpg", new File("output.jpg") );
        return picture;
	}
	
	//set data methods
	
	public void setFName(String fName) {
		this.fName = fName;
	}
	
	public void setLName(String lName) {
		this.lName = lName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public void setColleg(String college) {
		this.college = college;
	}
	
	public void setHighschool(String highSchool) {
		this.highSchool = highSchool;
	}
	
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	
	public void setPicture(InputStream in) {
		this.picture = in;
	}
}
