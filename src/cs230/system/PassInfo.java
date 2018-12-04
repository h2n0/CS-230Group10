package cs230.system;
/**
 * Stores info to be passed between windows
 * @author 963257
 * @version 1.0
 *
 */
public class PassInfo {
	//user to be passed from Fine page to EditFine page
	private static User editFineUser;
	
	/**
	 * sets the user selected on the fine page
	 * @param u user from the Fine page
	 */
	public static void setEditFineUser(User u){
		editFineUser = u;
	}
	/**
	 * gets the user selected on the fine page
	 * @return the user to be loaded into the EditFine page
	 */
	public static User getEditFineUser(){
		return(editFineUser);
	}
	
	/**
	 * resets all attributes to null
	 */
	public static void resetAll() {
		setEditFineUser(null);
	}
	
	
}