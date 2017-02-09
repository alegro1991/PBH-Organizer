package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.LinkedList;

public class Manipulation {

	private Statement statement;
	List<User> userList = new LinkedList<User>();
	private Connection connection;

	public Manipulation(){
		try {
			connection = DB_Connection.getConnection();
			statement = DB_Connection.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't connect to database to manipulate");
			e.printStackTrace();
		}
	}

	public void createUser(String userName){
		User.createTable(userName);
	}

	public void showUsers(){
		String selectUsers = "SELECT * FROM sqlite_master WHERE type = 'table'";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(selectUsers);
			ResultSet rs = prepStatement.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't select users");
			e.printStackTrace();
		}
	}

	public void dropUser(String userName){
		DatabaseMetaData metaData;
		try {
			metaData = connection.getMetaData();
			ResultSet tables = metaData.getTables(null, null, userName, null);
			if(tables.next()){
				tables.close();
				User.dropUser(userName);
			}
			else{
				System.out.println("There's no such user");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't apply the metadata");
			e.printStackTrace();
		}
	}



	public boolean insertRecord(String title, String content, LocalDate ldate, String name){
		try {
			System.out.println(connection);
			PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO " + name + " VALUES (NULL, ?, ?, ?, ?)");
			prepStatement.setString(1, title);
			prepStatement.setString(2, content);
			Instant insertOccured = Instant.now();
			prepStatement.setTimestamp(3, Timestamp.from(insertOccured));
			Date date = Date.valueOf(ldate);
			prepStatement.setDate(4, date);
			prepStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't make the insert statement");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void deleteRecord(String name, int id){
		String delete = "DELETE FROM " + name + " WHERE id = ?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(delete);
			prepStatement.setInt(1, id);
			prepStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Record> selectRecord(String name){
		List<Record> records = new LinkedList<Record>();
		try {
			ResultSet result = statement.executeQuery("SELECT * FROM " + name);
			int id;
			String title, content;
			Instant insertOccured;
			LocalDate date;
			while(result.next()){
				id = result.getInt(1);
				title = result.getString(2);
				content = result.getString(3);
				insertOccured = result.getTimestamp(4).toInstant();
				date = result.getDate(5).toLocalDate();
				records.add(new Record(id, title, content, insertOccured, date));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't retrieve the rows");
			e.printStackTrace();
			return null;
		}
		return records;
	}

	public void showRecords(String name){
		List<Record> records = selectRecord(name);
		System.out.println("The list of records: ");
		for(Record r: records){
			System.out.println("ID: " + r.getID() + "; Title: " + r.getTitle());
			System.out.println("Content: " + r.getContent() + "\n");
			System.out.println("Date and time: " + r.getDateTime());
			System.out.println("Planned date: " + r.getDate());
		}
	}

}
