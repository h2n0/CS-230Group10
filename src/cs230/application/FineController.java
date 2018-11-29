package path.to;

public class MyController implements Initializable {

    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> FineID;
    @FXML private TableColumn<User, String> UserName;
    @FXML private TableColumn<User, String> Amount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserId.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        UserName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        Active.setCellValueFactory(new PropertyValueFactory<User, String>("active"));

        tableView.getItems().setAll(parseUserList());
    }
    private List<User> parseUserList(){
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list   
    }
}