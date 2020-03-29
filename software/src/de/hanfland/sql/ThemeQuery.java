package de.hanfland.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.hanfland.main.School;

public class ThemeQuery {

	private MySQL mysql;
	
	public ThemeQuery(MySQL mysql) {
		this.mysql = mysql;
	}
	
	public void intializeTable() {
		try {
        	Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS themes(id int(5) NOT NULL AUTO_INCREMENT, name VARCHAR(255), pathname VARCHAR(255), classid INT(4), primary key (id));");
            stmt.close();
		} catch (SQLException e) {
		      	 e.printStackTrace();
		}
	}
	
	
	public boolean create(String name, int classid) {
		 String sql = "INSERT INTO themes(name, pathname, classid) VALUES (?,?,?);";		 
	     try {
	    	 PreparedStatement pstmt = mysql.getConnection().prepareStatement(sql);
	       	 pstmt.setString(1, name);
	       	 pstmt.setString(2, name.replaceAll(" ", "_"));
	       	 pstmt.setInt(3, classid);
	       	 pstmt.executeUpdate();
	         pstmt.close();
	       	 return true;
	     } catch (SQLException e) {
	       	 e.printStackTrace();
	 	     return false;
	     }
	}
	
	public String[] getThemesOf(String name) {
		String sql = "SELECT name FROM themes WHERE classid=?;";
		List<String> list = new ArrayList<String>();
		 try {
			 PreparedStatement pstmt = mysql.getConnection().prepareStatement(sql);
			 pstmt.setInt(0, School.classquery.getId(name));
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
	
	@SuppressWarnings("static-access")
	public String[] getThemes() {
		String sql = "SELECT name FROM themes;";
		List<String> list = new ArrayList<String>();
		 try {
			 PreparedStatement pstmt = mysql.getConnection().prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 list.add(rs.getString("name"));
			 }
			 pstmt.close();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		
		
		return this.toArray(list);
	}
	
	public boolean exist(int id) {
		 boolean ret = true;
		 String sql = "SELECT name FROM themes WHERE id=?;";
	     try {
	        	PreparedStatement stmt = mysql.getConnection().prepareStatement(sql);
	        	stmt.setInt(1, id);
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
	
	public boolean exist(String name) {
		 boolean ret = true;
		 String sql = "SELECT id FROM themes WHERE name=?;";
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
	
	public int getId(String name) {
		String sql = "SELECT id FROM themes WHERE name=?;";
		 try {
			 PreparedStatement pstmt = mysql.getConnection().prepareStatement(sql);
			 pstmt.setString(1, name);
			 ResultSet rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 return rs.getInt("id");
			 }
			 pstmt.close();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		
		
		return -1;
	}
	
    public static String[] toArray(List<String> arr){ 
        String str[] = new String[arr.size()]; 
        for (int j = 0; j < arr.size(); j++) {str[j] = arr.get(j);}
        return str; 
    } 
	
}