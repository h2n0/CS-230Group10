package cs230.system;
/**
 * Stores info to be passed between windows
 * @author 963257
 * @version 1.1
 *
 */
public class PassInfo {
	//user to be passed from Fine page to EditFine page
	private static User editFineUser;
	//copy to be passed to the copyHistory page
	private static Copy copyHistCopy;
	//Resource to be passed from the ResourceList page to the resourceDetail page
	private static Resource resourceDetails;
	//User currently logged in
	private static User currentUser;
	

	/**
	 * sets the user currently logged in
	 * @param u user currently logged in
	 */
	public static void setCurrentUser(User u){
		currentUser = u;
	}
	/**
	 * gets the user currently logged in
	 * @return the user currently logged in
	 */
	public static User getCurrentUser(){
		return(currentUser);
	}
	
	/**
	 * sets the user selected on the fine page
	 * @param u user from the Fine page
	 */
	public static void setResourceDetails(Resource r){
		resourceDetails = r;
	}
	/**
	 * gets the user selected on the fine page
	 * @return the user to be loaded into the EditFine page
	 */
	public static Resource getResourceDetails(){
		return(resourceDetails);
	}
	
	/**
	 * sets the user selected on the fine page
	 * @param u user from the Fine page
	 */
	public static void setcopyHistCopy(Copy c){
		copyHistCopy = c;
	}
	/**
	 * gets the user selected on the fine page
	 * @return the user to be loaded into the EditFine page
	 */
	public static Copy getcopyHistCopy(){
		return(copyHistCopy);
	}
	
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