package cs230.application;

import java.util.ArrayList;

import cs230.system.Copy;
import cs230.system.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**Controls the copy edit/save page
 * 
 * @author 901306
 *
 */
public class CopyEditController {

        //The copy to edit
        private String copyID = "";
        
        //The resource to add the copy to
        private String resourceID;
        
        //The old Duration
        private int duration;
        
        @FXML
        private Label editLabel;
        
        @FXML
        private Label newLabel;
        
        @FXML
        private Label incorrectlabel;
        
        @FXML
        private TextField durationTextBox;
        
        @FXML
        private Button saveButton;
        
        @FXML
        private Button cancelButton;
        
        private boolean isNew;
        
        public CopyEditController() {
                
        }
        
        /**
         * Sets the copyID to be shown
         */
        public void setCopyID(String copyID) {
                this.copyID = copyID;
        }
        
        /**
         * Sets the resourceID to be shown
         */
        public void setResourceID(String resourceID) {
                this.resourceID = resourceID;
        }
        
        @FXML
        public void initialize() {
                if(copyID.equals("")) {
                        editLabel.setVisible(false);
                        incorrectlabel.setVisible(false);
                        isNew = true;
                        this.copyID = getCopyId();
                } else {
                        newLabel.setVisible(false);
                        incorrectlabel.setVisible(false);
                        isNew = false;
                }
                
        }
        
        public void handleSave(ActionEvent e) {
                if(isNew){
                        
                } else {
                      ArrayList<Copy> copyList= (ArrayList<Copy>)DatabaseManager.getTable("copy");
                      int maxId = 0;
                      for(Copy copy : copyList) {
                              if(Integer.parseInt(copy.getID()) > maxId) {
                                      maxId = 0;
                              }
                      }
                      if(maxId == 0) {
                              maxId = 1;
                      }
                      
                }
        }
}
