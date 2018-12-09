package cs230.application;

import cs230.system.Loan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Date;

import cs230.system.Loan;
import cs230.system.PassInfo;
import cs230.system.Resource;
import cs230.system.User;
import cs230.system.DatabaseManager;
import cs230.system.SharedData;

/**
 * controller behind the page showing history of a copy
 * @author 963257
 * @version 1.1
 */
public class CopyHistoryController  {
        // list of history for the copy
        private static ArrayList<Loan> inputCopyHist;
        //the table with the columns below
        @FXML private TableView<Loan> tableView;
        //the user who borrowed the copy column
        @FXML private TableColumn<Loan, String> userBorrow;
        //the start date column
        @FXML private TableColumn<Loan, Date> startDate;
        //the return date column
        @FXML private TableColumn<Loan, Date> returnDate;
        //the back button
        @FXML private Button backButton;
    
        /**
         * takes the user back to the main menu
         * @param event
         */
        @FXML
        private void handleBackButton(ActionEvent event) {
        	// Code that either closes window or returns to main menu
        }
    
    /**
	 * overides the initialize function so when the window is open the
	 * info for all users with fines are displayed
	 */
    @SuppressWarnings("unchecked")
	@FXML
    public void initialize() {
    	copyID = PassInfo.getCopyID();
    	
    	ArrayList<Loan> history = new ArrayList<Loan>();
		try {
			history = (ArrayList<Loan>) DatabaseManager.getTable("loan");
		}
    	catch(Exception e){
			e.printStackTrace();
		}
		history.removeIf(s -> (s.getCopyID != copyID));
		PopulateCopyTable(history);
    }
    
        /**
         * Populates the appropriate features on the window for a user
         * @param fineList a list of users to be displayed in the table
         */
        private void PopulateCopyTable(ArrayList<Loan> historyList) {
                //prepare the columns to accept values
                userBorrow.setCellValueFactory(new PropertyValueFactory<Loan, String>("user"));
                startDate.setCellValueFactory(new PropertyValueFactory<Loan, Date>("startDate"));
                returnDate.setCellValueFactory(new PropertyValueFactory<Loan, Date>("startDate"));
                
                //set the values in the table
                if (historyList != null){
                        tableView.getItems().setAll(historyList);
                }
        
        }
}