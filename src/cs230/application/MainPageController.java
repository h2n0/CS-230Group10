package cs230.application;

import cs230.system.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPageController {

	private static final String COMBOBOX_ALL = "All";

	private static final String COMBOBOX_BOOK = "Book";

	private static final String COMBOBOX_DVD = "Dvd";

	private static final String COMBOBOX_LAPTOP = "Laptop";

	@FXML
	private VBox sideOptions;

	@FXML
	private Label username;

	@FXML
	private Label balance;

	@FXML
	private ComboBox<String> resourcePicker;

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

	private String currentResourceSelection;

	@FXML
	public void initialize() {
		setResourceLinks();
		username.textProperty().set(currentUser.getName());
		balance.textProperty().set(Double.toString(currentUser.getBalance()));
		userImage = new ImageView(currentUser.getAvatarFilePath());
		updateComboBox();
		username.setText(SharedData.getUsername());
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	private User currentUser;

	private void updateComboBox() {
		ObservableList<String> pickerOptions = FXCollections
				.observableArrayList(COMBOBOX_ALL, COMBOBOX_DVD,
				COMBOBOX_BOOK, COMBOBOX_LAPTOP);
		resourcePicker.getItems().addAll(pickerOptions);
		resourcePicker.valueProperty()
		.addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String selection) {
				currentResourceSelection = selection;
			}
		});
	}

	@FXML
	private void handleLogout(ActionEvent event) {
		try {
			Stage stage = (Stage) mainContent.getScene()
					.getWindow();
			AnchorPane root = FXMLLoader.load(getClass()
					.getClassLoader()
					.getResource("cs230/application/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getClassLoader()
							.getResource("cs230/application/application.css")
							.toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@FXML
	private void handleSearch(ActionEvent event) {
		ArrayList<Resource> resources = getResourceList();
		if (currentResourceSelection.equals(COMBOBOX_BOOK)) {
			resources.removeIf(r -> r.getType() != "book");
		} else if (currentResourceSelection.equals(COMBOBOX_LAPTOP)) {
			resources.removeIf(r -> r.getType() != "Laptop");
		} else if (currentResourceSelection.equals(COMBOBOX_DVD)) {
			resources.removeIf(r -> r.getType() != "Dvd");
		}
		if(!searchBox.textProperty().equals(null))
		{
			resources.removeIf(r -> r.getTitle()
					.contains((CharSequence) searchBox.textProperty()));
		}
		loadListPage(resources);
	}
	
	private void loadListPage(ArrayList<Resource> resources)
	{
		
	}

	private ArrayList<Resource> getResourceList() {
		return (ArrayList<Resource>) DatabaseManager.getTable("Resource");
	}

	@FXML
	private void handleFineAction(ActionEvent event) {
		try {
			VBox finePage = FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/Fine.fxml"));
			mainContent.setPrefHeight(finePage.getPrefHeight());
			mainContent.setPrefWidth(finePage.getPrefWidth());
			mainContent.setContent(finePage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}