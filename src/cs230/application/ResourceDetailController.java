package cs230.application;

import cs230.system.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResourceDetailController {

	@FXML
	private Button editButton;

	@FXML
	private Button saveButton;

	@FXML
	private Button deleteButton;

	@FXML
	private TableColumn copyIdColumn;
	
	@FXML
	private Label resourceID;

	@FXML
	private Label titleLabel;

	@FXML
	private TextField titleTextbox;

	@FXML
	private Label yearLabel;

	@FXML
	private TextField yearTextBox;

	@FXML
	private HBox imageBox;

	@FXML
	private ImageView thumbnailShow;

	@FXML
	private Label numOfCopiesLabel;

	@FXML
	private GridPane bookGrid;

	@FXML
	private Label authorLabel;

	@FXML
	private TextField authorTextBox;

	@FXML
	private Label publisherLabel;

	@FXML
	private TextField publisherTextBox;

	@FXML
	private Label genreLabel;

	@FXML
	private TextField genreTextBox;

	@FXML
	private Label isbnLabel;

	@FXML
	private TextField isbnTextBox;

	@FXML
	private Label bookLanguageLabel;

	@FXML
	private TextField bookLanguageTextBox;

	@FXML
	private GridPane dvdGrid;

	@FXML
	private Label directorLabel;

	@FXML
	private TextField directorTextBox;

	@FXML
	private Label runtimeLabel;

	@FXML
	private TextField runtimeTextBox;

	@FXML
	private Label dvdLanguageLabel;

	@FXML
	private TextField dvdLanguageTextBox;

	@FXML
	private GridPane laptopGrid;

	@FXML
	private Label manufacturerLabel;

	@FXML
	private TextField manufacturerTextBox;

	@FXML
	private Label modelLabel;

	@FXML
	private TextField modelTextBox;

	@FXML
	private Label osLabel;

	@FXML
	private TextField osTextBox;

	private Resource originalResource;
	
	private Resource showedResource;

	private Boolean isBook = false;

	private Boolean isDvd = false;

	private Boolean isLaptop = false;

	public ResourceDetailController(int resourceId) {
		setResourceInfo(resourceId);
	}

	@FXML
	public void initialize() {
		initializeGui();
		if (isDvd) {
			addDvdGui();
		} else if (isLaptop) {
			addLaptopGui();
		} else {
			addBookGui();
		}
	}
	
	private void setResourceInfo(int resourceId)
	{
		ArrayList<Resource> allResources = null;
		try {
			allResources = (ArrayList<Resource>) DatabaseManager.getTable("Resource");
		} catch (ClassCastException e) {
			// DBERROR
		}
		allResources.removeIf((s -> s.getID() != resourceId));
		showedResource = allResources.get(0);
		originalResource = showedResource;
		if (showedResource.getType().equals("Dvd")) {
			showedResource = (Dvd) showedResource;
			isDvd = true;
		} else if (showedResource.getType().equals("Laptop")) {
			showedResource = (Laptop) showedResource;
			isLaptop = true;
		} else {
			showedResource = (Book) showedResource;
			isBook = true;
		}
	}

	private void initializeGui() {
		String showedResourceID = Integer.toString(showedResource.getID());
		resourceID.textProperty().set(showedResourceID);
		titleLabel.textProperty().set(showedResource.getTitle());
		yearLabel.textProperty().set(Integer.toString(showedResource.getYear()));
		Image thumbnail = new Image(showedResource.getThumbnail());
		thumbnailShow.setImage(thumbnail);
		numOfCopiesLabel.textProperty().set(Integer.toString(showedResource.getNumCopies()));
	}

	private void addDvdGui() {
		dvdGrid.setVisible(true);
		Dvd currentDvd = (Dvd) showedResource;
		directorLabel.textProperty().set(currentDvd.getDirector());
		runtimeLabel.textProperty().set(Integer.toString(currentDvd.getRuntime()));
		dvdLanguageLabel.textProperty().set(Integer.toString(currentDvd.getRuntime()));
	}

	private void addLaptopGui() {
		laptopGrid.setVisible(true);
		Laptop currentLaptop = (Laptop) showedResource;
		manufacturerLabel.textProperty().set(currentLaptop.getManufacturer());
		modelLabel.textProperty().set(currentLaptop.getModel());
		osLabel.textProperty().set(currentLaptop.getOperatingSystem());

	}

	private void addBookGui() {
		bookGrid.setVisible(true);
		Book currentBook = (Book) showedResource;
		authorLabel.textProperty().set(currentBook.getAuthor());
		genreLabel.textProperty().set(currentBook.getGenre());
		isbnLabel.textProperty().set(currentBook.getISBN());
		bookLanguageLabel.textProperty().set(currentBook.getLanguage());
	}

	private void sharedInfoEditToggle() {
		boolean titleTextboxShow = titleTextbox.visibleProperty().get();
		titleTextbox.visibleProperty().set(!titleTextboxShow);
		titleLabel.setVisible(!titleLabel.visibleProperty().get());
		boolean yearTextBoxShow = yearTextBox.visibleProperty().get();
		yearTextBox.visibleProperty().set(!yearTextBoxShow);
		yearLabel.setVisible(!yearLabel.visibleProperty().get());
		thumbnailShow.setVisible(!thumbnailShow.visibleProperty().get());
		// Show file select
		saveButton.setVisible(!saveButton.visibleProperty().get());
		if(editButton.getText().equals("Cancel"))
		{
			editButton.setText("Edit Details");
		}
		else
		{
			editButton.setText("Cancel");
		}
	}

	private void laptopInfoEdit() {
		boolean manufacturerTBShow = manufacturerTextBox.visibleProperty().get();
		manufacturerTextBox.visibleProperty().set(!manufacturerTBShow);
		boolean manufacturerLabelShow = manufacturerLabel.visibleProperty().get();
		manufacturerLabel.visibleProperty().set(!manufacturerLabelShow);
		modelTextBox.visibleProperty().set(!modelTextBox.visibleProperty().get());
		modelLabel.visibleProperty().set(!modelLabel.visibleProperty().get());
		osLabel.visibleProperty().set(!osLabel.visibleProperty().get());
		osTextBox.visibleProperty().set(!osTextBox.visibleProperty().get());
	}

	private void dvdInfoEdit() {
		directorLabel.visibleProperty().set(!directorLabel.visibleProperty().get());
		directorTextBox.visibleProperty().set(!directorTextBox.visibleProperty().get());
		runtimeLabel.visibleProperty().set(!runtimeLabel.visibleProperty().get());
		runtimeTextBox.visibleProperty().set(!runtimeTextBox.visibleProperty().get());
		dvdLanguageLabel.visibleProperty().set(!dvdLanguageLabel.visibleProperty().get());
		dvdLanguageTextBox.visibleProperty().set(!dvdLanguageTextBox.visibleProperty().get());
		// DVD TABLE
	}

	private void bookInfoEdit() {
		authorLabel.visibleProperty().set(!authorLabel.visibleProperty().get());
		authorTextBox.visibleProperty().set(!authorTextBox.visibleProperty().get());
		publisherLabel.visibleProperty().set(!publisherLabel.visibleProperty().get());
		publisherTextBox.visibleProperty().set(!publisherTextBox.visibleProperty().get());
		genreTextBox.visibleProperty().set(!genreTextBox.visibleProperty().get());
		genreLabel.visibleProperty().set(!genreLabel.visibleProperty().get());
		isbnLabel.visibleProperty().set(!isbnLabel.visibleProperty().get());
		isbnTextBox.visibleProperty().set(!isbnTextBox.visibleProperty().get());
		bookLanguageLabel.visibleProperty().set(!bookLanguageLabel.visibleProperty().get());
		boolean bookLanguageTBShow = bookLanguageTextBox.visibleProperty().get();
		bookLanguageTextBox.visibleProperty().set(!bookLanguageTBShow);
	}
	
	@FXML
	private void handleEditAction(ActionEvent event) {
		sharedInfoEditToggle();
		if (isDvd) {
			dvdInfoEdit();
		} else if (isLaptop) {
			laptopInfoEdit();
		} else {
			bookInfoEdit();
		}
	}
	
	@FXML
	private void handleDeleteAction(ActionEvent event) {
		DatabaseManager.deleteRecord(originalResource, "Resource");
		DatabaseManager.deleteRecord(showedResource, "Resource");
		//load fine page fxml
		VBox root = null;
    	try {
			root = (VBox)FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/ResourceList.fxml"));
		} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//show fine page
    	Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());
		Stage stage = (Stage)deleteButton.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
