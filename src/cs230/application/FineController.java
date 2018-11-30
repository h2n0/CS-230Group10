package cs230.application;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.ResourceBundle;
import cs230.system.User;
import cs230.system.DatabaseManager;

public class FineController  {

	@FXML private TextField studentBox;
    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> UserName;
    @FXML private TableColumn<User, Double> Amount;

    @FXML
    private void handleBackButton(ActionEvent even) {
    	// Code that either closes window or returns to main menu
    }

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
    
    @FXML
	private void handleCancelButton(ActionEvent event) {
		//delete popup here, thanks Jack
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
		allUsers.removeIf(s -> (s.getBalance()==0.0));
		PopulateFineTable(allUsers);
    }
    
    private void PopulateFineTable(ArrayList<User> fineList) {
    	UserName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        Amount.setCellValueFactory(new PropertyValueFactory<User, Double>("balance"));
    	
        if (fineList != null){
        	tableView.getItems().setAll(fineList);
        }
        
    }
        
}