package cs230.application;

import cs230.system.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller behind the Fine page
 * 
 * @author 963257
 * @version 1.0
 */
public class ResourceListController {
        // list of resources to show
	    private static ArrayList<Dvd> dvdResources;
	    private static ArrayList<Laptop> laptopResources;
	    private static ArrayList<Book> bookResources;
	    @FXML
	    private TableView<Dvd> tableViewDvd;
	    // resourceID column in table
	    @FXML
	    private TableColumn<Dvd, String> resourceIDDvd;
	    // title column in table
	    @FXML
	    private TableColumn<Dvd, String> titleDvd;
	    // year column in table
	    @FXML
	    private TableColumn<Dvd, Integer> yearDvd;
	    // column to put the details buttons into
	    @FXML
	    private TableColumn<Dvd, Button> detailsDvd;
	    
	    @FXML
        private TableView<Book> tableViewBook;
        // resourceID column in table
        @FXML
        private TableColumn<Book, String> resourceIDBook;
        // title column in table
        @FXML
        private TableColumn<Book, String> titleBook;
        // year column in table
        @FXML
        private TableColumn<Book, Integer> yearBook;
        // column to put the details buttons into
        @FXML
        private TableColumn<Book, Button> detailsBook;
        
        @FXML
        private TableView<Laptop> tableViewLaptop;
        // resourceID column in table
        @FXML
        private TableColumn<Laptop, String> resourceIDLaptop;
        // title column in table
        @FXML
        private TableColumn<Laptop, String> titleLaptop;
        // year column in table
        @FXML
        private TableColumn<Laptop, Integer> yearLaptop;
        // column to put the details buttons into
        @FXML
        private TableColumn<Laptop, Button> detailsLaptop;
        
        private boolean isDvd = false;
        private boolean isLaptop = false;
        private boolean isBook = false;

        /**
	     * Overrides the initialise function so when the window is open the info for all
	     * users with fines are displayed
	     */
        @FXML
        public void initialize() {
                if(isDvd)
                {
                        populateDvdListTable();
                        tableViewLaptop.setVisible(false);
                        tableViewBook.setVisible(false);
                } else if(isLaptop) {
                        populateLaptopListTable(); 
                        tableViewDvd.setVisible(false);
                        tableViewBook.setVisible(false);
                } else if(isBook) {
                        populateBookListTable(); 
                        tableViewDvd.setVisible(false);
                        tableViewLaptop.setVisible(false);
                }
                
	    }

	    public void setDvdToShow(ArrayList<Dvd> dvdList) {
	            dvdResources = dvdList;
	            isDvd = true;
	    }
	    
	    public void setLaptopToShow(ArrayList<Laptop> laptopList) {
	            laptopResources = laptopList;
                isLaptop = true;
        }

	    public void setbookToShow(ArrayList<Book> bookList) {
	            bookResources = bookList;
                isBook = true;
	    }
        
        /**
	     * Populates the appropriate features on the window for a user
	     * @param resourceList a list of users to be displayed in the table
	     */
        private void populateDvdListTable() {
                //prepare the columns to accept values
                //resourceID.setCellValueFactory(new
                // PropertyValueFactory<Resource, Integer>("resourceID"));
                titleDvd.setCellValueFactory(new PropertyValueFactory<Dvd,
                        String>("title"));
                yearDvd.setCellValueFactory(new PropertyValueFactory<Dvd, Integer>("year"));
                detailsDvd.setCellFactory(ActionButtonTableCell
                 .<Dvd>forTableColumn("Edit", (Dvd d) -> loadDvdResourceDetail(d)));

                // if the list of resources isnt null
                if (dvdResources != null) {
                        // populate the columns
                        tableViewDvd.getItems().setAll(dvdResources);
                }

        }
        
        /**
         * Populates the appropriate features on the window for a user
         * @param resourceList a list of users to be displayed in the table
         */
        private void populateLaptopListTable() {
                //prepare the columns to accept values
                //resourceID.setCellValueFactory(new
                // PropertyValueFactory<Resource, Integer>("resourceID"));
                titleLaptop.setCellValueFactory(new PropertyValueFactory<Laptop,
                        String>("title"));
                yearLaptop.setCellValueFactory(new PropertyValueFactory<Laptop, Integer>("year"));
                detailsLaptop.setCellFactory(ActionButtonTableCell
                 .<Laptop>forTableColumn("Edit", (Laptop l) -> this.loadLaptopResourceDetail(l)));

                // if the list of resources isnt null
                if (dvdResources != null) {
                        // populate the columns
                        tableViewLaptop.getItems().setAll(laptopResources);
                }

        }
        
        /**
         * Populates the appropriate features on the window for a user
         * @param resourceList a list of users to be displayed in the table
         */
        private void populateBookListTable() {
                //prepare the columns to accept values
                //resourceID.setCellValueFactory(new
                // PropertyValueFactory<Resource, Integer>("resourceID"));
                titleBook.setCellValueFactory(new PropertyValueFactory<Book,
                        String>("title"));
                yearBook.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
                detailsBook.setCellFactory(ActionButtonTableCell
                 .<Book>forTableColumn("Edit", (Book b) -> this.loadBookResourceDetail(b)));

                // if the list of resources isnt null
                if (bookResources != null) {
                        // populate the columns
                        tableViewBook.getItems().setAll(bookResources);
                }

        }

        /**
	     * Loads the edit fine page, passing the user through the PassInfo class
	     * @return the user that was edited
	     */
        private Dvd loadDvdResourceDetail(Dvd d) {
                try {
                        // Create a FXML loader for loading the resourceDetail FXML file.
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resourceDetail.fxml"));     

                        // Run the loader
                        AnchorPane editRoot = (AnchorPane)fxmlLoader.load();          
                        // Access the controller that was created by the FXML loader
                        ResourceDetailController detailController = fxmlLoader.<ResourceDetailController>getController();
                        
                        //set the user to be edited
                        detailController.setResource(d.getID());
                        
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
                }
                return d;
	    }
        
        private Book loadBookResourceDetail(Book b) {
                try {
                        // Create a FXML loader for loading the resourceDetail FXML file.
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resourceDetail.fxml"));     

                        // Run the loader
                        AnchorPane editRoot = (AnchorPane)fxmlLoader.load();          
                        // Access the controller that was created by the FXML loader
                        ResourceDetailController detailController = fxmlLoader.<ResourceDetailController>getController();
                        
                        //set the user to be edited
                        detailController.setResource(b.getID());
                        
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
                }
                return b;
        }
        
        private Laptop loadLaptopResourceDetail(Laptop l) {
                try {
                        // Create a FXML loader for loading the resourceDetail FXML file.
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resourceDetail.fxml"));     

                        // Run the loader
                        AnchorPane editRoot = (AnchorPane)fxmlLoader.load();          
                        // Access the controller that was created by the FXML loader
                        ResourceDetailController detailController = fxmlLoader.<ResourceDetailController>getController();
                        
                        //set the user to be edited
                        detailController.setResource(l.getID());
                        
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
                }
                return l;
        }
}			