package cs230.application;

import java.io.IOException;
import java.util.ArrayList;

import cs230.system.Loan;
import cs230.system.Resource;
import cs230.system.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

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
                infoColumn.setCellFactory(ActionButtonTableCell.<Loan>forTableColumn("Copy Info", (Loan l) -> loadCopy(l)));
        }
        
        private Loan loadCopy(Loan l)
        {
                try {
                        FXMLLoader fxmlLoader = new FXMLLoader(
                                getClass().getClassLoader().getResource("cs230/application/Copy.fxml"));
                        VBox listPage = fxmlLoader.load();
                        ResourceListController controller = fxmlLoader.<ResourceListController>getController();
                        //controller.setListToShow(resources);
                        //mainContent.setPrefHeight(listPage.getPrefHeight());
                        //mainContent.setPrefWidth(listPage.getPrefWidth());
                        //mainContent.setContent(listPage);
                        return l;
                } catch(IOException e) {
                        e.printStackTrace();
                        return null;
                }
        }

}
