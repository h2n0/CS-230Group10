package cs230.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		//try setting up the scheduler for the database update
		//launch the login window to start the program
		try {
		        AnchorPane root =
				FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/Login.fxml"));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			primaryStage.centerOnScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
