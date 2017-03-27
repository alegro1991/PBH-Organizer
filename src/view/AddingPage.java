package view;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Manipulation;

public class AddingPage {

	private Stage mainStage;
	private Stage stage;
	private String name;
	private Manipulation manipulation;
	private LocalDate date;
	private String title;
	private String content;
	private int id;
	private boolean isEdited;

	public AddingPage(Stage mainStage, Manipulation manipulation, String name){
		this.name = name;
		this.mainStage = mainStage;
		this.manipulation = manipulation;
		isEdited = false;
		start();
	}


	public AddingPage(Stage mainStage, Manipulation manipulation, String name, LocalDate date, String title, String content, int id){
		this.name = name;
		this.manipulation = manipulation;
		this.mainStage = mainStage;
		this.date = date;
		this.title = title;
		this.content = content;
		this.id = id;
		isEdited = true;
		start();
	}

	public void start(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AddingPage.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			AddingPageController controller = loader.getController();
			controller.setManipulation(manipulation);
			controller.setName(name);
			if(isEdited){
				controller.setDate(date);
				controller.setTitle(title);
				controller.setContent(content);
				controller.setId(id);
				controller.init();
			}
			stage = new Stage();
			stage.initOwner(mainStage);
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(root);
			CSSInjector.setStandard(scene, this);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
