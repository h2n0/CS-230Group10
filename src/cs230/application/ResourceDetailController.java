package cs230.application;

import java.util.ArrayList;

import cs230.system.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private GridPane bookDetail;
	
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
	private GridPane dvdDetail;
	
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
	
	private static int resourceId;
	
	public ResourceDetailController(int resourceId)
	{
		//ArrayList<Resource> allResources = DatabaseManager.getTable("Resource");
	}
}
