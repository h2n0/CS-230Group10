package cs230.application;

import java.io.File;

//import com.sun.corba.se.impl.transport.SharedCDRContactInfoImpl;

import cs230.system.Address;
import cs230.system.DatabaseManager;
import cs230.system.SharedData;
import cs230.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * 
 * @author 
 * @version 1.0
 */
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
	
	
	private void initialiseAvatar() {
	    User tempUser = new User(SharedData.getUsername(),null,null,
                    null, null, null, null);
	    User curUser = (User) DatabaseManager.searchExact(tempUser, "user");
	    String avatarImageLocation = curUser.getAvatarFilePath();

		String root = new File("").getAbsolutePath();

		if (avatarImageLocation == null || ! new File(avatarImageLocation).exists()) { // User dosen't have an image for some reason
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
		TableView list = new TableView<>();
		
		TableColumn resourceName = new TableColumn<>("Name");
		resourceName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		TableColumn resourceIndex = new TableColumn<>("Position");
		resourceIndex.setCellValueFactory(new PropertyValueFactory<>("Position"));
		
		list.getColumns().addAll(resourceName, resourceIndex);
		
		
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
		
		
		Tab loansTab = new Tab("Loans");
		Tab requestsTab = new Tab("Requests");
		Tab overdueTab = new Tab("Overdue");
		
		initialiseAvatar();
		
		fillTab(loansTab);
		
		tabs.getTabs().addAll(loansTab, requestsTab, overdueTab);
		
	}
}
