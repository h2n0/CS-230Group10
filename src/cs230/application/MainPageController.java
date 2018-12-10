package cs230.application;

import cs230.system.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * @author 901306 & Scott (960689)
 */
public class MainPageController {

        private static final String COMBOBOX_ALL = "All";

        private static final String COMBOBOX_BOOK = "Book";

        private static final String COMBOBOX_DVD = "Dvd";

        private static final String COMBOBOX_LAPTOP = "Laptop";

        @FXML
        private VBox sideOptions;

        @FXML
        private Hyperlink username;

        @FXML
        private Label balance;

        @FXML
        private ComboBox<String> resourcePicker;

        @FXML
        private Button logOutButton;

        @FXML
        private Button searchButton;

        @FXML
        private TextField searchBox;

        @FXML
        private ImageView userImage;

        @FXML
        private Hyperlink dashboardLink;

        @FXML
        private Hyperlink fineLink;

        @FXML
        private Hyperlink transactionLink;

        @FXML
        private Hyperlink createResLink;

        @FXML
        private Hyperlink addUserLink;
		
        @FXML
        private ScrollPane mainContent;

        private String currentResourceSelection;



        @FXML
        public void initialize() {
                setResourceLinks();
                balance.textProperty()
                .set(Double.toString(SharedData.getBalance()));
                if(SharedData.getIsLibrarian())
                {
                        username.setOnAction(e -> loadLibrarianInfo()); 
                }
                else
                {
                        username.setOnAction(e -> loadUserInfo()); 
                }
                
                updateComboBox();
                username.setText(SharedData.getUsername());
                balance.textProperty().set("Balance: " + Double.toString(SharedData.getBalance()));
                userImage.setImage(SharedData.getAvatar());
                //userImage = new ImageView(SharedData.getAvatar());
                currentResourceSelection = COMBOBOX_ALL;
        }

        public void setCurrentUser(User currentUser) {
                this.currentUser = currentUser;
        }
        
        private void loadLibrarianInfo() {
                VBox root;
                try {
                        root  = FXMLLoader.load(
                                        getClass().getClassLoader()
                                        .getResource("cs230/application/Librarian"
                                                        + "AccountView.fxml"));
                        //mainContent.setPrefHeight(listPage.getPrefHeight());
                        //mainContent.setPrefWidth(listPage.getPrefWidth());
                        mainContent.setContent(root);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        
        private void loadUserInfo() {
                VBox root;
                try {
                        root  = FXMLLoader.load(
                                        getClass().getClassLoader()
                                        .getResource("cs230/application"
                                                        + "/AccountView.fxml"));
                        //mainContent.setPrefHeight(listPage.getPrefHeight());
                        //mainContent.setPrefWidth(listPage.getPrefWidth());
                        mainContent.setContent(root);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                
        }
        
        private User currentUser;

        private void updateComboBox() {
                ObservableList<String> pickerOptions = FXCollections
				.observableArrayList(COMBOBOX_ALL, COMBOBOX_DVD,
                                COMBOBOX_BOOK, COMBOBOX_LAPTOP);
                resourcePicker.getItems().addAll(pickerOptions);
                resourcePicker.valueProperty()
				.addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue ov, String t, String selection) {
                                currentResourceSelection = selection;
                        }
                });
        }

