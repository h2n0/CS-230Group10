package cs230.system;

import java.io.Serializable;

/**
 * User stores the info for each user
 * @author 963257
 * @version 1.0
 */
public class User implements Serializable {
	//Default serial ID
	private static final long serialVersionUID = 1L;
	//Uniquely identify each user
	private Integer userID;
	//The user's name
	private String name;
	//The user's address
	private Address address;
	//The user's balance 
	private Double balance;
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
	public User(Integer userID, String name, Address address,
		    Double balance, String avatarFilePath) {
		this.userID = userID;
		this.name = name;
		this.address = address;
		this.balance = balance;
		this.avatarFilePath = avatarFilePath;
	}

	/**
	 * Constructor when User exists in database 
	 * @param userID The identifier for the User
	 */
	public User(Integer userID) {
		//Object[] data = getDataFromDatabase ();
		//User(ID,data)
	}
	
	/**
	 * Gets the user ID
	 * @return The user ID
	 */
	public Integer getUserID() {
		return userID;
	}

	/**
	 * Sets the user's name
	 * @param name The new name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the user's name
	 * @return The users name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the user's address
	 * @param address The user's new address
	 */
	public void setAddress(Address address) {
		if (address.validAddress()) {
			this.address = address;
		}
		else {
			throw new IllegalArgumentException ("Address not valid"); 
		}
	}
	/**
	 * Gets the user's address
	 * @return The user's address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the user's balance
	 * @param balance The user's new balance
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	/**
	 * Gets the user's balance
	 * @return The user's balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * Sets the file path for the users avatar
	 * @param avatarFilePath The new file path to the user's avatar
	 */
	public void setAvatarFilePath(String avatarFilePath) {
		this.avatarFilePath = avatarFilePath;
	}

	/**
	 * Gets the file path for the users avatar
	 * @return The file path for the users avatar
	 */
	public String getAvatarFilePath() {
		return avatarFilePath;
	}

	/**
	 * Equals method defines two user objects as equal if the name is the
	 * same
	 * @param obj Object to compare to
	 * @return True if they are equal, False otherwise
	 */
	@Override 
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		User PERSON = (User) obj;

		if (this.name.equalsIgnoreCase(PERSON.name)) {
			return true;
		}

		if (this.userID == PERSON.userID) {
			return true;
		}

		return false;
	}

	public String toString() {
		return name;
	}
}