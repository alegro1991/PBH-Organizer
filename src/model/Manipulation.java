package model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.LinkedList;

public class Manipulation {

	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DATABASE = "jdbc:sqlite:database.db";

	private Connection connection;
	private Statement statement;

	public Manipulation(){
		try {
			Class.forName(Manipulation.DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("There's no driver for DBMS");
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(DATABASE);
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't connect to database");
			e.printStackTrace();
		}

		createRecord();

	}

	public void createRecord(){
		String createRecords = "CREATE TABLE IF NOT EXISTS records (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(255), content VARCHAR(255))";
		try {
			statement.execute(createRecords);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't make the create statement");
			e.printStackTrace();
		}
	}

	public boolean insertRecord(String title, String content){
		try {
			PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO records VALUES (NULL, ?, ?)");
			prepStatement.setString(1, title);
			prepStatement.setString(2, content);
			prepStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't make the insert statement");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Record> selectRecord(){
		List<Record> records = new LinkedList<Record>();
		try {
			ResultSet result = statement.executeQuery("SELECT * FROM records");
			int id;
			String title, content;
			while(result.next()){
				id = result.getInt(1);
				title = result.getString(2);
				content = result.getString(3);
				records.add(new Record(id, title, content));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't retrieve the rows");
			e.printStackTrace();
			return null;
		}
		return records;
	}

	public void dropRecord(){
		String dropRecords = "DROP TABLE IF EXISTS records";
		try {
			statement.execute(dropRecords);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't close the connection");
			e.printStackTrace();
		}
	}

}
