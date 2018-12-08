import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LibrarianAccountViewController {

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

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
    private TextField lastNametxt;

    @FXML
    private TextField mobNumtxt;

    @FXML
    private TextField addresstxt;

    @FXML
    private TextField staffNumtxt;

    @FXML
    private Button editFirstName;

    @FXML
    private Button editLastName;

    @FXML
    private Button editMobNum;

    @FXML
    private Button editAddress;

    @FXML
    private Button editStaffNum;

    @FXML
    private Button saveFn;

    @FXML
    private Button saveLn;

    @FXML
    private Button saveMobNum;

    @FXML
    private Button saveAdr;

    @FXML
    private Button saveStaffNum;

    @FXML
    private Button cancell1;

    @FXML
    private Button cancell2;

    @FXML
    private Button cancell3;

    @FXML
    private Button cancell4;

    @FXML
    private Button cancell5;

    @FXML
    void cancell(MouseEvent event) {
		editFirstName.setVisible(true);
		editLastName.setVisible(true);
		editAddress.setVisible(true);
		editMobNum.setVisible(true);
		editStaffNum.setVisible(true);
		saveFn.setVisible(false);
		saveLn.setVisible(false);
		saveAdr.setVisible(false);
		saveMobNum.setVisible(false);
		saveStaffNum.setVisible(false);
		cancell1.setVisible(false);
		cancell2.setVisible(false);
		cancell3.setVisible(false);
		cancell4.setVisible(false);
		cancell5.setVisible(false);
		firstNametxt.setVisible(false);
		lastNametxt.setVisible(false);
		mobNumtxt.setVisible(false);
		addresstxt.setVisible(false);
		staffNumtxt.setVisible(false);
    }

    @FXML
    void editFieldAddress(MouseEvent event) {
		addresstxt.setVisible(true);
		addresstxt.setText(address.getText());
		editLastName.setVisible(false);
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		editStaffNum.setVisible(false);
		saveAdr.setVisible(true);
		cancell4.setVisible(true);
    }

    @FXML
    void editFieldFname(MouseEvent event) {
		firstNametxt.setVisible(true);
		firstNametxt.setText(firstName.getText());
		editLastName.setVisible(false);
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		editStaffNum.setVisible(false);
		saveFn.setVisible(true);
		cancell1.setVisible(true);
    }

    @FXML
    void editFieldLastName(MouseEvent event) {
		lastNametxt.setVisible(true);
		lastNametxt.setText(lastName.getText());
		editLastName.setVisible(false);
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		editStaffNum.setVisible(false);
		saveLn.setVisible(true);
		cancell2.setVisible(true);
    }

    @FXML
    void editFieldMbNum(MouseEvent event) {
		mobNumtxt.setVisible(true);
		mobNumtxt.setText(mobileNumber.getText());
		editLastName.setVisible(false);
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		editStaffNum.setVisible(false);
		saveMobNum.setVisible(true);
		cancell3.setVisible(true);
    }
	
	
	@FXML
    void editFieldStaffNum(MouseEvent event) {
		staffNumtxt.setVisible(true);
		staffNumtxt.setText(staffNum.getText());
		editLastName.setVisible(false);
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		editStaffNum.setVisible(false);
		saveStaffNum.setVisible(true);
		cancell5.setVisible(true);
    }

    @FXML
    void saveAdress(MouseEvent event) {
		address.setText(addresstxt.getText());
		cancell(event);
    }

    @FXML
    void saveFirstName(MouseEvent event) {
		firstName.setText(firstNametxt.getText());
		cancell(event);
    }

    @FXML
    void saveLastName(MouseEvent event) {
		lastName.setText(lastNametxt.getText());
		cancell(event);
    }

    @FXML
    void saveMobNum(MouseEvent event) {
		mobileNumber.setText(mobNumtxt.getText());
		cancell(event);
    }
	
	@FXML
    void saveStaffNum(MouseEvent event) {
		staffNum.setText(staffNumtxt.getText());
		cancell(event);
    }

    @FXML
    void initialize() {
    }
}
