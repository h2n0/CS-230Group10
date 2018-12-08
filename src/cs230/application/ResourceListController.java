package cs230.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import cs230.system.User;
import cs230.system.Resource;
import cs230.system.SharedData;
import cs230.system.Book;
import cs230.system.Dvd;
import cs230.system.Laptop;
import cs230.system.Librarian;
import cs230.system.DatabaseManager;
import cs230.system.PassInfo;

/**
 * Controller behind the Fine page
 * 
 * @author 963257
 * @version 1.0
 */
public class ResourceListController {
        // list of resources to show
        private static ArrayList<Resource> inputResources;
        // Table containing all the resources
        @FXML
        private TableView<Resource> tableView;
        // resourceID column in table
        @FXML
        private TableColumn<Resource, Integer> resourceID;
        // title column in table
        @FXML
        private TableColumn<Resource, String> title;
        // year column in table
        @FXML
        private TableColumn<Resource, Integer> year;
        // column to put the details buttons into
        @FXML
        private TableColumn<Resource, Button> details;
        // button to create a new resource
        @FXML
        private Button addResourceButton;

        /**
	     * Overrides the initialise function so when the window is open the info for all
	     * users with fines are displayed
	     */
        @SuppressWarnings("unchecked")
        @FXML
        public void initialize() {
                // if user is a librarian then show add button
                String currentUser = SharedData.getUsername();
                
                //implementation using search record
                
                Librarian tempUser = new Librarian(currentUser,null,null,null,null,null);
                ArrayList<Librarian> matches = (ArrayList<Librarian>) DatabaseManager.searchRecord(tempUser,"librarian");
                if (matches != null || matches.size() > 0){
                        addResourceButton.setVisible(true); 
                }
                
                /*
                //old code that uses getTable instead of searchRecord
                ArrayList<Librarian> librarianList = (ArrayList<Librarian>)DatabaseManager.getTable("librarian");

                for (Librarian l : librarianList) {
                        if (currentUser.equals(l.getName())) {
                                addResourceButton.setVisible(true);
                        }
                }
                */
                
                // populate the table with the resources passed to the page
                populateListTable(inputResources);
	    }

        /** 
         * launches the page to add a resource
         */
        private void handleAddResourceButton() {
                
        }
        
        /**
	     * Populates the appropriate features on the window for a user
	     * 
	     * @param fineList a list of users to be displayed in the table
	     */
        private void populateListTable(ArrayList<Resource> resourceList) {
                //prepare the columns to accept values
                resourceID.setCellValueFactory(new PropertyValueFactory<Resource, Integer>("resourceID"));
                title.setCellValueFactory(new PropertyValueFactory<Resource, String>("title"));
                year.setCellValueFactory(new PropertyValueFactory<Resource, Integer>("year"));
                details.setCellFactory(ActionButtonTableCell.<Resource>forTableColumn("Edit", (Resource r)
						-> loadResourceDetail(r)));

                // if the list of resources isnt null
                if (resourceList != null) {
                        // populate the columns
                        tableView.getItems().setAll(resourceList);
                }

        }

        /**
	     * Loads the edit fine page, passing the user through the PassInfo class
	     * @param u the user to pass into the fine edit page
	     * @return the user that was edited
	     */
        private Resource loadResourceDetail(Resource r) {
                try {
                        // Create a FXML loader for loading the resourceDetail FXML file.
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resourceDetail.fxml"));     

                        // Run the loader
                        AnchorPane editRoot = (AnchorPane)fxmlLoader.load();          
                        // Access the controller that was created by the FXML loader
                        ResourceDetailController detailController = fxmlLoader.<ResourceDetailController>getController();
                        
                        //set the user to be edited
                        detailController.setResource(r.getID());
                        
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
                        // Quit the program (with an error code)
                        System.exit(-1);
                }
                return r;
	    }
}			