package cs230.application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;




public class ResourceCopyPageController
{
  @FXML
  private TableView tables;
  @FXML
  private TableColumn borrowType;
  @FXML
  private TableColumn userName;
  @FXML
  private TableColumn dateTime;
  @FXML
  private Button back;
  
  public ResourceCopyPageController() {}
  
  public void setBorrowType()
  {
    borrowType = borrowType;
  }
  

  private void getBorrowType(TableColumn borrowType) {}
  
  public void setUserName()
  {
    userName = userName;
  }
  

  private void getUserName(TableColumn userName) {}
  
  public void setDateTime()
  {
    dateTime = dateTime;
  }
  

  private void getDateTime(TableColumn dateTime) {}
  
  private void showHistory()
  {
    setBorrowType();
    setUserName();
    setDateTime();
    TableColumn borrowType = this.borrowType;
    TableColumn userName = this.userName;
    TableColumn dateTime = this.dateTime;
  }
  
  private void back() {}
}
