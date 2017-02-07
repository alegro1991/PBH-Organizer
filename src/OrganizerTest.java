import java.time.LocalDate;

import model.Manipulation;
import view.Interaction;

public class OrganizerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name;
		Manipulation manipulation = new Manipulation();
		Interaction inter = new Interaction();
		int choice = inter.workflow();
		while(choice >= 1 && choice <= 4){
			switch(choice){
				case 1:
					name = inter.nameUser("What's your name?");
					manipulation.createUser(name);
					break;
				case 2:
					manipulation.showUsers();
					break;
				case 3:
					name = inter.nameUser("Who do you want to remove?");
					manipulation.dropUser(name);
					break;
				case 4:
					name = inter.nameUser("Which user?");
					LocalDate date = inter.question();
					String title = inter.nameUser("What's the title");
					String content = inter.createContent("What's the content?");
					manipulation.insertRecord(title, content, date, name);
					manipulation.showRecords(name);
					break;
				default:
					break;
			}
		}
	}
}
