package cs230.application;


import cs230.system.Address;
import cs230.system.DatabaseManager;
import cs230.system.Librarian;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


/** Controls the librarian view controller
 * 
 * @author 959470
 *
 */

public class LibrarianAccountViewController {
	
	private Librarian librarian ;
	@FXML
	private Label emptyFieldLb;

	@FXML
	private Label incorrectadressLb;
	
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
    private TextField employDateTxt;


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
    private Button editEmployDate;
    
    @FXML
    private Button editLastName;

    @FXML
    private Button saveFn;


    @FXML
    private Button saveMobNum;

    @FXML
    private Button saveAdr;

    @FXML
    private Button saveStaffNum;
    
    @FXML
    private Button saveEmployDate;

    @FXML
    private Button cancel1;

    @FXML
    private Button cancel3;

    @FXML
    private Button cancel4;

    @FXML
    private Button cancel5;
    
    @FXML
    private Button cancel6;
    
    @FXML
    public void initialize() {
        firstName.setText(librarian.getfirstName());
        lastName.setText(librarian.getlastName());
        mobileNumber.setText(librarian.getphoneNum());
        address.setText((librarian.getAddress().getHouseNumberName()
                        + librarian.getAddress().getRoadName()
                        + librarian.getAddress().getCity()
                        + librarian.getAddress().getPostcode() ));
        staffNum.setText(librarian.getstaffNumber().toString());
        emplDate.setText(librarian.getemploymentDate().toString());
    }
    
    @FXML
    private void cancel1(MouseEvent event) {
            cancel(event);
    }
    
    @FXML
    private void cancel2(MouseEvent event) {
            cancel(event);
    }
    
    @FXML
    private void cancel3(MouseEvent event) {
            cancel(event);
    }
    
    @FXML
    private void cancel4(MouseEvent event) {
            cancel(event);
    }
    
    @FXML
    private void cancel5(MouseEvent event) {
            cancel(event);
    }
    
    @FXML
    private void cancel6(MouseEvent event) {
            cancel(event);
    }

    public void setLibrarian(Librarian librarian)
    {
            this.librarian = librarian;
    }
    
    @FXML
    private void cancel(MouseEvent event) {
		editFirstName.setVisible(true);
		editLastName.setVisible(true);
		editAddress.setVisible(true);
		editMobNum.setVisible(true);
		editStaffNum.setVisible(true);
		saveFn.setVisible(false);

		saveAdr.setVisible(false);
		saveMobNum.setVisible(false);
		saveStaffNum.setVisible(false);
		cancel1.setVisible(false);

		cancel3.setVisible(false);
		cancel4.setVisible(false);
		cancel5.setVisible(false);
		cancel6.setVisible(false);
		firstNametxt.setVisible(false);
		mobNumtxt.setVisible(false);
		addresstxt.setVisible(false);
		staffNumtxt.setVisible(false);
		emptyFieldLb.setVisible(false);
		incorrectadressLb.setVisible(false);
    }

    @FXML
    private void editFieldAddress(MouseEvent event) {
		addresstxt.setVisible(true);
		addresstxt.setText(address.getText());
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		editLastName.setVisible(false);
		editStaffNum.setVisible(false);
		saveAdr.setVisible(true);
		cancel4.setVisible(true);
    }

    @FXML
    private void editFieldFname(MouseEvent event) {
		firstNametxt.setVisible(true);
		firstNametxt.setText(firstName.getText());
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		editStaffNum.setVisible(false);
		saveFn.setVisible(true);
		cancel1.setVisible(true);
    }
    
    @FXML
    private void editLastname(MouseEvent event) {
        firstNametxt.setVisible(true);
        firstNametxt.setText(firstName.getText());
        editAddress.setVisible(false);
        editMobNum.setVisible(false);
        editFirstName.setVisible(false);
        editStaffNum.setVisible(false);
        saveFn.setVisible(true);
        cancel1.setVisible(true);
    }


    @FXML
    private void editFieldMbNum(MouseEvent event) {
		mobNumtxt.setVisible(true);
		mobNumtxt.setText(mobileNumber.getText());
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		editStaffNum.setVisible(false);
		saveMobNum.setVisible(true);
		cancel3.setVisible(true);
    }
	
	
	@FXML
    private void editFieldStaffNum(MouseEvent event) {
		staffNumtxt.setVisible(true);
		staffNumtxt.setText(staffNum.getText());
		editAddress.setVisible(false);
		editMobNum.setVisible(false);
		editFirstName.setVisible(false);
		editStaffNum.setVisible(false);
		editEmployDate.setVisible(false);
		saveStaffNum.setVisible(true);
		cancel5.setVisible(true);
    }
	
