package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Manipulation;

public class NewUserPage {

	AnchorPane root;
	Stage mainStage;
	Manipulation manipulation;

	public NewUserPage(Stage stage, Manipulation manipulation){
		mainStage = stage;
		this.manipulation = manipulation;
	}

	public void start() throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NewUserPage.fxml"));
		root = (AnchorPane) loader.load();
		NewUserPageController controller = loader.getController();
		controller.setManipulation(manipulation);
		Stage stage = new Stage();
		stage.initOwner(mainStage);
		stage.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(root);
		CSSInjector.setStandard(scene, this);
		stage.setScene(scene);
		stage.showAndWait();
	}
}
