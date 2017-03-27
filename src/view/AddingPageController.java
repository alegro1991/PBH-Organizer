package view;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Manipulation;

public class AddingPageController {

	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField titleField;
	@FXML
	private TextArea contentArea;
	@FXML
	private Button okButton;

	private LocalDate date;
	private String title;
	private String content;
	private String name;
	private int id;
	private Manipulation manipulation;
	private boolean isEdited;

	public void AddingPageControler(){
		isEdited = false;
	}

	public void init(){
		datePicker.setValue(date);
		titleField.setText(title);
		contentArea.setText(content);
		isEdited = true;
	}


	public void okClicked(){
		date = datePicker.getValue();
		title = titleField.getText();
		content = contentArea.getText();
		if(isEdited){
			manipulation.editRecord(title, content, date, name, id);
		}
		else{
			manipulation.insertRecord(title, content, date, name);
		}
		Stage stage = (Stage) okButton.getScene().getWindow();
		stage.close();
	}

	public LocalDate getDate(){
		return date;
	}
	public String getTitle(){
		return title;
	}
	public String getContent(){
		return content;
	}
	public void setDate(LocalDate date){
		this.date = date;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setContent(String content){
		this.content = content;
	}
	public void setId(int id){
		this.id = id;
	}

	public void setManipulation(Manipulation manipulation){
		this.manipulation = manipulation;
	}
	public void setName(String name){
		this.name = name;
	}

}
