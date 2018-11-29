import cs230.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditResourceController  {

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
    
	private void handleSaveButton(ActionEvent event) {
		String resourceID = ResourceID.getText();
		String title = Title.getText();
		Integer year = Integer.parseInt(Year.getText());
		String thumbnail = Thumbnail.getText();
		String subject = Subject.getText();
		String queue = Queue.getText();
		Integer numCopies = Integer.parseInt(NumCopies.getText());
		Integer numAvailableCopies = Integer.parseInt(NumAvailableCopies.getText());
	}
	
	private void handleCancelButton(ActionEvent event) {
		//back to prev page
	}
	
	/*
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FineID.setCellValueFactory(new PropertyValueFactory<User, String>("FineID"));
        User.setCellValueFactory(new PropertyValueFactory<User, String>("User"));
        Amount.setCellValueFactory(new PropertyValueFactory<User, String>("Amount"));
		NewAmount.setCellValueFactory(new PropertyValueFactory<User, String>("NewAmount"));
		
        tableView.getItems().setAll(parseList());
    }
    private List<User> parseList(){
        // parse and construct User datamodel list by looping your ResultSet rs
        return(list);   
    }
    */
}