        @FXML
        private void handleLogout(ActionEvent event) {
                try {
                        Stage stage = (Stage) mainContent.getScene()
						.getWindow();
                        AnchorPane root = FXMLLoader.load(getClass()
						.getClassLoader()
						.getResource("cs230/application/Login.fxml"));
                        Scene scene = new Scene(root);
                        scene.getStylesheets()
						.add(getClass().getClassLoader()
                                        .getResource("cs230/application/application.css")
										.toExternalForm());
                        stage.setScene(scene);
                        stage.show();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        

        private void setResourceLinks() {
                TitledPane resources = new TitledPane();
                resources.setText("View Resource");
                VBox resourceChoices = new VBox();
                Hyperlink laptopChoice = new Hyperlink();
                laptopChoice.setText("Laptops");
                ArrayList<Laptop>laptopList = (ArrayList<Laptop>) DatabaseManager.getTable("laptop");
                laptopChoice.setOnAction(e -> loadLaptopPage(laptopList));
                Hyperlink bookChoice = new Hyperlink();
                ArrayList<Book>bookList = (ArrayList<Book>) DatabaseManager.getTable("book");
                bookChoice.setText("Books");
                bookChoice.setOnAction(e -> loadBookPage(bookList));
                Hyperlink dvdChoice = new Hyperlink();
                dvdChoice.setText("DVDs");
                ArrayList<Dvd>dvdList = (ArrayList<Dvd>) DatabaseManager.getTable("dvd");
                dvdChoice.setOnAction(e -> loadDVDPage(dvdList));
                resourceChoices.getChildren().add(laptopChoice);
                resourceChoices.getChildren().add(bookChoice);
                resourceChoices.getChildren().add(dvdChoice);
                resources.setContent(resourceChoices);
                sideOptions.getChildren().add(resources);
        }

        @FXML
        private void handleSearch(ActionEvent event) {
                if (!searchBox.getText().equals(null)) {
                        if (currentResourceSelection.equals(COMBOBOX_DVD)) {
                                ArrayList<Dvd>dvdList = (ArrayList<Dvd>) DatabaseManager.getTable("dvd");
                                dvdList.removeIf(d -> d.getTitle().equals(searchBox.getText()));
                                loadDVDPage(dvdList);
                        } else if (currentResourceSelection.equals(COMBOBOX_LAPTOP)) {
                                ArrayList<Laptop>laptopList = (ArrayList<Laptop>) DatabaseManager.getTable("laptop"); 
                                loadLaptopPage(laptopList);
                        } else if (currentResourceSelection.equals(COMBOBOX_BOOK)) {
                                ArrayList<Book>bookList = (ArrayList<Book>) DatabaseManager.getTable("book"); 
                                loadBookPage(bookList);
                        }
                }
        }

        private void loadDVDPage(ArrayList<Dvd> dvdList) {
                try {
                        ResourceListController controller =
                                        new ResourceListController();
                        controller.setDvdToShow(dvdList);
                        FXMLLoader loader = new 
                                        FXMLLoader(getClass().getResource("ResourceList.fxml"));
                        loader.setController(controller);
                        VBox root = loader.load();
                        mainContent.setContent(root);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private void loadBookPage(ArrayList<Book> bookList) {
                try {
                        ResourceListController controller =
                                        new ResourceListController();
                        controller.setbookToShow(bookList);
                        FXMLLoader loader = new 
                                        FXMLLoader(getClass().getResource("ResourceList.fxml"));
                        loader.setController(controller);
                        VBox root = loader.load();
                        mainContent.setContent(root);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private void loadLaptopPage(ArrayList<Laptop> laptopList) {
                try {
                        ResourceListController controller =
                                        new ResourceListController();
                        controller.setLaptopToShow(laptopList);
                        FXMLLoader loader = new 
                                        FXMLLoader(getClass().getResource("ResourceList.fxml"));
                        loader.setController(controller);
                        VBox root = loader.load();
                        mainContent.setContent(root);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        @FXML
        private void handleFineAction(ActionEvent event) {
                try {
                        VBox finePage = FXMLLoader
                                        .load(getClass().getClassLoader().getResource("cs230/application/Fine.fxml"));
                        mainContent.setPrefHeight(finePage.getPrefHeight());
                        mainContent.setPrefWidth(finePage.getPrefWidth());
                        mainContent.setContent(finePage);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
		
		 /**
        * Handles exiting and logging out of the main menu back to the login menu
        * @param event A button pressed event
        */
        @FXML
        private void handleExit (ActionEvent event){
		    try {
		            // Create a new login scene
			        AnchorPane root =
				        FXMLLoader.load(getClass().getClassLoader().getResource("cs230/application/Login.fxml"));
			        Scene scene = new Scene(root);

			        // Get current stage
			        Stage stage =
                    (Stage) logOutButton.getScene().getWindow();

			        stage.setScene(scene);
			        stage.centerOnScreen();

		    } catch (IOException e) {
                    System.exit(0);
            }
	}

        @FXML
        private void toMainpage(ActionEvent event) {
                if (SharedData.getIsLibrarian()) {
                        try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader()
                                                .getResource("cs230/application/LibrarianMainPage.fxml"));
                                VBox mainPage = fxmlLoader.load();
                                mainContent.setPrefHeight(mainPage.getPrefHeight());
                                mainContent.setPrefWidth(mainPage.getPrefWidth());
                                mainContent.setContent(mainPage);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                } else {
                        //To implement
                }
        }
        
        @FXML
        private void handleCreateRes(ActionEvent event) {
                try {
                        FXMLLoader fxmlLoader =
                                new FXMLLoader(getClass().getClassLoader().getResource("cs230/application/CreateResourceView.fxml"));
                        BorderPane createResource = fxmlLoader.load();
                        mainContent.setContent(createResource);
                } catch(IOException e) {
                        e.printStackTrace();
                }
        }

        @FXML
        private void handleViewTrans(ActionEvent event) {
                try {
                        FXMLLoader fxmlLoader =
                                new FXMLLoader(getClass().getClassLoader().getResource("cs230/application/Transaction.fxml"));
                        VBox viewTrans = fxmlLoader.load();
                        mainContent.setContent(viewTrans);
                } catch(IOException e) {
                        e.printStackTrace();
                }
        }

        @FXML
        private void handleAddUser(ActionEvent event) {
                try {
                        FXMLLoader fxmlLoader =
                                new FXMLLoader(getClass().getClassLoader().getResource("cs230/application/NewUser.fxml"));
                        VBox viewAddUser = fxmlLoader.load();
                        mainContent.setContent(viewAddUser);
                } catch(IOException e) {
                        e.printStackTrace();
                }
        }
}