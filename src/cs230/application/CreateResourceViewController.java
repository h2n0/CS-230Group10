package cs230.application;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import cs230.system.*;

import java.util.ArrayList;

/**
 * the controller behind create resource
 * @author 959470
 * @version 1.0
 *
 */
public class CreateResourceViewController {
		//textfield to capture title
        @FXML private TextField title;
        //textfield to capture year
        @FXML private TextField year;
        //textfield to capture thumbnail
        @FXML private TextField thumbnail;
        //label to display author
        @FXML private Label authorLb;
        //label to display book language
        @FXML private Label bookLanguageLb;
        //label to display publisher
        @FXML private Label publisherLb;
        //label to display isbn
        @FXML private Label isbnLb;
        //label to display genere
        @FXML private Label genreLb;
        //textfield to capture author
        @FXML private TextField author;
        //textfield to capture book language
        @FXML private TextField bookLanguage;
        //textfield to capture publisher
        @FXML private TextField publisher;
        //textfield to capture isbn
        @FXML private TextField isbn;
        //textfield to capture genre
        @FXML private TextField genre;
        //label to show manufacturer
        @FXML private Label manufacturerLb;
        //label to show OS
        @FXML private Label osLb;
        //label to show model
        @FXML private Label modelLb;
        //textfield to capture manufacturer
        @FXML private TextField manufacturer;
        //textfield to capture OS
        @FXML private TextField operatingSystem;
        //textfield to capture model
        @FXML private TextField model;
        //label to show director
        @FXML private Label directorLb;
        //label to show runtime
        @FXML private Label runtimeLb;
        //label to show subtitles
        @FXML private Label subtitlesLb;
        //label to show dvd language
        @FXML private Label dvdLanguageLb;
        //textfield to capture director
        @FXML private TextField director;
        //textfield to capture runtime
        @FXML private TextField runtime;
        //textfield to capture dvd language
        @FXML private TextField dvdLanguage;
        //textfield to capture the subtitle languages
        @FXML private TextField subLanguages;
        //choice box to choose what resource type
        @FXML private ChoiceBox<String> resourceChoice = new ChoiceBox<>();
        //label to show unfinished
        @FXML private Label unfinishedLb;
        
	/**
	 * get the next id from the database
	 * @return the id from the database
	 */
	private String getNextId() {
		String newID = DatabaseManager.getNextID();
		return newID;
	}
 
		/**
		 * creates a resource
		 * @param event the event that triggered this code
		 */
        @FXML
        void createResource(MouseEvent event) {
		if (title.getText().equals("") || year.getText().equals("") || thumbnail.getText().equals("")){
			unfinishedLb.setVisible(true);
		}else {
			int year =Integer.parseInt(this.year.getText());
			String id = getNextId();
			System.out.println(id);
			String choice = resourceChoice.getValue();
			switch (choice) {
				case "Book" :
					if (bookLanguage.getText().equals("") || isbn.getText().equals("") || genre.getText().equals("")){
						unfinishedLb.setVisible(true);
					} else {
						
						unfinishedLb.setVisible(false);
						
						Book inputBook = new Book(id,
                                                        title.getText(),
                                                        year,
                                                        thumbnail.getText(),
                                                        author.getText(),
                                                        publisher.getText(),
                                                        genre.getText(),
                                                        isbn.getText(),
                                                        bookLanguage.getText());

						DatabaseManager.saveRecord(inputBook, "book");
						System.out.println("Saved successfully!");
					}
					break;
				case "Laptop" :
					if (manufacturer.getText().equals("") || operatingSystem.getText().equals("") || model.getText().equals("")){
						unfinishedLb.setVisible(true);
					} else {
						unfinishedLb.setVisible(false);
						Laptop laptop1 = new Laptop(id,title.getText(),year,thumbnail.getText(),manufacturer.getText(),model.getText(),operatingSystem.getText());
						DatabaseManager.saveRecord(laptop1, "laptop");
					}
					break;
				case "Dvd" :
					if (director.getText().equals("") || runtime.getText().equals("")){
						unfinishedLb.setVisible(true);
					} else {
						unfinishedLb.setVisible(false);
						String[] subArray = subLanguages.getText().split(",");
						ArrayList<String> subLang = new ArrayList<>();
						int i=0;
		
						while ( i != (subArray.length)) {
							subLang.add(subArray[i]);
							i++;
						}
						int runtime = Integer.parseInt(this.runtime.getText());
						Dvd dvd1 = new Dvd(id,title.getText(),year,thumbnail.getText(),director.getText(),runtime,dvdLanguage.getText(),subLang);
						DatabaseManager.saveRecord(dvd1, "dvd");
					}
					break;
			}
		}
    }

    /**
     * show the resources
     * @param event the event that triggered this code
     */
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
	
    /**
     * initialise the page
     */
    @FXML
    void initialize() {
		resourceChoice.getItems().add("Book");
		resourceChoice.getItems().add("Laptop");
		resourceChoice.getItems().add("Dvd");
		resourceChoice.setValue("Book");
    }
}