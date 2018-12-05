package cs230.application;

import java.net.URL;
import java.util.ResourceBundle;

import cs230.system.Address;
import cs230.system.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * @author 959470 This is the controller for LibrarianAccountView.fxm, which let's the currently logged in Librarian see and individually change their account details.
 *
 */
public class LibrarianAccountViewController {
	//The currently logged in Librarian
	private static Librarian librarian ;
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
    private Label staffNum;

    @FXML
    private Label emplDate;

    @FXML
    private TextField firstNametxt;


    @FXML
    private TextField mobNumtxt;

    @FXML
    private TextField addresstxt;

    @FXML
    private TextField staffNumtxt;

    @FXML
    private Button editFirstName;

    @FXML
    private Button editMobNum;

    @FXML
    private Button editAddress;

    @FXML
    private Button editStaffNum;

    @FXML
    private Button saveFn;


    @FXML
    private Button saveMobNum;

    @FXML
    private Button saveAdr;

    @FXML
    private Button saveStaffNum;

    @FXML
    private Button cancell1;

    @FXML
    private Button cancell3;

    @FXML
    private Button cancell4;

    @FXML
    private Button cancell5;

    /**
     * Removes all the text fields and save & exit buttons , reverting the page to it's default state
     */
    @FXML
    void cancell(MouseEvent event) {
		editFirstName.setVisible(true);

		editAddress.setVisible(true);
		editMobNum.setVisible(true);
		editStaffNum.setVisible(true);
		saveFn.setVisible(false);

		saveAdr.setVisible(false);
		saveMobNum.setVisible(false);
		saveStaffNum.setVisible(false);
		cancell1.setVisible(false);

		cancell3.setVisible(false);
		cancell4.setVisible(false);
		cancell5.setVisible(false);
		firstNametxt.setVisible(false);
		mobNumtxt.setVisible(false);
		addresstxt.setVisible(false);
		staffNumtxt.setVisible(false);
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
		editStaffNum.setVisible(false);
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
		editStaffNum.setVisible(false);
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
		editStaffNum.setVisible(false);
		saveMobNum.setVisible(true);
		cancell3.setVisible(true);
    }
	

    /**
     * Shows the text field for staff number as well as the corresponding save and cancel buttons. Hides all the rest
     */
	@FXML
    void editFieldStaffNum(MouseEvent event) {
		staffNumtxt.setVisible(true);
		staffNumtxt.setText(staffNum.getText());
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		editStaffNum.setVisible(false);
		saveStaffNum.setVisible(true);
		cancell5.setVisible(true);
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
    			librarian.setAddress(address1);
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
    		librarian.setName(firstNametxt.getText());
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
			librarian.setMobileNum(mobNumtxt.getText());
			cancell(event);
    	} else {
    		emptyFieldLb.setVisible(true);
    	}
    }
	
    /**
     * Checks if the field is empty and shows the appropriate message, otherwise updates the staff number
     */
	@FXML
    void saveStaffNum(MouseEvent event) {
		if (!staffNum.getText().equals("")) {
			staffNum.setText(staffNumtxt.getText());
			librarian.setstaffNumber(Integer.parseInt(staffNumtxt.getText()));
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
    	firstName.setText(librarian.getName());
    	mobileNumber.setText(librarian.getMobileNum());
    	address.setText((librarian.getAddress().gethouseNumorName() + librarian.getAddress().getroadName() + librarian.getAddress().getcity() + librarian.getAddress().getpostcode() ));
    	staffNum.setText(librarian.getstaffNumber().toString());
    	emplDate.setText(librarian.getemploymentDate().toString());
    }
}
