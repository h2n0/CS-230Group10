package cs230.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import cs230.system.User;
import cs230.system.Resource;
import cs230.system.Book;
import cs230.system.DVD;
import cs230.system.Laptop;
import cs230.system.DatabaseManager;
import cs230.system.PassInfo;

/**
 * Controller behind the Fine page
 * @author 963257
 * @version 1.0
 */
public class ResourceListController  {
	//Table containing all the resources
    @FXML private TableView<User> tableView;
    //resourceID column in table
    @FXML private TableColumn<User, String> resourceID;
    //title column in table
    @FXML private TableColumn<User, String> title;
    //year column in table
    @FXML private TableColumn<User, Integer> year;
    //column to put the details buttons into
    @FXML private TableColumn<User, Button> details;
    //button to create a new resource
    @FXML private Button addResourceButton;
    
    /**
	 * Overrides the initialise function so when the window is open the
	 * info for all users with fines are displayed
	 */
    @SuppressWarnings("unchecked")
	@FXML
    public void initialize() {
    	//get all the books using the DatabaseManager
    	ArrayList<Book> allBooks = new ArrayList<Book>();
		try {
			allBooks = (ArrayList<Book>) DatabaseManager.getTable("book");
		}
    	catch(Exception e){
			e.printStackTrace();
		}
		
		//get all the DVDs using the DatabaseManager
    	ArrayList<DVD> allDVDs = new ArrayList<Book>();
		try {
			allDVDs = (ArrayList<DVD>) DatabaseManager.getTable("dvd");
		}
    	catch(Exception e){
			e.printStackTrace();
		}
		
		//get all the laptops using the DatabaseManager
    	ArrayList<Laptop> allLaptops = new ArrayList<Laptop>();
		try {
			allLaptops = (ArrayList<Laptop>) DatabaseManager.getTable("laptop");
		}
    	catch(Exception e){
			e.printStackTrace();
		}
		
		ArrayList<Resource> allResources = (Resource) allBooks;
		allResources.append((Resource) allDVDs);
		allResources.append((Resource) allLaptops);
		
		//populate the table with the users above
		PopulateFineTable(allResources);
    }
    
    /**
     * Populates the appropriate features on the window for a user
     * @param fineList a list of users to be displayed in the table
     */
    private void PopulateFineTable(ArrayList<Resource> resourceList) {
    	//prepare the resourceID column to take IDs
    	resourceID.setCellValueFactory(new PropertyValueFactory<User, Integer>("resourceID"));
    	//prepare the title column to take titles
    	title.setCellValueFactory(new PropertyValueFactory<User, String>("title"));
    	//prepare the year column to take year
    	year.setCellValueFactory(new PropertyValueFactory<User, Integer>("year"));
        //prepare the edit column to take ActionButtons where text = "Details" and call loadResourceDetail when pressed, where u is the user from the row
        details.setCellFactory(ActionButtonTableCell.<User>forTableColumn("Edit", (Resource r) -> loadResourceDetail(r)));
        
        //if the list of resources isnt null
        if (resourceList != null){
        	//populate the columns
        	tableView.getItems().setAll(resourceList);
        }
        
    }
    
    /**
     * Loads the edit fine page, passing the user through the PassInfo class
     * @param u the user to pass into the fine edit page
     * @return the user that was edited //not sure why but it breaks when not returned so meh?
     */
    private Resource loadResourceDetail(Resource r){
    	try {
    		//set the resource to be passed to the resource detail page
    		PassInfo.setResourceDetails(r);
    		
    		//open the resource detail page
			VBox root =
				FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/resourceDetail.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());
			Stage stage = (Stage)addResourceButton.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return r;
    }   
}