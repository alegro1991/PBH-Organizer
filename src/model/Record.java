package model;

import java.time.Instant;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Record {

	private final IntegerProperty id;
	private final StringProperty title;
	private final StringProperty content;
	private final ObjectProperty<Instant> added;
	private final ObjectProperty<LocalDate> date;

	public Record(int id, String title, String content, Instant added, LocalDate date){
		this.id = new SimpleIntegerProperty(id);
		this.title = new SimpleStringProperty(title);
		this.content = new SimpleStringProperty(content);
		this.added = new SimpleObjectProperty<Instant>(added);
		this.date = new SimpleObjectProperty<LocalDate>(date);
	}

	public int getId(){
		return id.get();
	}
	public void setId(int id){
		this.id.set(id);
	}
	public IntegerProperty idProperty(){
		return id;
	}
	public String getTitle(){
		return title.get();
	}
	public void setTitle(String title){
		this.title.set(title);
	}
	public StringProperty titleProperty(){
		return title;
	}
	public String getContent(){
		return content.get();
	}
	public void setContent(String content){
		this.content.set(content);
	}
	public StringProperty contentProperty(){
		return content;
	}
	public Instant getAdded(){
		return added.get();
	}
	public ObjectProperty<Instant> addedProperty(){
		return added;
	}
	public void setDate(LocalDate date){
		this.date.set(date);
	}
	public LocalDate getDate(){
		return date.get();
	}
	public ObjectProperty<LocalDate> dateProperty(){
		return date;
	}
}
