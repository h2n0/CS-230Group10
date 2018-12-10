package cs230.application;

import cs230.system.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/** The controller for the account view
 * 
 * @author 959470
 *
 */

public class AccountViewController {

    // First name label
    @FXML
    private Label firstName;

    //Last name label
    @FXML
    private Label lastName;

    //Mobilenumber label
    @FXML
    private Label mobileNumber;

    //First address line label
    @FXML
    private Label addressLine1;
    
    //Second address line label
    @FXML
    private Label addressLine2;
    
    //City address label
    @FXML
    private Label addressCity;
    
    //Postcode address
    @FXML
    private Label addressPostcode;
    
    //User to be set
    private User user;

    //Initialises the gui
    @FXML
    public void initialize() {
            firstName.setText(user.getfirstName());
            lastName.setText(user.getlastName());
            mobileNumber.setText(user.getphoneNum());
            addressLine1.setText(user.getAddress()
                            .getHouseNumberName());
            addressLine2.setText(user.getAddress()
                            .getRoadName());
            addressCity.setText(user.getAddress()
                            .getHouseNumberName());
            addressPostcode.setText(user.getAddress()
                            .getHouseNumberName());
    }
    
    /**
     * Sets the user to be shown
     * @param user
     */
    public void setUser(User user) {
            this.user = user;
    }
}
