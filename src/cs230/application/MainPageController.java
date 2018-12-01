package cs230.application;

import java.util.ArrayList;
import cs230.system.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class MainPageController {
	
	@FXML
	private VBox sideOptions;
	
	@FXML
	public void initialize()
	{
		TitledPane resources = new TitledPane();
		resources.setText("View Resource");
		VBox resourceChoices = new VBox();
		Hyperlink allChoice = new Hyperlink();
		allChoice.setText("All resources");
		allChoice.setOnAction(e ->loadAllPage());
		Hyperlink laptopChoice = new Hyperlink();
		laptopChoice.setText("Laptops");
		laptopChoice.setOnAction(e ->loadLaptopPage());
		Hyperlink bookChoice = new Hyperlink();
		bookChoice.setText("Books");
		bookChoice.setOnAction(e -> loadBookPage());
		Hyperlink dvdChoice = new Hyperlink();
		dvdChoice.setText("DVDs");
		dvdChoice.setOnAction(e -> loadDVDPage());
		resourceChoices.getChildren().add(allChoice);
		resourceChoices.getChildren().add(laptopChoice);
		resourceChoices.getChildren().add(bookChoice);
		resourceChoices.getChildren().add(dvdChoice);
		resources.setContent(resourceChoices);
		sideOptions.getChildren().add(resources);
	}
	
	private void loadAllPage()
	{
//		ArrayList<Resource> resources = new ArrayList<Resource>();
//		resources.addAll(getLaptopList());
//		resources.addAll(getDVDList());
//		resources.addAll(getBookList());
	}
	
	private void loadLaptopPage()
	{
		
	}
	
	private void loadDVDPage()
	{
		ArrayList resources = new ArrayList();
	}
	
	private void loadBookPage()
	{
		ArrayList resources = new ArrayList();
	}
	
	private void getLaptopList()
	{
		
	}
	
	private void getBookList()
	{
		
	}
	
	private void getDVDList()
	{
		
	}
}
