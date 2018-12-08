package cs230.application;

import java.io.IOException;

import cs230.system.DatabaseManager;
import cs230.system.SharedData;
import cs230.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**This controllers the ui for the login page
 * 
 * @author Jack
 *
 */
public class LoginController {

	@FXML
	private Label userNotFound;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private Button loginButton;

	/**
	 * Handles the exit button being pressed
	 * @param event The button being clicked
	 */
	@FXML
	private void handExitAction(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * Handles the login button being pressed
	 * @param event The button being clicked
	 */
	@FXML
	private void handleLoginAction(ActionEvent event ) {
		boolean exists;
		String inputUsername = usernameField.getText();

		//Create a user object to search in DB
		User activeUser = new User(inputUsername, null, null, null, null, null, null);
		exists = DatabaseManager.checkForRecord(activeUser, "user");

		//If they exist log in, else show an error
	    	if(exists) {
			// Get all of user's details
			activeUser = (User)
				DatabaseManager.searchExact(activeUser,
					"user");

			SharedData.setUser(activeUser);
			changeToMainPage();
			userNotFound.setVisible(false);
		} else {
	    		userNotFound.setVisible(true);
	    	}
	}

	/**
	 * Loads and shows the main page of the program
	 */
	private void changeToMainPage() {
		AnchorPane root = null;
		try {
			// Initalise and load FXML for the main page
			root = FXMLLoader.load(
				getClass().getClassLoader().getResource(
					"cs230/application/UserInfo.fxml"));

			// Initalise and load CSS for scene
			Scene scene = new Scene(root);
			scene.getStylesheets().add(
				getClass().getClassLoader().getResource(
					"cs230/application/application.css").toExternalForm());

			// Initalise and display stage
			Stage stage =
				(Stage) loginButton.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}