package cs230.application;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controls a new user page.
 * @author 908112
 * @version 1.0
 */
public class NewUserController {
        public boolean isFirstRun;
        @FXML
        private VBox rootVBox;
        @FXML
        private Button switchAvatarButton;
        @FXML
        private HBox defaultAvatarBox;
        @FXML
        private VBox drawAvatarBox;
        @FXML
        private Button switchToUser;
        @FXML
        private Button switchToLibrarian;
        @FXML
        private Button saveButton;
        @FXML
        private Label incorrectDetails; 
        @FXML
        private TextField usernameTextBox;
        @FXML
        private TextField firstNameTextBox;
        @FXML
        private TextField lastNameTextBox;
        @FXML
        private TextField phoneNumberTextBox;
        @FXML
        private TextField houseNumberTextBox;
        @FXML
        private TextField roadNameTextBox;
        @FXML
        private TextField cityTextBox;
        @FXML
        private TextField postcodeTextBox;
        @FXML 
        private GridPane staffEditArea;
        @FXML 
        private TextField employmentDateTextBox;
        @FXML 
        private TextField staffNumberTextBox;
        @FXML
        private Button image1Button;
        @FXML
        private ImageView image1;
        @FXML
        private Button image2Button;
        @FXML
        private ImageView image2;
        @FXML
        private Button image3Button;
        @FXML
        private ImageView image3;
        @FXML
        private Button image4Button;
        @FXML
        private ImageView image4;
        @FXML
        private Button image5Button;
        @FXML
        private ImageView image5;
        @FXML
        private Button image6Button;
        @FXML
        private ImageView image6;
        
        private boolean isLibrarian = false;
        
        private boolean image1Selected = false;
        private boolean image2Selected = false;
        private boolean image3Selected = false;
        private boolean image4Selected = false;
        private boolean image5Selected = false;
        private boolean image6Selected = false;
        
        private String imagePath = "";
        /**
         * Constructor for the Controller
         */
        public NewUserController () {
                
        }
        @FXML
        public void initialize () {
                staffEditArea.setVisible(false);
                String path = new File("").getAbsolutePath();
                image1 = new ImageView(path + "/Assets/image1.png");
                image2 = new ImageView(path + "/Assets/image2.png");
                image3 = new ImageView(path + "/Assets/image3.png");
                image4 = new ImageView(path + "/Assets/image4.png");
                image5 = new ImageView(path + "/Assets/image5.png");
                image6 = new ImageView(path + "/Assets/image6.png");
                Pane root = null;
                try {
                    // Initalise and load FXML for the main page
                    root = FXMLLoader.load(
                        getClass().getClassLoader().getResource(
                            "cs230/application/AvatarDraw.fxml"));
                } catch (IOException e) {
                        e.printStackTrace();
                    }
                drawAvatarBox.getChildren().add(root); 
                if (isFirstRun) {
                        switchToUser.setVisible(false);
                        switchToLibrarian.setVisible(false);
                }
        }
        
