package cs230.application;

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
		//cs230.system.DatabaseManager.searchpartial()
		String inputUsername = usernameField.getText();
		System.out.println(inputUsername);
	    if(inputUsername.equals("test")) {
			changeToMainPage();
			userNotFound.setVisible(false);
		}
	    else
	    {
	    	userNotFound.setVisible(true);
	    }
	}
	
	private void changeToMainPage() {
		BorderPane root = null;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/MainPage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());
		Stage stage = (Stage)loginButton.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}