package cs230.application;

import java.util.ArrayList;

import cs230.system.DatabaseManager;
import cs230.system.SharedData;
import cs230.system.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		//try setting up the scheduler for the database update
		//launch the login window to start the program
	        ArrayList<User> userList = (ArrayList<User>) DatabaseManager.getTable("user");
	        if(userList == null || userList.isEmpty())
	        {
	                SharedData.setIsFirstRun(true);
	                try {
	                        VBox root =
	                        FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/NewUser.fxml"));
	                    primaryStage.initStyle(StageStyle.UNDECORATED);
	                    Scene scene = new Scene(root);
	                    primaryStage.setScene(scene);
	                    primaryStage.centerOnScreen();
	                    primaryStage.show();
	                    primaryStage.centerOnScreen();
	                } catch(Exception e) {
	                    e.printStackTrace();
	                } 
	        }
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
