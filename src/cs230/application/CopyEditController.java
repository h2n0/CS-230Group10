package cs230.application;

import java.util.ArrayList;

import cs230.system.Copy;
import cs230.system.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**Controls the copy edit/save page
 * 
 * @author 901306
 * @version 1.0
 */
public class CopyEditController {

        //The copy to edit
        private String copyID = "";
        //The resource to add the copy to
        private String resourceID;
        //The old Duration
        private int duration;
        //The status of the copy
        private Copy.Status status;
        //the edit label
        @FXML private Label editLabel;
        //the new label
        @FXML private Label newLabel;
        //the incorrect label
        @FXML private Label incorrectLabel;
        //the text Field to capture the duration
        @FXML private TextField durationTextBox;
        // the save button
        @FXML private Button saveButton;
        //the cancel button
        @FXML private Button cancelButton;
        //boolean to check if new or not
        private boolean isNew;
        //true if its a laptop
        private boolean isLaptop;
        //true if its a book
        private boolean isBook;
        //true if its a DVD
        private boolean isDvd;
        
        /**
         * 
         */
        public CopyEditController() {
                
        }
        
        /**
         * Sets the resource to be a laptop
         */
        public void setIsLaptop() {
                isLaptop = true;
        }
        
        /**
         * Sets the resource to be a Book
         */
        public void setIsBook() {
                isBook = true;
        }
        
        /**
         * Sets the resource to be a Dvd
         */
        public void setIsDvd() {
                isDvd = true;
        }
        
        /**
         * Sets the copyID to be shown
         * @param copyID The copy's ID
         */
        public void setCopyID(String copyID) {
                this.copyID = copyID;
        }
        
        /**
         * Sets the original duration
         * @param duration The old duration
         */
        public void setDuration(int duration) {
                this.duration = duration;
        }
        
        /**
         * Sets the resourceID
         * @param resourceID The resource's ID
         */
        public void setResourceID(String resourceID) {
                this.resourceID = resourceID;
        }
        
        /**
         * Sets the status
         * @param status The copy's status
         */
        public void setCopyStatus(Copy.Status status) {
                this.resourceID = resourceID;
        }
        
        /**
         * intialises the page
         */
        @FXML
        public void initialize() {
                if(copyID.equals("")) {
                        editLabel.setVisible(false);
                        incorrectLabel.setVisible(false);
                        isNew = true;
                } else {
                        newLabel.setVisible(false);
                        incorrectLabel.setVisible(false);
                        isNew = false;
                        durationTextBox.setText(Integer.toString(duration));
                }
                
        }
        
        /**
         * saves the copy to the database
         * @param e the event to trigger the code
         */
        public void handleSave(ActionEvent e) {
        		//get the duration
                int enteredDuration = Integer.parseInt(durationTextBox.getText());
                //if its a valid input
                if(enteredDuration == 1 || enteredDuration == 7 
                                || enteredDuration == 14 || enteredDuration == 28) {
                        
                        if(isNew){
                                ArrayList<Copy> copyList= (ArrayList<Copy>)DatabaseManager
                                                .getTable("copy");
                                int maxId = 0;
                                for(Copy copy : copyList) {
                                        if(Integer.parseInt(copy.getID()) > maxId) {
                                                maxId = 0;
                                        }
                                }
                                if(maxId == 0) {
                                        maxId = 1;
                                }
                                copyID = Integer.toString( maxId + 1);
                                String type = "";
                                if(isLaptop) {
                                        type = "Laptop";
                                } else if (isBook) {
                                        type = "book";
                                } else {
                                        type = "Dvd";
                                }
                          
                                Copy toAdd = new Copy(copyID ,resourceID,
                                        Copy.Status.AVAILABLE, type
                                                , enteredDuration);
                                DatabaseManager.saveRecord(toAdd, "copy");
                                Stage currentStage = (Stage)incorrectLabel.getScene().getWindow();
                                currentStage.close();
                        } else {
                                String type = "";
                                if(isLaptop) {
                                        type = "Laptop";
                                } else if (isBook) {
                                        type = "book";
                                } else {
                                        type = "Dvd";
                                }
                                Copy oldCopy = new Copy(copyID, resourceID,
                                        Copy.Status.AVAILABLE, type
                                                , duration);
                                if(duration != enteredDuration) {
                                        Copy newCopy = new Copy(copyID,
                                                resourceID,
                                                Copy.Status.AVAILABLE, type
                                                , enteredDuration);
                                        DatabaseManager
                                        .editRecord(oldCopy, newCopy, "copy");
                                } else {
                                        Stage currentStage = (Stage)incorrectLabel
                                                        .getScene().getWindow();
                                        currentStage.close();
                                }
                              
                              
                        }
                } else {
                        incorrectLabel.setVisible(true);
                }
        }
        /**
         * cancels the window
         * @param e
         */
        @FXML
        public void handleCancel(ActionEvent e) {
                Stage currentStage = (Stage)incorrectLabel.getScene().getWindow();
                currentStage.close();
        }
}
