package cs230.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

	public void handleExit(ActionEvent event) {
		changeToLogin();
	}


	private void changeToLogin() {
		BorderPane root = null;
		try {
			root =
				(BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/Login.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}
