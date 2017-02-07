package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {

	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DATABASE = "jdbc:sqlite:database.db";

	static Connection connection;

	private DB_Connection(){
		try {
			Class.forName(DB_Connection.DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("There's no driver for DBMS");
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(DATABASE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't connect to database");
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		if(connection == null){
			new DB_Connection();
		}
		return connection;
	}

	public static void closeConnection(){
		try {
			if(connection != null){
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
