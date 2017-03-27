package view;

import java.time.Instant;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Manipulation;
import model.Record;

public class WorkingController {

	private String name;
	private Manipulation manipulation;
	private Stage stage;
	private ObservableList<Record> obsList;
	private FilteredList<Record> filteredList;
	private SortedList<Record> sortedList;
	@FXML
	private TableView<Record> tableView;
	@FXML
	private TableColumn<Record, Integer> idColumn;
	@FXML
	private TableColumn<Record, LocalDate> dateColumn;
	@FXML
	private TableColumn<Record, String> titleColumn, contentColumn;
	@FXML
	private TableColumn<Record, Instant> addedColumn;
	@FXML
	private DatePicker whatDate;


	public void initialize(){
			makeList();
			whatDate.setValue(LocalDate.now());
	}

	private void makeList(){
		idColumn.setCellValueFactory(new PropertyValueFactory<Record, Integer>("id"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Record, LocalDate>("date"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("title"));
		contentColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("content"));
		addedColumn.setCellValueFactory(new PropertyValueFactory<Record, Instant>("added"));
	}

	public void setName(String name){
		this.name = name;
	}
	public void setManipulation(Manipulation manipulation){
		this.manipulation = manipulation;
	}
	public void setStage(Stage stage){
		this.stage = stage;
	}

	public void makeTable(){
		 filteredList = new FilteredList<>(obsList, p -> true);
		 whatDate.valueProperty().addListener((obsList, oldValue, newValue) ->{
			filteredList.setPredicate(record -> {
				if(record.getDate().toString().contains(newValue.toString())){
					return true;
				}
				else{
					return false;
				}
			});
		});
		sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
	}

	public void initTable(){
		obsList = manipulation.selectRecord(name);
		makeTable();
	}

	@FXML
	public void deleteRecord(){
		int selectedRecord = tableView.getSelectionModel().getSelectedIndex();
		if(selectedRecord >= 0){
			int id = tableView.getItems().get(selectedRecord).getId();
			manipulation.deleteRecord(name, id);
			initTable();
		}
	}

	@FXML
	public void addRecord(){
		AddingPage addingPage = new AddingPage(stage, manipulation, name);
		initTable();
	}

	@FXML
	public void editRecord(){
		int selectedRecord = tableView.getSelectionModel().getSelectedIndex();
		if(selectedRecord >= 0){
			int id = tableView.getItems().get(selectedRecord).getId();
			LocalDate ldate = tableView.getItems().get(selectedRecord).getDate();
			String title = tableView.getItems().get(selectedRecord).getTitle();
			String content = tableView.getItems().get(selectedRecord).getContent();
			AddingPage addingPage = new AddingPage(stage, manipulation, name, ldate, title, content, id);
			initTable();
		}
	}

	@FXML
	public void showAll(){
		whatDate.setValue(LocalDate.now());

	}
}
