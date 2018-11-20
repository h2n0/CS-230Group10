
public class User {

	private String userID;
	private String name;
	private String address;
	private int balance;
	private String avatarPath;
	
	public User(int id) {
		// Unused at the moment, waiting for database conections
	}
	
	public User(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	
	public void setAvatar(String location) {
		
	}
	
	public void pickAvatar() {
		
	}
	
	private boolean validateAddress(String newAddress) {
		return false;
	}
}
