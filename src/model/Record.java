package model;

public class Record {

	private int id;
	private String title;
	private String content;

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

	public Record(){};
	public Record(int id, String title, String content){
		this.id = id;
		this.title = title;
		this.content = content;
	}
}
