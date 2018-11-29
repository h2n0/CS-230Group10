import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FineController  {

	@FXML private TextField studentBox;
    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> FineID;
    @FXML private TableColumn<User, String> User;
    @FXML private TableColumn<User, String> Amount;
	
	private void handleSearchButton(ActionEvent event) {
		String student = studentBox.getText();
		//get users that match Student
		//update table
	}
	
	private void handleCancelButton(ActionEvent event) {
		//get all fines
		//update table
	}
	
	/*
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserId.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        UserName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        Active.setCellValueFactory(new PropertyValueFactory<User, String>("active"));

        tableView.getItems().setAll(parseUserList());
    }
    private List<User> parseUserList(){
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list   
    }
    */    
}