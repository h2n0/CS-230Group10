
public class User {

	// User properties
	private String userID;
	private String name;
	private String address;
	private int balance;
	private String avatarPath;
	
	/**
	 * Recreate a user based on information in the database
	 * @param id - The User's ID in the data base
	 */
	public User(int id) {
		// Unused at the moment, waiting for database connections
	}
	
	/**
	 * Create a brand new user from some basic info
	 * @param name - The users name
	 * @param address - The address at which they live at
	 */
	public User(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	/**
	 * Allows the user to draw a custom avatar for their profile
	 */
	public void drawAvatar() {
		
	}
	
	/**
	 * Allows a user to select from pre-made or custom avatars for their profile 
	 */
	public void pickAvatar() {
		
	}
	
	/**
	 * Check to see if the provided address is a valid one
	 * @param newAddress - The address to check
	 * @return boolean - True if address is valid and False otherwise
	 */
	private boolean validateAddress(String newAddress) {
		return false;
	}
}
