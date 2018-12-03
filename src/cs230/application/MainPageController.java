package cs230.application;

import java.io.IOException;
import java.util.ArrayList;
import cs230.system.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class MainPageController {

	@FXML
	private VBox sideOptions;

	@FXML
	private Label username;

	@FXML
	private Label balance;

	@FXML
	private ComboBox<Label> resourcePicker;

	@FXML
	private Button logOutButton;

	@FXML
	private Button searchButton;

	@FXML
	private TextField searchBox;

	@FXML
	private ImageView userImage;

	@FXML
	private Hyperlink dashboardLink;

	@FXML
	private Hyperlink fineLink;

	@FXML
	private ScrollPane mainContent;

	@FXML
	public void initialize() {
		setResourceLinks();
		username.textProperty().set(currentUser.getName());
		balance.textProperty().set(currentUser.getBalance().toString());
		updateComboBox();
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	private User currentUser;

	private void updateComboBox() {

		Label allresources = new Label();
		allresources.setText("All");
		//allresources.addEventHandler(changeSearchToAll());
		//resourcePicker.getItems().add(arg0);
	}

	private void setResourceLinks() {
		TitledPane resources = new TitledPane();
		resources.setText("View Resource");
		VBox resourceChoices = new VBox();
		Hyperlink allChoice = new Hyperlink();
		allChoice.setText("All resources");
		allChoice.setOnAction(e -> loadAllPage());
		Hyperlink laptopChoice = new Hyperlink();
		laptopChoice.setText("Laptops");
		laptopChoice.setOnAction(e -> loadLaptopPage());
		Hyperlink bookChoice = new Hyperlink();
		bookChoice.setText("Books");
		bookChoice.setOnAction(e -> loadBookPage());
		Hyperlink dvdChoice = new Hyperlink();
		dvdChoice.setText("DVDs");
		dvdChoice.setOnAction(e -> loadDVDPage());
		resourceChoices.getChildren().add(allChoice);
		resourceChoices.getChildren().add(laptopChoice);
		resourceChoices.getChildren().add(bookChoice);
		resourceChoices.getChildren().add(dvdChoice);
		resources.setContent(resourceChoices);
		sideOptions.getChildren().add(resources);
	}

	private void changeSearchToAll() {
		//searchBox.removeEventHandler(eventType, eventHandler);
	}

	private void loadAllPage() {
//		ArrayList<Resource> resources = new ArrayList<Resource>();
//		resources.addAll(getLaptopList());
//		resources.addAll(getDVDList());
//		resources.addAll(getBookList());
	}

	private void loadLaptopPage() {

	}

	private void loadDVDPage() {
		ArrayList resources = new ArrayList();
	}

	private void loadBookPage() {
		ArrayList resources = new ArrayList();
	}

	private void getLaptopList() {

	}

	private void getBookList() {

	}

	private void getDVDList() {
	}

	public void handleExit (ActionEvent event){
		//changeToLogin();
	}

	@FXML
	private void handleFineAction (ActionEvent event ){
		try {
			VBox finePage =
				FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/Fine.fxml"));
			mainContent.setContent(finePage);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
