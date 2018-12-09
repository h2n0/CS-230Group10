package cs230.application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Date;

import cs230.system.Address;
import cs230.system.Copy;
import cs230.system.DatabaseManager;
import cs230.system.SharedData;
import cs230.system.Transaction;
import cs230.system.User;

/**
 * Controller behind the Fine page
 * @author 963257
 * @version 1.2
 */
public class TransactionController  {
        //Table containing all the transactions
        @FXML private TableView<Transaction> tableView;
        //transactionID column in table
        @FXML private TableColumn<Transaction, Integer> transactionID;
        //date column in table
        @FXML private TableColumn<Transaction, Date> date;
        //type column in table
        @FXML private TableColumn<Transaction, String> type;
        //amount column in table
        @FXML private TableColumn<Transaction, Double> amount;
        //copy column in table
        @FXML private TableColumn<Transaction, Copy> copy;
        //days overdue column in table
        @FXML private TableColumn<Transaction, Integer> daysOverdue;
    
    	/**
	     * Overrides the initialise function so when the window is open the
	     * transaction history for the current user is displayed
	     */
        @SuppressWarnings("unchecked")
		@FXML
    	public void initialize() {
        	
                //get all the users using the DatabaseManager
                ArrayList<Transaction> allHistory = new ArrayList<Transaction>();
                try {
                        allHistory = (ArrayList<Transaction>) DatabaseManager.getTable("transaction");
	    	    } catch(Exception e){
	    	            e.printStackTrace();
	            }

	            //remove users if they have a balance of 0
                allHistory.removeIf(s -> (s.getUser()!=SharedData.getUser()));
		
                //populate the table with the users above
                PopulateTransactionTable(allHistory);
        }
    
        /**
         * Populates the table on the window with a users transaction history
         * @param historyList a list of transaction history
         */
        private void PopulateTransactionTable(ArrayList<Transaction> historyList) {
                //prepare the columns to accept values
                transactionID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("transactionID"));
                date.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("date"));
                type.setCellValueFactory(new PropertyValueFactory<Transaction, String>("type"));
                amount.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
                copy.setCellValueFactory(new PropertyValueFactory<Transaction, Copy>("copy"));
                daysOverdue.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("daysOverdue"));
        
                //if the list of users isnt null
                if (historyList != null){
                        //populate the columns
                        tableView.getItems().setAll(historyList);
                }
        
        }
}
        