        private void setIsFirstRun (boolean isFirstRun) {
                this.isFirstRun = isFirstRun;
        }
        @FXML
        private void handleSwitchUser (ActionEvent event) {
                staffEditArea.setVisible(false);
                isLibrarian = false;
        }
        @FXML
        private void handleSwitchLibrarian (ActionEvent event) {
                staffEditArea.setVisible(true);
                isLibrarian = true;
        }
        @FXML
        private void handleImage1SelectedButton (ActionEvent event) {
                selectImage(1);
                String path = new File("").getAbsolutePath();
                imagePath = path + "/Assets/image1.png";
        }
        @FXML
        private void handleImage2SelectedButton (ActionEvent event) {
                selectImage(2);
                String path = new File("").getAbsolutePath();
                imagePath = path + "/Assets/image2.png";
        }
        @FXML
        private void handleImage3SelectedButton (ActionEvent event) {
                selectImage(3);
                String path = new File("").getAbsolutePath();
                imagePath = path + "/Assets/image3.png";
        }
        @FXML
        private void handleImage4SelectedButton (ActionEvent event) {
                selectImage(4);
                String path = new File("").getAbsolutePath();
                imagePath = path + "/Assets/image4.png";
        }
        @FXML
        private void handleImage5SelectedButton (ActionEvent event) {
                selectImage(5);
                String path = new File("").getAbsolutePath();
                imagePath = path + "/Assets/image5.png";
        }
        @FXML
        private void handleImage6SelectedButton (ActionEvent event) {
                selectImage(6);
                String path = new File("").getAbsolutePath();
                imagePath = path + "/Assets/image6.png";
        }
        @FXML
        private void handleSwitchButton (ActionEvent event) {
                defaultAvatarBox.setVisible(!defaultAvatarBox.visibleProperty().get());
                drawAvatarBox.setVisible(!drawAvatarBox.visibleProperty().get());
                if (drawAvatarBox.visibleProperty().get()) {
                        String path = new File("").getAbsolutePath();
                        path = path + "/Assets/" + SharedData.getUsername() + "Image.png";
                        imagePath = path;
                } else {
                        imagePath = "";
                }
        }
        @FXML
        private void handleSaveButton (ActionEvent event) {
                String usernameAdd = "";
                String firstNameAdd = "";
                String lastNameAdd = "";
                String phoneNumberAdd = "";
                String houseNumberAdd = "";
                String roadNameAdd = "";
                String cityAdd = "";
                String postcodeAdd = "";
                String employmentDateAdd = "";
                String staffNumberAdd = "";
                usernameAdd = usernameTextBox.getText();
                firstNameAdd = firstNameTextBox.getText();
                lastNameAdd = lastNameTextBox.getText();
                phoneNumberAdd = phoneNumberTextBox.getText();
                houseNumberAdd = houseNumberTextBox.getText();
                roadNameAdd = roadNameTextBox.getText();
                cityAdd = cityTextBox.getText();
                postcodeAdd = postcodeTextBox.getText();
                employmentDateAdd = employmentDateTextBox.getText();
                staffNumberAdd = staffNumberTextBox.getText();
                boolean canContinue = false;
                if (!usernameAdd.isEmpty() && !firstNameAdd.isEmpty() && 
                                !lastNameAdd.isEmpty() && !phoneNumberAdd.isEmpty() &&
                                !houseNumberAdd.isEmpty() && !roadNameAdd.isEmpty() && 
                                !cityAdd.isEmpty() && !postcodeAdd.isEmpty() && 
                                imagePath != "") {
                        canContinue = true;
                }
                if (canContinue && !isLibrarian) {
                        Address newAddress = new Address(houseNumberAdd, roadNameAdd, cityAdd, postcodeAdd);
                        User newUser = new User(usernameAdd, firstNameAdd, lastNameAdd, phoneNumberAdd, newAddress, 0.0, imagePath);
                        DatabaseManager.saveRecord(newUser, "user");
                        changePage();
                }
                if (canContinue && isLibrarian) {
                        Date employmentDate = new SimpleDateFormat("dd/MM/yyyy").parse(employmentDateAdd);
                        Address newAddress = new Address(houseNumberAdd, roadNameAdd, cityAdd, postcodeAdd);
                        Librarian newLibrarian = new Librarian(usernameAdd, firstNameAdd, lastNameAdd, phoneNumberAdd, newAddress, 0.0, imagePath, employmentDate, staffNumberAdd);
                        DatabaseManager.saveRecord(newLibrarian, "librarian");
                        changePage();
                }
                if (!canContinue) {
                        incorrectDetails.setVisible(true);
                }
        }
        private void changePage() {
                AnchorPane root = null;
                try {
                    // Initalise and load FXML for the main page
                    root = FXMLLoader.load(
                        getClass().getClassLoader().getResource(
                            "cs230/application/UserInfo.fxml"));

                    // Initalise and load CSS for scene
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(
                        getClass().getClassLoader().getResource(
                            "cs230/application/application.css").toExternalForm());

                    // Initalise and display stage
                    Stage stage =
                        (Stage) incorrectDetails.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        
        private void selectImage(int numChosen) {
                image1Selected = false;
                image2Selected = false;
                image3Selected = false;
                image4Selected = false;
                image5Selected = false;
                image6Selected = false;
                switch (numChosen) {
                case 1: image1Selected = true;
                        break;
                case 2: image2Selected = true;
                        break;
                case 3: image3Selected = true;
                        break;
                case 4: image4Selected = true;
                        break; 
                case 5: image5Selected = true;
                        break;
                default:image6Selected = true;
                        break;
                }
                
        }
}
