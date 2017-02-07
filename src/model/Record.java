package model;

import java.time.Instant;
import java.time.LocalDate;

public class Record {

	private int id;
	private String title;
	private String content;
	private Instant dateTime;
	private LocalDate date;

	public int getID(){
		return id;
	}
	public void setID(int id){
		this.id = id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public Instant getDateTime(){
		return dateTime;
	}
	public void setDate(LocalDate date){
		this.date = date;
	}
	public LocalDate getDate(){
		return date;
	}


	public Record(){};
	public Record(int id, String title, String content, Instant dateTime, LocalDate date){
		this.id = id;
		this.title = title;
		this.content = content;
		this.dateTime = dateTime;
		this.date = date;
	}
}
