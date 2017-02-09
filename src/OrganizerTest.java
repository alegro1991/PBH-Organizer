import java.time.LocalDate;

import model.DB_Connection;
import model.Manipulation;
import controler.Interaction;
import controler.UserHandler;

public class OrganizerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name;
		Manipulation manipulation = new Manipulation();
		Interaction inter = new Interaction();
		UserHandler uHandler = new UserHandler();
		int choice = inter.preWorkflow();
		if(choice == 2){
			String newUser = uHandler.createUser();
			manipulation.createUser(newUser);
		}
		else if(choice == 1){
			name = uHandler.logUser();
			if(name != null){
				do{
					choice = inter.workflow();
					switch(choice){
						case 2:
							manipulation.showUsers();
							break;
						case 3:
							manipulation.dropUser(name);
							uHandler.destroyUser(name);
							System.out.println("The user has been destroyed");
							DB_Connection.closeConnection();
							System.exit(0);
							break;
						case 4:
							LocalDate date = inter.question();
							String title = inter.nameUser("What's the title");
							String content = inter.createContent("What's the content?");
							manipulation.insertRecord(title, content, date, name);
							manipulation.showRecords(name);
							break;
						case 5:
							manipulation.showRecords(name);
							break;
						case 6:
							int id = inter.idRecord();
							manipulation.deleteRecord(name, id);
						default:
							break;
					}
				}while(choice >= 2 && choice <= 6);
			}
		}
		DB_Connection.closeConnection();
	}
}
