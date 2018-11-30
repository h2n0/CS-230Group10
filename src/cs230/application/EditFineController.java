package cs230.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import cs230.system.Fine;


public class EditFineController  {

    @FXML private TableView<Fine> tableView;
    @FXML private TableColumn<Fine, String> FineID;
    @FXML private TableColumn<Fine, String> User;
    @FXML private TableColumn<Fine, String> Amount;
	@FXML private TableColumn<Fine, String> NewAmount;	
	
	private void handleSaveButton(ActionEvent event) {
		//float newAmount = Float.parseFloat();
		String fineID = FineID.getText();
		
		
		//cast to double to check no text
		//convert to 2 dp and then check if its the same
		//see if between 0.01 & balance
		//then save
		
		//then visible label "invalid details"
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