package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User{

	private static Connection connection = DB_Connection.getConnection();;

	public static void createTable(String userName){
		String createUser = "CREATE TABLE IF NOT EXISTS " + userName + " (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(255), content VARCHAR(255),"
				+ "mydate DATETIME, destDate DATE)";
		try {
			System.out.println(userName);
			PreparedStatement prepStatement = connection.prepareStatement(createUser);
			System.out.println(prepStatement);
			boolean whateva = prepStatement.execute();
			System.out.println(whateva);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't make the create statement");
			e.printStackTrace();
		}
	}

	public static void dropUser(String userName){
		String dropRecords = "DROP TABLE IF EXISTS " + userName;
		try {
			PreparedStatement prepStatement = connection.prepareStatement(dropRecords);
			prepStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't drop the table");
			e.printStackTrace();
		}
	}

}
