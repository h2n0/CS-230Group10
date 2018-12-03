package cs230.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import cs230.system.DatabaseManager;
import cs230.system.PassInfo;
import cs230.system.User;

/**
 * Controller behind the Edit Fine page
 * @author 963257
 * @version 1.2
 */
public class EditFineController  {
	
	//the textField for the librarian to type in the amount paid
	@FXML private TextField amountPaid;
	@FXML private Label invalidLabel;
	@FXML private Label UserName;
	@FXML private Label Amount;
	@FXML private Label saveLabel;
	@FXML private Button cancelButton;
	@FXML private VBox editFineVBox;
	
	//the user to be edited, set when the page is opened
	private User currentUser;
	
	/**
	 * gets the user input and validates it, if valid then use the 
	 * database manager to save the changes
	 * @param event ?????
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void handleSaveButton(ActionEvent event) {
		//remove invalid label from screen
		invalidLabel.setVisible(false);
		
		//get new amount and user
		String amountPaidTxt = amountPaid.getText();
		String user = UserName.getText();
				
		//set to -1 so it is invalid if not changed in try catch below
		Double amountPaidDbl= -1.00;
		
		//try casting to double to make sure only numbers input
		try {
			amountPaidDbl = Double.parseDouble(amountPaidTxt);
		}
		catch(NumberFormatException e){
			//amountPaid wasnt a number
			invalidAmountPaid();
		}
		
		//* by 100 to move 2dp to 0 dp
		Double foo = amountPaidDbl * 100;
		//if more than 2dp foo will != 0
		foo = foo - foo.intValue();
		
		if (foo > 0){
			//was more than 2dp therefore invalid
			invalidAmountPaid();
		}
		else if (amountPaidDbl<0.01 || amountPaidDbl > Double.parseDouble(Amount.getText())) {
			//outside of bounds
			invalidAmountPaid();
		}
		else {
			//have to instantiate User to set new amount and then use as record for db
			ArrayList<User> allUsers = (ArrayList<User>) DatabaseManager.getTable("user");
			allUsers.removeIf(s -> !(s.getName().equals(user)));
			User unchangedUser = allUsers.get(0);
			User changedUser = allUsers.get(0);
			changedUser.setBalance(amountPaidDbl);
			
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
	private void invalidAmountPaid() {
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
	 * @param event the button clicked
	 */
	@FXML
	private void handleBackButton(ActionEvent event) {
		//load fine page fxml
		VBox root = null;
    	try {
			root = (VBox)FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/Fine.fxml"));
		} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//show fine page
    	Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());
		Stage stage = (Stage)cancelButton.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * overides the initialize function so when the window is open the
	 * info for a user is displayed
	 */
	@FXML
    public void initialize() {
		//get the user from PassInfo
		currentUser = PassInfo.getEditFineUser();
		//populate page with user info
		PopulateEditFine(currentUser);
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
        else {
        	System.out.println("failed to load the user into the window");
        }
        
    }
    
}