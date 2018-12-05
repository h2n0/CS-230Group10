package cs230.application;

import java.util.ArrayList;

import cs230.system.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ResourceDetailController {

	@FXML
	private Button editButton;

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
	private Label ISBNLabel;

	@FXML
	private TextField ISBNTextBox;

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

	private Resource showedResource;

	private Boolean isBook = false;

	private Boolean isDvd = false;

	private Boolean isLaptop = false;

	public ResourceDetailController(int resourceId) {
		ArrayList<Resource> allResources = null;
		try {
			allResources = (ArrayList<Resource>) DatabaseManager.getTable("Resource");
		} catch (Exception e) {
			// DBERROR
		}
		allResources.removeIf((s -> s.getID() != resourceId));
		showedResource = allResources.get(0);
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

	private void initializeGui() {
		resourceID.textProperty().set(Integer.toString(showedResource.getID()));
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
		ISBNLabel.textProperty().set(currentBook.getISBN());
		bookLanguageLabel.textProperty().set(currentBook.getLanguage());
	}
	
	@FXML
	private void handleEditAction(ActionEvent event ) {
		sharedInfoEdit();
		if (isDvd) {
			dvdInfoEdit();
		} else if (isLaptop) {
			laptopInfoEdit();
		} else {
			bookInfoEdit();
		}
	}
	
	private void sharedInfoEdit() {
		titleTextbox.visibleProperty().set(true);
		titleLabel.setVisible(false);
		yearTextBox.visibleProperty().set(true);
		yearLabel.setVisible(false);
		thumbnailShow.setVisible(false);
		//Show file select
	}
	
	private void laptopInfoEdit()
	{
		
	}
	
	private void dvdInfoEdit()
	{
		
	}
	
	private void bookInfoEdit()
	{
		
	}
}
