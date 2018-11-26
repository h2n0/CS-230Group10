
/**
<<<<<<< HEAD
 * The class handles all of the user functions and responsibilities
 * @author Elliot Lee-Cerrino (964552)
 *
 */
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
	 * 
	 * @param newAddress - The address to check
	 * @return boolean - True if address is valid and False otherwise
	 */
	private boolean validateAddress(String newAddress) {
		return false;
	}
}
=======
 * User stores the info for each user
 * @author 963257
 * @version 1.0
 */

public class User {
	//Uniquely identify each user
	private Integer userID;
	//The user's name
	private String name;
	//The user's address
	private Address address;
	//The user's balance 
	private Float balance;
	//The filepath to the user's avatar
	private String avatarFilePath;

	/**
	 * Constructor
	 * @param userID the identifier for the user
	 * @param name the name of the user
	 * @param address the address of the user
	 * @param balance how much money the user owes the library
	 * @param avatarFilePath the file path to the user's avatar
	 */
	public User(Integer userID, String name, Address address, Float balance, String avatarFilePath) {
		this.userID = userID;
		this.name = name;
		this.address = address;
		this.balance = balance;
		this.avatarFilePath = avatarFilePath;
	}

	/**
	 * Constructor when User exists in database 
	 * @param userID the identifier for the User
	 */
	public User(Integer userID) {
		//Object[] data = getDataFromDatabase ();
		//User(ID,data)
	}
	
	/**
	 * gets the user ID
	 * @return the user ID
	 */
	public Integer getuserID() {
		return userID;
	}

	/**
	 * sets the user's name
	 * @param name the new name of the user
	 */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * gets the user's name
	 * @return the users name
	 */
	public String getname() {
		return name;
	}

	/**
	 * sets the user's address
	 * @param address the user's address
	 */
	public void setaddress(Address address) {
		if (address.validAddress()) {
			this.address = address;
		}
		else {
			throw new IllegalArgumentException ("Address not valid"); 
		}
	}
	/**
	 * gets the user's address
	 * @return the user's address
	 */
	public Address getaddress() {
		return address;
	}

	/**
	 * sets the user's balance
	 * @param balance the user's balance
	 */
	public void setbalance(Float balance) {
		this.balance = balance;
	}
	/**
	 * gets the user's balance
	 * @return the user's balance
	 */
	public Float getbalance() {
		return balance;
	}

	/**
	 * sets the file path for the users avatar
	 * @param avatarFilePath the file path to the user's avatar
	 */
	public void setavatarFilePath(String avatarFilePath) {
		this.avatarFilePath = avatarFilePath;
	}
	/**
	 * gets the file path for the users avatar
	 * @return the file path for the users avatar
	 */
	public String getavatarFilePath() {
		return avatarFilePath;
	}
	
}
>>>>>>> de9a5512ad6d063a9537fbdd27769443317b452c
