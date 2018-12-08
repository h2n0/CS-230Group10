package cs230.application;

import cs230.system.Loan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Date;

public class CopyHistoryController  {

    @FXML private TableView<Loan> tableView;
    @FXML private TableColumn<Loan, String> userBorrow;
    @FXML private TableColumn<Loan, Date> startDate;
    @FXML private TableColumn<Loan, Date> returnDate;
    @FXML private Button backButton;
    private Integer copyID;
    
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
	 **
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
     */
    
    /**
     * Populates the appropriate features on the window for a user
     * @param historyList a list of users to be displayed in the table
     */
    private void PopulateCopyTable(ArrayList<Loan> historyList) {
    	userBorrow.setCellValueFactory(new PropertyValueFactory<Loan, String>("user"));
    	startDate.setCellValueFactory(new PropertyValueFactory<Loan, Date>("startDate"));
    	returnDate.setCellValueFactory(new PropertyValueFactory<Loan, Date>("startDate"));
               
        if (historyList != null){
        	tableView.getItems().setAll(historyList);
        }
        
    }
}