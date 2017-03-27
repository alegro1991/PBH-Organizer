package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Manipulation;

public class MainPage {


	public void start(Stage window, String name, Manipulation manipulation) throws Exception{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("WorkingPage.fxml"));
		Pane root = (Pane) loader.load();
		Scene scene = new Scene(root);
		CSSInjector.setStandard(scene, this);
		WorkingController controller = loader.getController();
		controller.setName(name);
		controller.setManipulation(manipulation);
		controller.setStage(window);
		controller.initTable();
		controller.makeTable();
		window.setScene(scene);
		window.show();

	}



}
