package cs230.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import cs230.system.User;
import cs230.system.DatabaseManager;

public class FineController  {

	@FXML private TextField studentBox;
    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> UserName;
    @FXML private TableColumn<User, Double> Amount;

    /**
     * not sure why this is here, same as cancel button?
     * @param event
     */
    @FXML
    private void handleBackButton(ActionEvent event) {
    	// Code that either closes window or returns to main menu
    }
    
    /**
	 * gets the user input and searches for usernames matching the input
	 * then displays these users in the table
	 * @param event ?????
	 */
    @FXML
	private void handleSearchButton(ActionEvent event) {
		String student = studentBox.getText();
		ArrayList<User> allUsers = new ArrayList<User>();
		try {
			allUsers = (ArrayList<User>) DatabaseManager.getTable("user");
			System.out.println(allUsers.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		allUsers.removeIf(s -> (s.getBalance()==0.0 || !s.getName().contains(student)));
		System.out.println(allUsers.toString());
		PopulateFineTable(allUsers);
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
	 * info for all users with fines are displayed
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
		allUsers.removeIf(s -> (s.getBalance()==0.0));
		PopulateFineTable(allUsers);
    }
    
    /**
     * Populates the appropriate features on the window for a user
     * @param fineList a list of users to be displayed in the table
     */
    private void PopulateFineTable(ArrayList<User> fineList) {
    	UserName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        Amount.setCellValueFactory(new PropertyValueFactory<User, Double>("balance"));
    	
        if (fineList != null){
        	tableView.getItems().setAll(fineList);
        }
        
    }
        
}