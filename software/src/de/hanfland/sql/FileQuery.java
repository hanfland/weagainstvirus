package de.hanfland.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileQuery {

	private MySQL mysql;
	
	public FileQuery(MySQL mysql) {
		this.mysql = mysql;
	}
	
	public void intializeTable() {
		try {
        	Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS files(id int(16) NOT NULL AUTO_INCREMENT, path VARCHAR(255), name VARCHAR(255), description TEXT, themeid INT(5), date VARCHAR(64), primary key (id));");
            stmt.close();
		} catch (SQLException e) {
		      	 e.printStackTrace();
		}
	}
	
	public String date() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date currentTime = new Date();
        return formatter.format(currentTime);
	}
	
	
	public boolean create(String name, String path, String description, int themeid) {
		 String sql = "INSERT INTO files(path,name,description,themeid,date) VALUES (?,?,?,?,?);";		 
	     try {
	    	 PreparedStatement pstmt = mysql.getConnection().prepareStatement(sql);
	       	 pstmt.setString(1, name);
	       	 pstmt.setString(2, path);
	       	 pstmt.setString(3, description);
	       	 pstmt.setInt(4, themeid);
	       	 pstmt.setString(5, date());
	       	 pstmt.executeUpdate();
	         pstmt.close();
	       	 return true;
	     } catch (SQLException e) {
	       	 e.printStackTrace();
	 	     return false;
	     }
	}
	
	
	/*
	public FileData[] getFiles(int themeid) {
		String sql = "SELECT * FROM files WHERE themeid=?;";
		List<String> list = new ArrayList<String>();
		 try {
			 PreparedStatement pstmt = mysql.getConnection().prepareStatement(sql);
	       	 pstmt.setInt(1, themeid);
			 ResultSet rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 list.add(rs.getString("name"));
			 }
			 pstmt.close();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		
		
		return toArray(list);
	}
	*/
	public boolean exist(String path) {
		 boolean ret = true;
		 String sql = "SELECT * FROM files WHERE path=?;";
	     try {
	        	PreparedStatement stmt = mysql.getConnection().prepareStatement(sql);
	        	stmt.setString(1, path);
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