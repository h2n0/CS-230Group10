package cs230.application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;




public class ResourceListPageController
{
  @FXML
  private Button addSource;
  @FXML
  private TableView tables;
  @FXML
  private TableColumn resourceID;
  @FXML
  private TableColumn title;
  @FXML
  private TableColumn year;
  
  public ResourceListPageController() {}
  
  public void setResourceID()
  {
    resourceID = resourceID;
  }
  

  private void getResourceID(TableColumn ResourceID) {}
  
  public void setTitle()
  {
    title = title;
  }
  

  private void getTitle(TableColumn title) {}
  
  public void setYear()
  {
    year = year;
  }
  

  private void getYear(TableColumn year) {}
  
  private void showHistory()
  {
    setResourceID();
    setTitle();
    setYear();
    TableColumn resourceID = this.resourceID;
    TableColumn title = this.title;
    TableColumn year = this.year;
  }
  
  private void addSource() {}
}
