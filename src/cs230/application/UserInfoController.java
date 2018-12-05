package cs230.application;

import cs230.system.Address;
import cs230.system.SharedData;
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
		nameText.setText(SharedData.getUsername());
		
		Address ass = SharedData.getAddress();
		addressText.setText(ass.gethouseNumorName() + ", " + ass.getroadName() + ", " + ass.getcity() + ", " + ass.getpostcode());
		
		balanceText.setText("£" + SharedData.getBalance());
	}
}
