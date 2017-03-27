package view;


import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Manipulation;

public class MainPageController {

	@FXML
	private Button myButton;
	@FXML
	private ChoiceBox<String> loginBox;
	@FXML
	private PasswordField passField;

	private MainPage mp;
	private Manipulation manipulation;
	private ObservableList<String> usersList;

	public void initialize(){
		passField.setDisable(true);
		mp = new MainPage();
		manipulation = new Manipulation();
		refreshChoiceBox();
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginBox.requestFocus();
            }
        });
	}

	@FXML
	private void changePage() throws Exception{
		String name = loginBox.getValue();
		if(name != null){
			Stage stage = (Stage) myButton.getScene().getWindow();
			mp.start(stage, name, manipulation);
		}
	}

	@FXML
	private void deleteUser(){
		String name = loginBox.getValue();
		if(name != null){
			manipulation.dropUser(name);
			refreshChoiceBox();
		}
	}

	@FXML
	private void createNewUser(ActionEvent event){
		Stage stage = (Stage) myButton.getScene().getWindow();
		NewUserPage newUserPage = new NewUserPage(stage, manipulation);
		try {
			newUserPage.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshChoiceBox();
	}

	public void refreshChoiceBox(){
		usersList = manipulation.showUsers();
		loginBox.setItems(usersList);
	}

}
