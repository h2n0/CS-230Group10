package cs230.application;
	
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import cs230.system.DatabaseManager;
import cs230.system.UpdateDatabase;
import cs230.system.User;
//import cs230.system.UpdateDatabase;
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
		try {
			//get todays date
			Date today = new Date();
			
			//long delay is time in milliseconds between the scheduled code 
			//43200000 milliseconds is 12hrs
			long delay = 43200000;
			
			//set a new timer and load in updateDB and set to run everyday at midday
			Timer timer = new Timer();
			timer.schedule(new UpdateDatabase(), today, delay );
			
		} catch (Exception e){
			System.out.println("database update failed");
		}
		ArrayList<User> userList = (ArrayList<User>) DatabaseManager.getTable("user");
		if(userList != null)
		{
		        //launch the login window to start the program
		        try {
		                AnchorPane root =
		                                FXMLLoader
		                                .load(getClass()
                                                .getClassLoader()
                                                .getResource("cs230/application/"
                                                        + "Login.fxml"));
		                primaryStage.initStyle(StageStyle.UNDECORATED);
		                Scene scene = new Scene(root);
		                scene.getStylesheets()
		                .add(getClass().getClassLoader()
		                                .getResource("cs230/"
                                                + "application/application.css")
		                                .toExternalForm());
		                primaryStage.setScene(scene);
		                primaryStage.centerOnScreen();
		                primaryStage.show();
		                primaryStage.centerOnScreen();
		        } catch(Exception e) {
		                e.printStackTrace();
		        }
		} else {
		        try {
                        VBox root = FXMLLoader.load(getClass()
                                        .getClassLoader()
                                        .getResource("cs230"
                                                + "/application/NewUser.fxml"));
                        NewUserController controller = new NewUserController();
                        controller.setIsFirstRun();
                        primaryStage.initStyle(StageStyle.UNDECORATED);
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(getClass()
                                        .getClassLoader()
                                        .getResource("cs230"
                                                + "/application/application.css")
                                        .toExternalForm());
                        primaryStage.setScene(scene);
                        primaryStage.centerOnScreen();
                        primaryStage.show();
                        primaryStage.centerOnScreen();
                } catch(Exception e) {
                        e.printStackTrace();
                }
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
