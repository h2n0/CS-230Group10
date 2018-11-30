package cs230.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cs230.system.DatabaseManager;
import cs230.system.Fine;
import cs230.system.User;


public class EditFineController  {

	@FXML private TextField newAmount;
    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> User;
    @FXML private TableColumn<User, Double> Amount;
	@FXML private TableColumn<User, Double> NewAmount;	
	
	@FXML
	private void handleSaveButton(ActionEvent event) {
		String newAmountTxt = NewAmount.getText();
		String user = User.getText();
		
		//set to -1 so it is caught if not changed
		Double newAmountDbl= -1.00;
		
		try {
			newAmountDbl = Double.parseDouble(newAmountTxt);
		}
		catch(NumberFormatException e){
			//newAmount wasnt a number hence handle exception
		}
		
		//* by 100 to move 2dp to 0 dp
		Double foo = newAmountDbl * 100;
		//if more than 2dp foo will != 0
		foo = foo - foo.intValue();
		
		if (foo > 0){
			//was more than 2dp
		}
		else if (newAmountDbl<0.01 || newAmountDbl > Amount.getCellData(0)) {
			//outside of bounds
		}
		else {
			//save
		}
		
		//then visible label "invalid details"
	}
	
	@FXML
	private void handleCancelButton(ActionEvent event) {
		//remove popup from screen here, thanks Jack
	}
	
	
	@FXML
    public void initialize() {
    	ArrayList<User> allUsers = new ArrayList<User>();
		try {
			allUsers = (ArrayList<User>) DatabaseManager.getTable("user");
		}
    	catch(Exception e){
			e.printStackTrace();
		}
		allUsers.removeIf(s -> !(s.getName()=="USERSEARCHINGFOR"));
		PopulateFineTable(allUsers);
    }
    
    private void PopulateFineTable(ArrayList<User> fineList) {
    	User.setCellValueFactory(new PropertyValueFactory<User, String>("User"));
        Amount.setCellValueFactory(new PropertyValueFactory<User, Double>("Amount"));
		NewAmount.setCellValueFactory(new PropertyValueFactory<User, Double>("NewAmount"));
		    	
        if (fineList != null){
        	tableView.getItems().setAll(fineList);
        }
        
    }
	/*
        
		
    
    */
}