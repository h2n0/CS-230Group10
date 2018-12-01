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
	@FXML private Label invalidLabel;
	@FXML private Label UserName;
	@FXML private Label Amount;
	@FXML private Label saveLabel;
	
	/**
	 * gets the user input and validates it, if valid then use the 
	 * database manager to save the changes
	 * @param event ?????
	 */
	@FXML
	private void handleSaveButton(ActionEvent event) {
		//remove invalid label from screen
		invalidLabel.setVisible(false);
		
		//get new amount and user
		String newAmountTxt = newAmount.getText();
		String user = UserName.getText();
		
		System.out.println(newAmountTxt);
		
		//set to -1 so it is caught if not changed in try catch below
		Double newAmountDbl= -1.00;
		
		//try casting to double to make sure only numbers input
		try {
			newAmountDbl = Double.parseDouble(newAmountTxt);
		}
		catch(NumberFormatException e){
			//newAmount wasnt a number
			invalidNewAmount();
		}
		
		//* by 100 to move 2dp to 0 dp
		Double foo = newAmountDbl * 100;
		//if more than 2dp foo will != 0
		foo = foo - foo.intValue();
		
		if (foo > 0){
			//was more than 2dp therefore invalid
			invalidNewAmount();
		}
		else if (newAmountDbl<0.01 || newAmountDbl > Double.parseDouble(Amount.getText())) {
			//outside of bounds
			invalidNewAmount();
		}
		else {
			//have to instantiate User to set new amount and then use as record for db
			ArrayList<User> allUsers = (ArrayList<User>) DatabaseManager.getTable("user");
			allUsers.removeIf(s -> !(s.getName().contains(user)));
			User unchangedUser = allUsers.get(0);
			User changedUser = allUsers.get(0);
			changedUser.setBalance(newAmountDbl);
			
			//save new balance amount and display label for success/error
			if (DatabaseManager.editRecord(unchangedUser, changedUser, "user")) {
				saveSuccessful();
				PopulateEditFine(changedUser);
			}
			else {
				saveUnsuccessful();
			}
			
		}
	}
	
	/**
	 * shows a label saying "invalid New Amount" in red
	 */
	@FXML
	private void invalidNewAmount() {
		invalidLabel.setVisible(true);
	}
	
	/**
	 * shows a label saying "save successful" in red
	 */
	@FXML
	private void saveSuccessful() {
		saveLabel.setText("save successful");
		saveLabel.setVisible(true);
	}
	
	/**
	 * shows a label saying "save unsuccessful" in red
	 */
	@FXML
	private void saveUnsuccessful() {
		saveLabel.setText("save unsuccessful");
		saveLabel.setVisible(true);
	}
	
	/**
	 * handles the cancel button
	 * @param event ?????
	 */
	@FXML
	private void handleCancelButton(ActionEvent event) {
		//remove popup from screen here, thanks Jack
	}
	
	/**
	 * overides the initialize function so when the window is open the
	 * info for a user is displayed
	 */
	@FXML
    public void initialize() {
    	ArrayList<User> allUsers = new ArrayList<User>();
		try {
			allUsers = (ArrayList<User>) DatabaseManager.getTable("user");
		}
    	catch(Exception e){
			e.printStackTrace();
		}
		
		//how to get selected user here??
		allUsers.removeIf(s -> !(s.getName().contains("Joe")));
		
		System.out.println(allUsers);
		//only expect 1 row at a time so only display first row
		User u = allUsers.get(0);
		System.out.println(u.toString());
		System.out.println(u.getName());
		PopulateEditFine(allUsers.get(0));
    }
	
    /**
     * Populates the appropriate features on the window for a user
     * @param u the user who's info will be displayed on the screen
     */
    private void PopulateEditFine(User u) {
        if (u != null){
        	UserName.setText(u.getName());
        	Amount.setText(u.getBalance().toString());
        }
        
    }
    
}