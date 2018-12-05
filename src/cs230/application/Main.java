package cs230.application;
	
import java.util.Date;
import java.util.Timer;

import cs230.system.UpdateDatabase;
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
		try {
			//get todays date
			Date today = new Date();
			
			//long delay is time in milliseconds between the scheduled code 
			long delay = 10000;
			
			//set a new timer and load in updateDB and set to run everyday at midday
			Timer timer = new Timer();
			timer.schedule(new UpdateDatabase(), today, delay );
			
		} catch (Exception e){
			System.out.println("database update failed");
		}
		
		//launch the login window to start the program
		try {
			AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/Login.fxml"));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("cs230/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
