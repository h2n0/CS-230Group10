package cs230.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class UserInfoController {

	@FXML
	private Text nameText;
	
	@FXML
	private Text addressText;
	
	@FXML
	private Text balanceText;
	

	@FXML
	private ImageView avatarImage;
	
	
	@FXML
	public void tabChange(ActionEvent e) {
		
	}
	
	@FXML
	public void initialize() {
		//Here we can populate all of the info
	}
}
