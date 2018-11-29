package path.to;

public class MyController implements Initializable {

    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> FineID;
    @FXML private TableColumn<User, String> User;
    @FXML private TableColumn<User, String> Amount;
	@FXML private TableColumn<User, String> NewAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FineID.setCellValueFactory(new PropertyValueFactory<User, String>("FineID"));
        User.setCellValueFactory(new PropertyValueFactory<User, String>("User"));
        Amount.setCellValueFactory(new PropertyValueFactory<User, String>("Amount"));
		NewAmount.setCellValueFactory(new PropertyValueFactory<User, String>("NewAmount"));
		
        tableView.getItems().setAll(parseList());
    }
    private List<User> parseList(){
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list   
    }
}