   @FXML
    private void editEmployDate(MouseEvent event) {
	        staffNumtxt.setVisible(false);
	        staffNumtxt.setText(staffNum.getText());
	        editAddress.setVisible(false);
	        editMobNum.setVisible(false);
	        editFirstName.setVisible(false);
	        editStaffNum.setVisible(false);
	        saveEmployDate.setVisible(true);
	        cancel6.setVisible(true);
	    }

    @FXML
    private void saveAdress(MouseEvent event) {
    	if (!addresstxt.getText().equals("")) {
    		try {
    			String[] adComp = addresstxt.getText().split(" ");
    			Address address1 = new Address(adComp[0],adComp[1],adComp[2],adComp[3]);
    			Librarian newLibrarian = new Librarian(librarian.getName(),
                                librarian.getfirstName(), librarian.getlastName(),
                                librarian.getphoneNum(), address1,
                                librarian.getBalance(), librarian.getAvatarFilePath(),
                                librarian.getemploymentDate()
                                , librarian.getstaffNumber());
                addLibrarian(newLibrarian);
    			address.setText(addresstxt.getText());
    			cancel(event);
    		}
    		catch (Exception e) {
    			incorrectadressLb.setVisible(true);
    		}
    	}else {
    		emptyFieldLb.setVisible(true);
    	}
    }

    @FXML
    private void saveFirstName(MouseEvent event) {
    	if (!firstNametxt.getText().equals("")) {
    		firstName.setText(firstNametxt.getText());
            Librarian newLibrarian = new Librarian(librarian.getName(),
                            firstNametxt.getText(), librarian.getlastName(),
                            librarian.getphoneNum(), librarian.getAddress(),
                            librarian.getBalance(), librarian.getAvatarFilePath(),
                            librarian.getemploymentDate()
                            , librarian.getstaffNumber());
            addLibrarian(newLibrarian);
    		cancel(event);
    	} else {
    		emptyFieldLb.setVisible(true);
    	}
    }


    @FXML
    private void saveMobNum(MouseEvent event) {
    	if (!mobNumtxt.getText().equals("")) {
			mobileNumber.setText(mobNumtxt.getText());
            Librarian newLibrarian = new Librarian(librarian.getName(),
                            librarian.getfirstName(), librarian.getlastName(),
                            mobNumtxt.getText(), librarian.getAddress(),
                            librarian.getBalance(), librarian.getAvatarFilePath(),
                            librarian.getemploymentDate()
                            , librarian.getstaffNumber());
            addLibrarian(newLibrarian);
			cancel(event);
    	} else {
    		emptyFieldLb.setVisible(true);
    	}
    }
	
	@FXML
    private void saveStaffNum(MouseEvent event) {
		if (!staffNum.getText().equals("")) {
			staffNum.setText(staffNumtxt.getText());
			Librarian newLibrarian = new Librarian(librarian.getName(),
	                        librarian.getfirstName(), librarian.getlastName(),
	                        librarian.getphoneNum(), librarian.getAddress(),
	                        librarian.getBalance(), librarian.getAvatarFilePath(),
	                        librarian.getemploymentDate()
	                        , staffNumtxt.getText());
			addLibrarian(newLibrarian);
			cancel(event);
		} else {
    		emptyFieldLb.setVisible(true);
    	}
    }
	
	@FXML
    private void saveEmployDate(MouseEvent event) {
	        String employDate = employDateTxt.getText();
	        LocalDate date = LocalDate.now();
	        boolean canContinue = true; 
	        if(!employDate.equals("")) {
	                try {
	                        date = LocalDate.parse(employDate); 
	                }
	                catch(DateTimeParseException e) {
	                        canContinue = false;
	                        emptyFieldLb.setVisible(true);
	                        
	                }
	                if(canContinue) {
	                        Librarian newLibrarian = new Librarian(librarian.getName(),
	                                        librarian.getfirstName(), librarian.getlastName(),
	                                        librarian.getphoneNum(), librarian.getAddress(),
	                                        librarian.getBalance(), librarian.getAvatarFilePath(),
	                                        date, librarian.getstaffNumber());
	                        addLibrarian(newLibrarian);
	                        cancel(event);
	                }
	                
	        }
	}
	
	@FXML
    private void saveLastName(MouseEvent event) {
	        String lastName = lastNametxt.getText();
	        if(!lastName.equals(""))
	        {
	                Librarian newLibrarian = new Librarian(librarian.getName(),
                                    librarian.getfirstName(), lastName,
                                    librarian.getphoneNum(), librarian.getAddress(),
                                    librarian.getBalance(), librarian.getAvatarFilePath(),
                                    librarian.getemploymentDate(), librarian.getstaffNumber());
                    addLibrarian(newLibrarian);
	        } else {
	                emptyFieldLb.setVisible(true);
            }
	        
	}
	
	private void addLibrarian(Librarian newLibrarian)
	{
	        DatabaseManager.editRecord(librarian,
	                        newLibrarian, "librarian");
	}
}
