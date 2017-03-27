package view;

import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Manipulation;

public class NewUserPageController{
	@FXML
	private Button createButton;
	@FXML
	private TextField nameField;
	@FXML
	private PasswordField passField;
	@FXML
	private PasswordField rePassField;
	private Manipulation manipulation;

	public void initialize(){
		passField.setDisable(true);
		rePassField.setDisable(true);
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
               nameField.requestFocus();
            }
        });
	}

	public void setManipulation(Manipulation manipulation){
		this.manipulation = manipulation;
	}

	public void createClicked(){
		List<String> usersList = manipulation.showUsers();
		String name = nameField.getText();
		if(name.contains(" ")){
			name = name.substring(0, name.indexOf(" "));
		}
		Stage stage = null;
		if(!(usersList.contains(name))){
			manipulation.createUser(name);
			stage = (Stage) createButton.getScene().getWindow();
			stage.close();
		}
		else{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Username with the same name already exists. Please use other name.");
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("layoutStyle.css").toExternalForm());
			alert.showAndWait();
		}
	}
}
