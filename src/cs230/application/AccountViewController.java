package cs230.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author 959470
 * @version 1.0
 */
public class AccountViewController {
	//the resources to be displayed
    @FXML private ResourceBundle resources;
    // 
    @FXML private URL location;
    // the users first name
    @FXML private Label firstName;
    // the users last name
    @FXML private Label lastName;
    // the users phone number
    @FXML private Label mobileNumber;
    //the users address
    @FXML private Label address;
    //textfield to capture first name
    @FXML private TextField firstNametxt;
    //textfield to capture last name
    @FXML private TextField lastNametxt;
    //textfield to capture phone number
    @FXML private TextField mobNumtxt;
    //textfield to capture address
    @FXML private TextField addresstxt;
    //textfield to capture firstname
    @FXML private Button editFirstName;
    //button to edit lastname
    @FXML private Button editLastName;
    //button to edit the mobile number
    @FXML private Button editMobNum;
    //button to edit address
    @FXML private Button editAddress;
    //button to save first name
    @FXML private Button saveFn;
    //button to save last name
    @FXML private Button saveLn;
    //button to save phone number
    @FXML private Button saveMobNum;
    //button to save address
    @FXML private Button saveAdr;
    //button to cancel a popup
    @FXML private Button cancell1;
    //button to cancel a popup
    @FXML private Button cancell2;
    //button to cancel a popup
    @FXML private Button cancell3;
    //button to cancel a popup
    @FXML private Button cancell4;

    /**
     * sets all window features to be invisible
     * @param event event to trigger code
     */
    @FXML
    void cancell(MouseEvent event) {
		editFirstName.setVisible(true);
		editLastName.setVisible(true);
		editAddress.setVisible(true);
		editMobNum.setVisible(true);
		saveFn.setVisible(false);
		saveLn.setVisible(false);
		saveAdr.setVisible(false);
		saveMobNum.setVisible(false);
		cancell1.setVisible(false);
		cancell2.setVisible(false);
		cancell3.setVisible(false);
		cancell4.setVisible(false);
		firstNametxt.setVisible(false);
		lastNametxt.setVisible(false);
		mobNumtxt.setVisible(false);
		addresstxt.setVisible(false);
    }

    /**
     * set the relevant features to be visible so 
     * user can edit address
     * @param event even to trigger this code
     */
    @FXML
    void editFieldAddress(MouseEvent event) {
		addresstxt.setVisible(true);
		addresstxt.setText(address.getText());
		editLastName.setVisible(false);
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		saveAdr.setVisible(true);
		cancell4.setVisible(true);
    }

    /**
     * set the relevant features to be visible so 
     * user can edit first name
     * @param event even to trigger this code
     */
    @FXML
    void editFieldFname(MouseEvent event) {
		firstNametxt.setVisible(true);
		firstNametxt.setText(firstName.getText());
		editLastName.setVisible(false);
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		saveFn.setVisible(true);
		cancell1.setVisible(true);
    }

    /**
     * set the relevant features to be visible so 
     * user can edit last name
     * @param event even to trigger this code
     */
    @FXML
    void editFieldLastName(MouseEvent event) {
		lastNametxt.setVisible(true);
		lastNametxt.setText(lastName.getText());
		editLastName.setVisible(false);
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		saveLn.setVisible(true);
		cancell2.setVisible(true);
    }

    /**
     * set the relevant features to be visible so 
     * user can edit phone number
     * @param event even to trigger this code
     */
    @FXML
    void editFieldMbNum(MouseEvent event) {
		mobNumtxt.setVisible(true);
		mobNumtxt.setText(mobileNumber.getText());
		editLastName.setVisible(false);
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		saveMobNum.setVisible(true);
		cancell3.setVisible(true);
    }
    
    /**
     * saves the address
     * @param event even to trigger this code
     */
    @FXML
    void saveAdress(MouseEvent event) {
		address.setText(addresstxt.getText());
		cancell(event);
    }

    /**
     * saves the first name
     * @param event even to trigger this code
     */
    @FXML
    void saveFirstName(MouseEvent event) {
		firstName.setText(firstNametxt.getText());
		cancell(event);
    }

    /**
     * saves the last name
     * @param event even to trigger this code
     */
    @FXML
    void saveLastName(MouseEvent event) {
		lastName.setText(lastNametxt.getText());
		cancell(event);
    }

    /**
     * saves the phone number
     * @param event even to trigger this code
     */
    @FXML
    void saveMobNum(MouseEvent event) {
		mobileNumber.setText(mobNumtxt.getText());
		cancell(event);
    }
}
