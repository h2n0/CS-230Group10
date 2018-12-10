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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
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
//                balance.textProperty()
//                .set(Double.toString(currentUser.getBalance()));
//                if(SharedData.getIsLibrarian())
//                {
//                        username.setOnAction(e -> loadLibrarianInfo()); 
//                }
 //               else
 //               {
//                        username.setOnAction(e -> loadUserInfo()); 
 //               }
                
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
                Hyperlink allChoice = new Hyperlink();
                allChoice.setText("All resources");
                allChoice.setOnAction(e -> loadAllPage());
                Hyperlink laptopChoice = new Hyperlink();
                laptopChoice.setText("Laptops");
                laptopChoice.setOnAction(e -> loadLaptopPage());
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

        @FXML
        private void handleSearch(ActionEvent event) {
                ArrayList<Resource> resources = getResourceList();
                if (currentResourceSelection.equals(COMBOBOX_BOOK)) {
                        resources.removeIf(r -> r.getType() != "book");
                } else if (currentResourceSelection.equals(COMBOBOX_LAPTOP)) {
                        resources.removeIf(r -> r.getType() != "Laptop");
                } else if (currentResourceSelection.equals(COMBOBOX_DVD)) {
                        resources.removeIf(r -> r.getType() != "Dvd");
                }
                if (!searchBox.textProperty().equals(null)) {
                        resources.removeIf(r -> r.getTitle()
							.contains((CharSequence) searchBox.textProperty()));
                }
                loadListPage(resources);
        }

        private void loadAllPage() {
                loadListPage(getResourceList());
        }

        private void loadDVDPage() {
                ArrayList<Resource> resources = getResourceList();
                resources.removeIf(r -> r.getType().equals("Dvd"));
                loadListPage(resources);
        }

        private void loadBookPage() {
                ArrayList<Resource> resources = getResourceList();
                resources.removeIf(r -> r.getType().equals("book"));
                loadListPage(resources);
        }

        private void loadLaptopPage() {
                ArrayList<Resource> resources = getResourceList();
                resources.removeIf(r -> r.getType().equals("Laptop"));
                loadListPage(resources);
        }

        private void loadListPage(ArrayList<Resource> resources) {
                VBox root;
                try {
                        root  = FXMLLoader.load(
                                        getClass().getClassLoader().getResource("cs230/application/ResourceList.fxml"));
                        //mainContent.setPrefHeight(listPage.getPrefHeight());
                        //mainContent.setPrefWidth(listPage.getPrefWidth());
                        mainContent.setContent(root);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private ArrayList<Resource> getResourceList() {
                ArrayList<Resource> resourceList =
                                new ArrayList<Resource>();
                ArrayList<Dvd> dvdList
                = (ArrayList<Dvd>) DatabaseManager.getTable("dvd");
                ArrayList<Book> bookList = (ArrayList<Book>) DatabaseManager.getTable("book");
                ArrayList<Laptop> laptopList
                = (ArrayList<Laptop>) DatabaseManager.getTable("laptop");
                resourceList
                .addAll((ArrayList<Resource>) (ArrayList<?>) dvdList);
                resourceList
                .addAll((ArrayList<Resource>) (ArrayList<?>) bookList);
                resourceList
                .addAll((ArrayList<Resource>) (ArrayList<?>) laptopList);
                return resourceList;
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
                        try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader()
                                                .getResource("cs230" +
                                                        "/application" +
                                                        "/UserInfo" +
                                                        ".fxml"));
                                VBox mainPage = fxmlLoader.load();
                                mainContent.setPrefHeight(mainPage.getPrefHeight());
                                mainContent.setPrefWidth(mainPage.getPrefWidth());
                                mainContent.setContent(mainPage);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
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