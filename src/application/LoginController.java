package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	
	@FXML
	private void handleLoginAction(ActionEvent event ) {
		//DatabaseManager.searchpartial()
		String inputUsername = usernameField.getText();
		if(inputUsername == "test")
		{
			changeToMainPage();
		}
	}
	
	private void changeToMainPage() {
		BorderPane root = null;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getClassLoader().getResource("MainPage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
		Stage stage = (Stage)loginButton.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}