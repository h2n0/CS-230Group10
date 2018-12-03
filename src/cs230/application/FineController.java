package cs230.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import cs230.system.User;
import cs230.system.DatabaseManager;
import cs230.system.PassInfo;

public class FineController  {

	@FXML private TextField studentBox;
    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> UserName;
    @FXML private TableColumn<User, Double> Amount;
    @FXML private TableColumn<User, Button> Edit;
    @FXML private Button backButton;
    
    /**
     * takes the user back to the main menu
     * @param event
     */
    @FXML
    private void handleBackButton(ActionEvent event) {
    	//Jack implement here
    	
    	//Temp code to close the window
    	Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
    
    /**
	 * gets the user input and searches for usernames matching the input
	 * then displays these users in the table
	 * @param event ?????
	 */
    @SuppressWarnings("unchecked")
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
	 * overides the initialize function so when the window is open the
	 * info for all users with fines are displayed
	 */
    @SuppressWarnings("unchecked")
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
        Edit.setCellFactory(ActionButtonTableCell.<User>forTableColumn("Edit", (User u) -> loadEditFine(u)));
        
        if (fineList != null){
        	tableView.getItems().setAll(fineList);
        }
        
    }
    
    private User loadEditFine(User u){
    	try {
    		PassInfo.setEditFineUser(u);
			VBox root =
				FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/EditFine.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());
			Stage stage = (Stage)backButton.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return u;
    }   
}
    	/*   	
		try {
			
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EditFine.fxml"));
			VBox root = (VBox) loader.load();
	        EditFineController controller = loader.getController();
	        
	        if(controller == null){
	        	System.out.println("Failed to load EditFine controller");
	        }
	        
	        controller.setCurrentUser(u);
            Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());
    		
    		Stage stage = (Stage)backButton.getScene().getWindow();
    		stage.setScene(scene);
    		stage.show(); 		
    		
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return u;
	}
}  */