package cs230.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import cs230.system.User;
import cs230.system.DatabaseManager;

/**
 * Controller behind the Fine page
 * @author 963257
 * @version 1.2
 */
public class FineController  {
        //TextField to search for a student
        @FXML private TextField studentBox;
        //Table containing all the students
        @FXML private TableView<User> tableView;
        //Username column in table
        @FXML private TableColumn<User, String> UserName;
        //Amount column in table
        @FXML private TableColumn<User, Double> Amount;
        //column to put the edit buttons into
        @FXML private TableColumn<User, Button> Edit;
    
        /**
	     * gets the user input and searches for usernames matching the input
	     * then displays these users in the table
	     * @param event ?????
	     */
        @SuppressWarnings("unchecked")
        @FXML
        private void handleSearchButton(ActionEvent event) {
                /*
    	        //implementation using searchRecord
    	
    	        //get the text from the search button
    	        String student = studentBox.getText();
    	        //create a new student with the name
    	        User searchStudent = new User (student, null, null, null);
    	    
    	        //search the database for the student
    	        ArrayList<User> usersFound = new ArrayList<User>();
    	        usersFound = (ArrayList<User>) DatabaseManager.searchRecord(searchStudent, "User");
    	
    	        //populate the table with the users found
    	        PopulateFineTable(allUsers);
    	        */
    	
                //get the text from the search button
                String student = studentBox.getText();
		
                //get all the users
                ArrayList<User> allUsers = new ArrayList<User>();
                try {
                        allUsers = (ArrayList<User>) DatabaseManager.getTable("user");
		        }
                catch(Exception e){
                        e.printStackTrace();
		        }
                
                //remove users if their balance is 0 or their name doesnt contain the search value
                allUsers.removeIf(s -> (s.getBalance()==0.0 || !s.getName().contains(student)));
                
                //populate the table with the users found
                PopulateFineTable(allUsers);
	    }
    
    	/**
	     * Overrides the initialise function so when the window is open the
	     * info for all users with fines are displayed
	     */
        @FXML
    	public void initialize() {
                //get all the users using the DatabaseManager
                ArrayList<User> allUsers = new ArrayList<User>();
                try {
                        allUsers = (ArrayList<User>) DatabaseManager.getTable("user");
	    	    } catch(Exception e){
	    	            e.printStackTrace();
	            }

	            //remove users if they have a balance of 0
                allUsers.removeIf(s -> (s.getBalance()==0.0));
		
                //populate the table with the users above
                PopulateFineTable(allUsers);
        }
    
        /**
         * Populates the appropriate features on the window for a user
         * @param userList a list of users to be displayed in the table
         */
        private void PopulateFineTable(ArrayList<User> userList) {
                //prepare the columns to accept values
                UserName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
                Amount.setCellValueFactory(new PropertyValueFactory<User, Double>("balance"));
                Edit.setCellFactory(ActionButtonTableCell.<User>forTableColumn("Edit", (User u) -> loadEditFine(u)));
        
                //if the list of users isnt null
                if (userList != null){
                        //populate the columns
                        tableView.getItems().setAll(userList);
                }
        
        }
        
        
        /**
         * Loads the edit fine page, passing the user through the PassInfo class
         * @param u the user to pass into the fine edit page
         * @return the user that was edited //not sure why but it breaks when not returned so meh?
         */
        private User loadEditFine(User u){
                try {
                        // Create a FXML loader for loading the Edit Country FXML file.
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditFine.fxml"));     

                        // Run the loader
                        VBox editRoot = (VBox)fxmlLoader.load();          
                        // Access the controller that was created by the FXML loader
                        EditFineController editController = fxmlLoader.<EditFineController>getController();
                        
                        //set the user to be edited
                        editController.setUserFine(u);
                        
                        // Create a scene based on the loaded FXML scene graph
                        Scene editScene = new Scene(editRoot);
                        
                        // Create a new stage (i.e., window) based on the edit scene
                        Stage editStage = new Stage();
                        editStage.setScene(editScene);
                        
                        // Make the stage a modal window.
                        // This means that it must be closed before you can interact with any other window from this application.
                        editStage.initModality(Modality.APPLICATION_MODAL);
                        
                        // Show the edit scene and wait for it to be closed
                        editStage.showAndWait();
                        
                        // refresh this page to update any changes to u
                        initialize();
                        
                } catch (IOException e) {
                        e.printStackTrace();
                        // Quit the program (with an error code)
                        System.exit(-1);
                }
    	        return u;
    }   
}