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
	private TableColumn<Resource, String> resourceID;
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
		User currentUser = PassInfo.getCurrentUser();
		ArrayList<Librarian> librarianList = 
				(ArrayList<Librarian>)DatabaseManager
				.getTable("librarian");

		for (Librarian l : librarianList) {
			if (currentUser.getName().equals(l.getName())) {
				addResourceButton.setVisible(true);
			}
		}

		// populate the table with the resources above
		populateListTable(inputResources);
	}

	public void setListToShow(ArrayList<Resource> resourceList) {

	}

	/**
	 * Populates the appropriate features on the window for a user
	 * 
	 * @param fineList a list of users to be displayed in the table
	 */
	private void populateListTable(ArrayList<Resource> resourceList) {
		// prepare the resourceID column to take IDs
		resourceID.setCellValueFactory(
				new PropertyValueFactory<Resource, String>("resourceID"));
		// prepare the title column to take titles
		title.setCellValueFactory(new 
				PropertyValueFactory<Resource, String>("title"));
		// prepare the year column to take year
		year.setCellValueFactory(new 
				PropertyValueFactory<Resource, Integer>("year"));
		// prepare the edit column to take
		// ActionButtons where text = "Details" and call
		// loadResourceDetail when pressed, where r is the resource from the row
		details.setCellFactory(
				ActionButtonTableCell
				.<Resource>forTableColumn("Edit", (Resource r)
						-> loadResourceDetail(r)));

		// if the list of resources isnt null
		if (resourceList != null) {
			// populate the columns
			tableView.getItems().setAll(resourceList);
		}

	}

	/**
	 * Loads the edit fine page, passing the user through the PassInfo class
	 * 
	 * @param u the user to pass into the fine edit page
	 * @return the user that was edited //not sure why but it breaks when not
	 *         returned so meh?
	 */
	private Resource loadResourceDetail(Resource r) {
		try {
			// set the resource to be passed to the resource detail page
			PassInfo.setResourceDetails(r);
			// open the resource detail page
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getClassLoader().getResource("cs230/application/resourceDetail.fxml"));
			VBox root = fxmlLoader.load();
			ResourceDetailController controller = fxmlLoader.<ResourceDetailController>getController();
			controller.setResource(r.getID());
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());
			Stage stage = (Stage) addResourceButton.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
}			