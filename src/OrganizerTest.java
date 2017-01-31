import java.util.List;

import model.Manipulation;
import model.Record;

public class OrganizerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manipulation manipulation = new Manipulation();
		manipulation.insertRecord("Test 1, 2", "Test zawartoœci 1, 2");
		manipulation.insertRecord("Test 3, 4", "Test zawartoœci 3, 4");

		List<Record> records = manipulation.selectRecord();

		System.out.println("The list of records: ");
		for(Record r: records){
			System.out.println("ID: " + r.getID() + "; Title: " + r.getTitle());
			System.out.println("Content: " + r.getContent() + "\n");
		}
		manipulation.dropRecord();
		manipulation.closeConnection();
	}

}
