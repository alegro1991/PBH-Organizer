package controler;

import java.time.LocalDate;
import java.util.Scanner;

import model.User;

public class Interaction {

	private int day, month, year;
	private Scanner in = new Scanner(System.in);
	private LocalDate date;


	public LocalDate question(){
		System.out.println("Day of event?");
		day = in.nextInt();
		System.out.println("Month of event?");
		month = in.nextInt();
		System.out.println("Year of event?");
		year = in.nextInt();

		date = LocalDate.of(year, month, day);

		return date;
	}

	public int preWorkflow(){
		System.out.println("What to do?");
		System.out.println("1 - Log user\n2 - Create user");
		int choice = in.nextInt();
		return choice;
	}

	public int workflow(){
		System.out.println("\nWhat to do?");
		System.out.println("2 - Show users\n3 - Drop user\n4 - Proceed with data"
				+ "\n5 - Show records\n6 - Delete records");
		int choice = in.nextInt();
		return choice;
	}

	public String nameUser(String message){
		System.out.println(message);
		String name = in.next();
		return name;
	}

	public String createContent(String message){
		System.out.println(message);
		in.nextLine();
		String content = in.nextLine();
		return content;
	}

	public int idRecord(){
		System.out.println("Which record do you want to remove?");
		int id = in.nextInt();
		return id;
	}




}
