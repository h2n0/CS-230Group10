package cs230.application;

import java.net.URL;
import java.util.ResourceBundle;

import cs230.system.Address;
import cs230.system.DatabaseManager;
import cs230.system.SharedData;
import cs230.system.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * @author 959470 This is the controller for View.fxm, which let's the currently logged in User see and individually change their account details.
 *
 */

public class AccountViewController {
	//The currently logged in User
	private static User user ;
	@FXML
	private Label emptyFieldLb;

	@FXML
	private Label incorrectadressLb;
	
	@FXML
    private Label firstName;

    @FXML
    private Label mobileNumber;

    @FXML
    private Label address;

    @FXML
    private TextField firstNametxt;


    @FXML
    private TextField mobNumtxt;

    @FXML
    private TextField addresstxt;


    @FXML
    private Button editFirstName;

    @FXML
    private Button editMobNum;

    @FXML
    private Button editAddress;



    @FXML
    private Button saveFn;


    @FXML
    private Button saveMobNum;

    @FXML
    private Button saveAdr;



    @FXML
    private Button cancell1;

    @FXML
    private Button cancell3;

    @FXML
    private Button cancell4;


    /**
     * Removes all the text fields and save & exit buttons , reverting the page to it's default state
     */
    @FXML
    void cancell(MouseEvent event) {
		editFirstName.setVisible(true);

		editAddress.setVisible(true);
		editMobNum.setVisible(true);
		
		saveFn.setVisible(false);

		saveAdr.setVisible(false);
		saveMobNum.setVisible(false);
		
		cancell1.setVisible(false);

		cancell3.setVisible(false);
		cancell4.setVisible(false);
	
		firstNametxt.setVisible(false);
		mobNumtxt.setVisible(false);
		addresstxt.setVisible(false);

		emptyFieldLb.setVisible(false);
		incorrectadressLb.setVisible(false);
    }
    
    /**
     * Shows the text field for address as well as the corresponding save and cancel buttons. Hides all the rest
     */
    @FXML
    void editFieldAddress(MouseEvent event) {
		addresstxt.setVisible(true);
		addresstxt.setText(address.getText());
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		
		saveAdr.setVisible(true);
		cancell4.setVisible(true);
    }
    
    /**
     * Shows the text field for Full Name as well as the corresponding save and cancel buttons. Hides all the rest
     */
    @FXML
    void editFieldFname(MouseEvent event) {
		firstNametxt.setVisible(true);
		firstNametxt.setText(firstName.getText());
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		
		saveFn.setVisible(true);
		cancell1.setVisible(true);
    }

    /**
     * Shows the text field for mobile number as well as the corresponding save and cancel buttons. Hides all the rest
     */
    @FXML
    void editFieldMbNum(MouseEvent event) {
		mobNumtxt.setVisible(true);
		mobNumtxt.setText(mobileNumber.getText());
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		
		saveMobNum.setVisible(true);
		cancell3.setVisible(true);
    }
	
	
    /**
     * Checks if the field is empty or the address is entered wrong and shows the appropriate message, otherwise updates the address
     */
    @FXML
    void saveAdress(MouseEvent event) {
    	if (!addresstxt.getText().equals("")) {
    		try {
    			String[] adComp = addresstxt.getText().split(" ");
    			Address address1 = new Address(adComp[0],adComp[1],adComp[2],adComp[3]);
    			user.setAddress(address1);
    			address.setText(addresstxt.getText());
    			cancell(event);
    		}
    		catch (Exception e) {
    			incorrectadressLb.setVisible(true);
    		}
    	}else {
    		emptyFieldLb.setVisible(true);
    	}
    }
    
    /**
     * Checks if the field is empty and shows the appropriate message, otherwise updates the FullName
     */
    @FXML
    void saveFirstName(MouseEvent event) {
    	if (!firstNametxt.getText().equals("")) {
    		firstName.setText(firstNametxt.getText());
    		user.setName(firstNametxt.getText());
    		cancell(event);
    	} else {
    		emptyFieldLb.setVisible(true);
    	}
    }

    
    /**
     * Checks if the field is empty and shows the appropriate message, otherwise updates the mobile number
     */
    @FXML
    void saveMobNum(MouseEvent event) {
    	if (!mobNumtxt.getText().equals("")) {
			mobileNumber.setText(mobNumtxt.getText());
			user.setMobileNum(mobNumtxt.getText());
			cancell(event);
    	} else {
    		emptyFieldLb.setVisible(true);
    	}
    }
	
    /**
     * Intitializes the page, displaying the correct values depending on the currently logged in user.
     */
    @FXML
    void initialize() {
    	User temptuser = new User(SharedData.getUsername(),null,null,0.0,null);
    	user = (User) DatabaseManager.searchRecord(temptuser, "user");
    	firstName.setText(user.getName());
    	mobileNumber.setText(user.getMobileNum());
    	address.setText((user.getAddress().getHouseNumorName() + user.getAddress().getRoadName() +user.getAddress().getCity() + user.getAddress().getPostcode() ));

    }
}
