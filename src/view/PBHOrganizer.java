package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PBHOrganizer extends Application{

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("PBH Organizer");
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(PBHOrganizer.class.getResource("LoginView.fxml"));
			Scene scene = new Scene(root);
			CSSInjector.setStandard(scene, this);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}


}
