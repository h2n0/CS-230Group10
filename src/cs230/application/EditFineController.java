import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditFineController  {

    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> FineID;
    @FXML private TableColumn<User, String> User;
    @FXML private TableColumn<User, String> Amount;
	@FXML private TableColumn<User, String> NewAmount;	
	
	private void handleSaveButton(ActionEvent event) {
		float newAmount = Float.parseFloat();
		String fineID = FineID.getText();
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