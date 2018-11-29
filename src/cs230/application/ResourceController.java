import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ResourceController  {

	@FXML private TextField resourceBox;
    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> ResourceID;
    @FXML private TableColumn<User, String> Title;
    @FXML private TableColumn<User, String> Year;
    @FXML private TableColumn<User, String> Thumbnail;
    @FXML private TableColumn<User, String> Subject;
    @FXML private TableColumn<User, String> Queue;
    @FXML private TableColumn<User, String> NumCopies;
    @FXML private TableColumn<User, String> NumAvailableCopies;
	
	private void handleSearchButton(ActionEvent event) {
		String resource = resourceBox.getText();
		//get Resources that match resource
		//update table
	}
	
	private void handleCancelButton(ActionEvent event) {
		//get all Resources
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