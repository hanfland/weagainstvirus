package de.hanfland.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassQuery {

	private MySQL mysql;
	
	public ClassQuery(MySQL mysql) {
		this.mysql = mysql;
	}
	
	public void intializeTable() {
		try {
        	Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS classes(id int(5) NOT NULL AUTO_INCREMENT, name VARCHAR(255), primary key (id));");
            stmt.close();
		} catch (SQLException e) {
		      	 e.printStackTrace();
		}
	}
	
	
	public boolean create(String name) {
		 String sql = "INSERT INTO classes(name) VALUES (?);";		 
	     try {
	    	 PreparedStatement pstmt = mysql.getConnection().prepareStatement(sql);
	       	 pstmt.setString(1, name);
	       	 pstmt.executeUpdate();
	         pstmt.close();
	       	 return true;
	     } catch (SQLException e) {
	       	 e.printStackTrace();
	 	     return false;
	     }
	}
	
	public String[] getClassNames() {
		String sql = "SELECT name FROM classes;";
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
		
		
		return toArray(list);
	}
	
	public boolean exist(int id) {
		 boolean ret = true;
		 String sql = "SELECT name FROM classes WHERE id=?;";
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
		 String sql = "SELECT id FROM classes WHERE name=?;";
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
		String sql = "SELECT id FROM classes WHERE name=?;";
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