import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class CreateResourceViewController {

    @FXML
    private TextField title;

    @FXML
    private TextField year;

    @FXML
    private TextField thumbnail;

    @FXML
    private Label authorLb;

    @FXML
    private Label bookLanguageLb;

    @FXML
    private Label publisherLb;

    @FXML
    private Label isbnLb;

    @FXML
    private Label genreLb;

    @FXML
    private TextField author;

    @FXML
    private TextField bookLanguage;

    @FXML
    private TextField publisher;

    @FXML
    private TextField isbn;

    @FXML
    private TextField genre;

    @FXML
    private Label manufacturerLb;

    @FXML
    private Label osLb;

    @FXML
    private Label modelLb;

    @FXML
    private TextField manufacturer;

    @FXML
    private TextField operatingSystem;

    @FXML
    private TextField model;

    @FXML
    private Label directorLb;

    @FXML
    private Label runtimeLb;

    @FXML
    private Label subtitlesLb;
	
	@FXML
    private Label dvdLanguageLb;

    @FXML
    private TextField director;

    @FXML
    private TextField runtime;

    @FXML
    private TextField dvdLanguage;

    @FXML
    private TextField subLanguages;

    @FXML
    private ChoiceBox<String> resourceChoice = new ChoiceBox<>();
	
	@FXML
    private Label unfinishedLb;

    @FXML
    void createResource(MouseEvent event) {
		if (title.getText().equals("") || year.getText().equals("") || thumbnail.getText().equals("")){
			unfinishedLb.setVisible(true);
		}else {
			String choice = resourceChoice.getValue();
			switch (choice) {
				case "Book" :
					if (bookLanguage.getText().equals("") || isbn.getText().equals("") || genre.getText().equals("")){
						unfinishedLb.setVisible(true);
					} else {
						unfinishedLb.setVisible(false);
						
						//Book book1 = new Book(id.getText(),title.getText(),year.getText(),thumbnail.getText(),author.getText(),publisher.getText(),genre.getText(),isbn.getText(),bookLanguage.getText());
					}
					break;
				case "Laptop" :
					if (manufacturer.getText().equals("") || operatingSystem.getText().equals("") || model.getText().equals("")){
						unfinishedLb.setVisible(true);
					} else {
						unfinishedLb.setVisible(false);
						//Laptop laptop1 = new Laptop(id.getText(),title.getText(),year.getText(),thumbnail.getText(),manufacturer.getText(),model.getText(),operatingSystem.getText());
					}
					break;
				case "Dvd" :
					if (director.getText().equals("") || runtime.getText().equals("")){
						unfinishedLb.setVisible(true);
					} else {
						unfinishedLb.setVisible(false);
						String[] subArray = subLanguages.getText().split(",");
						ArrayList<String> subLang = new ArrayList<String>(); 
						int i=0;
		
						while ( i != (subArray.length)) {
							subLang.add(subArray[i]);
							i++;
						}
						//Dvd dvd1 = new Dvd(id.getText(),title.getText(),year.getText(),thumbnail.getText(),director.getText(),runtime.getText(),dvdLanguage.getText(),subLang);
					}
					break;
			}
		}
    }

    @FXML
    void showResourceFields(MouseEvent event) {
		String value = resourceChoice.getValue();
		switch (value) {
			case "Book" : 
				authorLb.setVisible(true);
				bookLanguageLb.setVisible(true);
				publisherLb.setVisible(true);
				isbnLb.setVisible(true);
				genreLb.setVisible(true);
				author.setVisible(true);
				bookLanguage.setVisible(true);
				publisher.setVisible(true);
				isbn.setVisible(true);
				genre.setVisible(true);
				
				manufacturerLb.setVisible(false);
				osLb.setVisible(false);
				modelLb.setVisible(false);
				manufacturer.setVisible(false);
				operatingSystem.setVisible(false);
				model.setVisible(false);
				
				directorLb.setVisible(false);
				runtimeLb.setVisible(false);
				dvdLanguageLb.setVisible(false);
				subtitlesLb.setVisible(false);
				director.setVisible(false);
				runtime.setVisible(false);
				dvdLanguage.setVisible(false);
				subLanguages.setVisible(false);
				break;
			case "Laptop" :
				authorLb.setVisible(false);
				bookLanguageLb.setVisible(false);
				publisherLb.setVisible(false);
				isbnLb.setVisible(false);
				genreLb.setVisible(false);
				author.setVisible(false);
				bookLanguage.setVisible(false);
				publisher.setVisible(false);
				isbn.setVisible(false);
				genre.setVisible(false);
				
				manufacturerLb.setVisible(true);
				osLb.setVisible(true);
				modelLb.setVisible(true);
				manufacturer.setVisible(true);
				operatingSystem.setVisible(true);
				model.setVisible(true);
				
				directorLb.setVisible(false);
				runtimeLb.setVisible(false);
				dvdLanguageLb.setVisible(false);
				subtitlesLb.setVisible(false);
				director.setVisible(false);
				runtime.setVisible(false);
				dvdLanguage.setVisible(false);
				subLanguages.setVisible(false);
				break;
			case "Dvd" :
				authorLb.setVisible(false);
				bookLanguageLb.setVisible(false);
				publisherLb.setVisible(false);
				isbnLb.setVisible(false);
				genreLb.setVisible(false);
				author.setVisible(false);
				bookLanguage.setVisible(false);
				publisher.setVisible(false);
				isbn.setVisible(false);
				genre.setVisible(false);
				
				manufacturerLb.setVisible(false);
				osLb.setVisible(false);
				modelLb.setVisible(false);
				manufacturer.setVisible(false);
				operatingSystem.setVisible(false);
				model.setVisible(false);
				
				directorLb.setVisible(true);
				runtimeLb.setVisible(true);
				dvdLanguageLb.setVisible(true);
				subtitlesLb.setVisible(true);
				director.setVisible(true);
				runtime.setVisible(true);
				dvdLanguage.setVisible(true);
				subLanguages.setVisible(true);
				break;
			default: 
                break;
				
		}
		
    }
	
	@FXML
    void initialize() {
		resourceChoice.getItems().add("Book");
		resourceChoice.getItems().add("Laptop");
		resourceChoice.getItems().add("Dvd");
		resourceChoice.setValue("Book");
    }

}
