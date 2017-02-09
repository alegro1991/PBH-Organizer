package controler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class UserHandler {
	private Scanner scanFile;
	private Scanner scanIn;
	private PrintWriter pWriter;
	private String dbUsername;
	private File logs;

	public UserHandler(){
		scanFile = null;
		logs = null;
		try {
			logs = new File("login.txt");
			logs.createNewFile();
			scanFile = new Scanner(logs);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't find the users file to read");
			e.printStackTrace();
		}
		try {
			FileWriter fWriter = new FileWriter(logs, true);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			pWriter = new PrintWriter(bWriter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanIn = new Scanner(System.in);
	}

	public String logUser(){
		System.out.println("What's your username?");
		String username = scanIn.next();
		while(scanFile.hasNext()){
			dbUsername = scanFile.next();
			if(username.equals(dbUsername)){
				scanFile.close();
				return username;
			}
		}
		scanFile.close();
		return null;
	}

	public String createUser(){
		System.out.println("What's your username?");
		String username = scanIn.next();
		while(scanFile.hasNext()){
			dbUsername = scanFile.next();
			if(username.equals(dbUsername)){
				scanFile.close();
				return null;
			}
		}
		pWriter.println(username);
		pWriter.close();
		scanFile.close();
		return username;
	}

	public void destroyUser(String name){
		Scanner freshIn = null;
		try {
			freshIn = new Scanner(logs);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File tempFile = new File("tempLogin.txt");
		PrintWriter tempPWriter = null;
		try {
			BufferedWriter tempBWriter = new BufferedWriter(new FileWriter(tempFile));
			tempPWriter = new PrintWriter(tempBWriter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(freshIn.hasNext()){
			dbUsername = freshIn.next();
			if(!name.equals(dbUsername)){
				tempPWriter.println(dbUsername);
			}
		}
		tempPWriter.close();
		scanFile.close();
		freshIn.close();
		scanIn.close();
		pWriter.close();
		logs.delete();
		logs = new File("login.txt");
		try {
			Files.move(tempFile.toPath(), logs.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Filename can't be changed");
			e.printStackTrace();
		}
	}
}
