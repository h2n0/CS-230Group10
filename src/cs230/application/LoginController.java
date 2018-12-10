package cs230.application;

import java.io.IOException;

import cs230.system.DatabaseManager;
import cs230.system.Librarian;
import cs230.system.SharedData;
import cs230.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This controllers the ui for the login page
 * 
 * @author Jack
 * @version 1.0
 */
public class LoginController {
	// label to say the user wasnt found
	@FXML
	private Label userNotFound;
	// text field to capture user input
	@FXML
	private TextField usernameField;
	// button to log in
	@FXML
	private Button loginButton;

	/**
	 * Handles the exit button being pressed
	 * 
	 * @param event The button being clicked
	 */
	@FXML
	private void handExitAction(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * Handles the login button being pressed
	 * 
	 * @param event The button being clicked
	 */
	@FXML
	private void handleLoginAction(ActionEvent event) {
		boolean userExists;
		boolean libExists;
		String inputUsername = usernameField.getText();

		// Create a user object to search in DB
		User activeUser = new User(inputUsername, null, null, null, null, null, null);
		userExists = DatabaseManager.checkForRecord(activeUser, "user");
		Librarian librarian = new Librarian(inputUsername, null, null, null, null, null, null, null, null);
		libExists = DatabaseManager.checkForRecord(librarian, "librarian");

		// If they exist log in, else show an error
		if (userExists) {
			// Get all of user's details
			activeUser = (User) DatabaseManager.searchExact(activeUser, "user");

			SharedData.setUser(activeUser);
			userNotFound.setVisible(false);
			changeToMainPage();
		} else if (libExists) {
			activeUser = (User) DatabaseManager.searchExact(librarian, "librarian");

			SharedData.setUser(activeUser);
			SharedData.setIsLibrarian(true);
			userNotFound.setVisible(false);
			changeToMainPage();
		} else {
			userNotFound.setVisible(true);
		}
	}

	/**
	 * Loads and shows the main page of the program
	 */
	private void changeToMainPage() {
		BorderPane root;
		try {
			// Initalise and load FXML for the main page
			root = FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/MainPage.fxml"));

			// Initalise and load CSS for scene
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());

			// Initalise and display stage
			Stage stage = (Stage) loginButton.getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}