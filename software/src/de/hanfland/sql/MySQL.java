package de.hanfland.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
	
	private Connection connection;
	final private String url;
	final private String user;
	final private String password;
	
	public MySQL(String hostname, String port, String database, String user, String password) {
	    System.out.println("* Verbindung aufbauen"); 
		this.url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
		this.user = user;
		this.password = password;
	}

	public Connection getConnection() {
		return this.connection;
	}
	
	public void open() {
	    try {
			this.connection = DriverManager.getConnection(this.url, this.user, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
	    if(this.connection != null) {
	    	System.out.println("* Datenbank-Verbindung beenden"); 
		    try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	    }
	}
	
}
