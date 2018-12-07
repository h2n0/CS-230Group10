package cs230.application;

import java.util.ArrayList;

import cs230.system.Loan;
import cs230.system.Resource;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class LibrarianMainpageController {
        @FXML
        private TableColumn<Loan,String> copyIdColumn;
        
        @FXML
        private TableColumn<Loan,String> resourceIdColumn;
        
        @FXML
        private TableColumn<Loan,String> borrowerColumn;
        
        @FXML
        private TableColumn<Loan,String> overdueColumn;
        
        @FXML
        private TableColumn<Loan,Button> infoColumn;
        
        @FXML
        public void initialize() {
                
        }
        
        private void populateListTable(ArrayList<Loan> loanList) {
                // prepare the copyID column to take IDs
                copyIdColumn.setCellValueFactory(
                        new PropertyValueFactory<Loan, String>("copyID"));
                resourceIdColumn.setCellValueFactory(
                                new PropertyValueFactory<Loan, String>("resourceID"));
                borrowerColumn.setCellValueFactory(
                                new PropertyValueFactory<Loan, String>("copyID"));
                copyIdColumn.setCellValueFactory(
                                new PropertyValueFactory<Loan, String>("username"));
        }

}
