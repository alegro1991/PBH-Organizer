package view;

import java.time.LocalDate;
import java.util.Scanner;

import model.User;

public class Interaction {

	private int day, month, year;
	Scanner in = new Scanner(System.in);
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

	public int workflow(){
		System.out.println("What to do?");
		System.out.println("1 - Create user\n2 - Show users\n3 - Drop user\n4 - Proceed with data"
				+ "\n5 - Exit");
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
		String content = in.nextLine();
		return content;
	}



}
