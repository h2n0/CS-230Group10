package cs230.application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import cs230.system.DatabaseManager;
import cs230.system.Loan;
import cs230.system.Resource;
import cs230.system.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

/**
 * @author Jack
 */
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
                ArrayList<Loan> loanList 
                = (ArrayList<Loan>) DatabaseManager.getTable("loan");
                loanList.removeIf(l -> (l.getDueDate() == null 
                                && l.getDueDate().isAfter(LocalDate.now())));
                
                
        }
        
        private void populateListTable(ArrayList<Loan> loanList) {
                // prepare the copyID column to take IDs
                copyIdColumn.setCellValueFactory(
                        new PropertyValueFactory<Loan, String>("copyID"));
                resourceIdColumn.setCellValueFactory(
                                new PropertyValueFactory<Loan, String>("resourceID"));
                borrowerColumn.setCellValueFactory(
                                new PropertyValueFactory<Loan, String>("copyID"));
                infoColumn.setCellFactory(ActionButtonTableCell
                                .<Loan>forTableColumn("Copy Info",
                                                (Loan l) -> loadCopy(l)));
        }
        
        private Loan loadCopy(Loan l)
        {
                Popup popup = new Popup();
                CopyHistoryController controller =
                                new CopyHistoryController();
                controller.setCopyId(l.getCopyID());
                FXMLLoader loader = new 
                                FXMLLoader(getClass().getResource("Copy.fxml"));
                loader.setController(controller);
                try {
                        popup.getContent().add((Parent)loader.load());
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return l;  
        }

}
