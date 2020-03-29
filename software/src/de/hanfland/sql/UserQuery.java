package de.hanfland.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserQuery {

	private MySQL mysql;
	
	public UserQuery(MySQL mysql) {
		this.mysql = mysql;
	}
	
	public void intializeTable() {
		try {
        	Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users(id int(16) NOT NULL AUTO_INCREMENT, username VARCHAR(255), password LONGTEXT, classid int(4), primary key (id));");
            stmt.close();
		} catch (SQLException e) {
		      	 e.printStackTrace();
		}
	}
	
	
	public boolean create(String username, String password, int classid) {
		 String sql = "INSERT INTO users(username, password, classid) VALUES (?,?,?);";		 
	     try {
	    	 PreparedStatement pstmt = mysql.getConnection().prepareStatement(sql);
	       	 pstmt.setString(1, username);
	       	 pstmt.setString(2, password);
	       	 pstmt.setInt(3, classid);
	       	 pstmt.executeUpdate();
	         pstmt.close();
	       	 return true;
	     } catch (SQLException e) {
	       	 e.printStackTrace();
	 	     return false;
	     }
	 }
	
	public boolean exist(String name) {
		 boolean ret = true;
		 String sql = "SELECT * FROM users WHERE username=?;";
	     try {
	        	PreparedStatement stmt = mysql.getConnection().prepareStatement(sql);
	        	stmt.setString(1, name);
	            ResultSet rs = stmt.executeQuery();
	            ret = rs.next();
	            rs.close();
	            stmt.close();
	        } catch (SQLException e) {
	        	 e.printStackTrace();
	 			return false;
	        }
		 return ret;
	 }
	
}
