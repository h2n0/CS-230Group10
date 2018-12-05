package cs230.application;

import java.io.File;

import cs230.system.Address;
import cs230.system.DatabaseManager;
import cs230.system.PassInfo;
import cs230.system.Resource;
import cs230.system.SharedData;
import cs230.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
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
	private TabPane tabs;

	@FXML
	private ImageView avatarImage;

	private Image currentImage;

	@FXML
	public void tabChange(ActionEvent e) {
		System.out.println("Changed tabs!");
	}
	
	
	private void initialiseAvatar(User u) {
		String avatarImageLocation = SharedData.getUser().getAvatarFilePath();

		String root = new File("").getAbsolutePath();

		if (avatarImageLocation == null) { // User dosen't have an image for some reason
			avatarImageLocation = root + "/Assets/default.png";
		} else {
			avatarImageLocation = avatarImageLocation.trim();
		}
		
		try {
			String URI = "" + new File(avatarImageLocation).toURI();
			this.currentImage = new Image(URI);
			this.avatarImage.setImage(this.currentImage);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillTab(Tab t) {
		ListView list = new ListView<Resource>();
		
		
		//DatabaseManager.getTable("resourcse");
		
		t.setContent(list);
	}

	@FXML
	public void initialize() {
		nameText.setText(SharedData.getUsername());

		Address ass = SharedData.getAddress();
		addressText.setText(ass.getHouseNumorName() + " " + ass.getRoadName() + ",\n" + ass.getCity() + ",\n"
				+ ass.getPostcode());

		balanceText.setText("£" + SharedData.getBalance());

		initialiseAvatar(PassInfo.getCurrentUser());
		
		
		Tab loansTab = new Tab("Loans");
		Tab requestsTab = new Tab("Requests");
		Tab overdueTab = new Tab("Overdue");
		
		//fillTab(loansTab);
		
		tabs.getTabs().addAll(loansTab, requestsTab, overdueTab);
		
	}
}
