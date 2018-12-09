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
        //Table containing all the fines
        @FXML private TableView<Transaction> tableView;
        //transactionID column in fines table
        @FXML private TableColumn<Transaction, Integer> transactionID;
        //date column in fines table
        @FXML private TableColumn<Transaction, Date> date;
        //type column in fines table
        @FXML private TableColumn<Transaction, String> type;
        //amount column in fines table
        @FXML private TableColumn<Transaction, Double> amount;
        //copy column in fines table
        @FXML private TableColumn<Transaction, Copy> copy;
        //days overdue column in fines table
        @FXML private TableColumn<Transaction, Integer> daysOverdue;
        

        //Table containing all the transactions
        @FXML private TableView<Transaction> tableViewPayment;
        //transactionID column in table
        @FXML private TableColumn<Transaction, Integer> transactionIDPayment;
        //date column in table
        @FXML private TableColumn<Transaction, Date> datePayment;
        //type column in table
        @FXML private TableColumn<Transaction, String> typePayment;
        //amount column in table
        @FXML private TableColumn<Transaction, Double> amountPayment;
    
    	/**
	     * Overrides the initialise function so when the window is open the
	     * transaction history for the current user is displayed
	     */
        @SuppressWarnings("unchecked")
		@FXML
    	public void initialize() {
        	    System.out.println("Hello World!");
                //get all the users using the DatabaseManager
                ArrayList<Transaction> allHistory = new ArrayList<Transaction>();
                try {
                        allHistory = (ArrayList<Transaction>) DatabaseManager.getTable("transaction");
                        System.out.println(allHistory.size());
	    	    } catch(Exception e){
	    	            e.printStackTrace();
	            }
                
	            //remove transactions if they arent for the current user
                allHistory.removeIf(s -> (s.getUser()!=SharedData.getUser()));

                System.out.println(allHistory.size());
                
                ArrayList<Transaction> allPayments = allHistory;
                ArrayList<Transaction> allFines = allHistory;
                
                allPayments.removeIf(s -> (s.getType() !="payment"));

                System.out.println(allPayments.size());
                
                allFines.removeIf(s -> (s.getType() !="fine"));
                
                System.out.println(allFines.size());
                
                //populate the fines table
                PopulateFineTable(allFines);
                
                PopulatePaymentTable(allPayments);
                
                
                
        }
    
        /**
         * Populates the table on the window with a users transaction history
         * @param historyList a list of transaction history
         */
        private void PopulateFineTable(ArrayList<Transaction> historyList) {
                //prepare the columns to accept values
                transactionID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("transactionID"));
                date.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("date"));
                amount.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
                copy.setCellValueFactory(new PropertyValueFactory<Transaction, Copy>("copy"));
                daysOverdue.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("daysOverdue"));
        
                //if the list of users isnt null
                if (historyList != null){
                        //populate the columns
                        tableView.getItems().setAll(historyList);
                }
        
        }
        
        /**
         * Populates the table on the window with a users transaction history
         * @param historyList a list of transaction history
         */
        private void PopulatePaymentTable(ArrayList<Transaction> historyList) {
                //prepare the columns to accept values
                transactionIDPayment.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("transactionID"));
                datePayment.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("date"));
                amountPayment.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
        
                //if the list of users isnt null
                if (historyList != null){
                        //populate the columns
                        tableViewPayment.getItems().setAll(historyList);
                }
        
        }
}
        