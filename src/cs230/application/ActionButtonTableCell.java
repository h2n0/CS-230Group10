package cs230.application;

import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Generic button in a tableview that can be assigned text and a function
 * @author 963257
 * @version 1.1
 */
public class ActionButtonTableCell<S> extends TableCell<S, Button> {
	//action button held in the cell
    private final Button actionButton;

    /**
     * constructor 
     * @param label the text the button displays
     * @param function the function that gets called when the button is pressed
     */
    public ActionButtonTableCell(String label, Function< S, S> function) {
    	//create a new button
        this.actionButton = new Button(label);
        
        //set the onAction to the function passed in
        this.actionButton.setOnAction((ActionEvent e) -> {
            function.apply(getCurrentItem());
        });
        
        //set the button to fill the cell
        this.actionButton.setMaxWidth(Double.MAX_VALUE);
    }

    /**
     * get the item displayed in the same row as the cell that contains the button
     * @return the Object displayed in that row
     */
    public S getCurrentItem() {
    	//return from the table, the items, at the index of the button (return class instance used to populate that row)
        return (S) getTableView().getItems().get(getIndex());
    }

    /**
     * 
     * @param label the text the button displays 
     * @param function the function that gets called when the button is pressed
     * @return the object returned in the function called when the button is pressed
     */
    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn(String label, Function< S, S> function) {
        return param -> new ActionButtonTableCell<>(label, function);
    }

    /**
     * sets the cell to null if the row is empty, else display the action button
     * @param item the button in the cell
     * @param empty true if row is populated other than button, false if row is empty
     */
    @Override
    public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {                
            setGraphic(actionButton);
        }
    }
}