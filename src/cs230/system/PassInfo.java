package cs230.system;

public class PassInfo {
	private static User editFineUser;
	
	public static void setEditFineUser(User u){
		editFineUser = u;
	}
	public static User getEditFineUser(){
		return(editFineUser);
	}
	
	public static void resetAll() {
		setEditFineUser(null);
	}
	
	
}