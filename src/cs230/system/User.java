package cs230.system;

import java.io.Serializable;

/**
 * User stores the info for each user
 * @author 963257
 * @version 2.0
 */
public class User implements Serializable {
	//Default serial ID
	private static final long serialVersionUID = 1L;
	//The user's name
	private String name;
	//The user's address
	private Address address;
	//The user's balance 
	private Double balance;
	//The user's mobile number
	private String mobileNum;
	//The filepath to the user's avatar
	private String avatarFilePath;
		
	/**
	 * Constructor for a new user
	 * @param userID the identifier for the user
	 * @param name the name of the user
	 * @param address the address of the user
	 * @param balance how much money the user owes the library
	 * @param avatarFilePath the file path to the user's avatar
	 */
	public User(String name, Address address, String mobileNum, Double balance, String avatarFilePath) {
		this.name = name;
		this.address = address;
		this.mobileNum = mobileNum;
		this.balance = balance;
		this.avatarFilePath = avatarFilePath;
	}
	
	/**
	 * Sets the user's name
	 * @param name The new name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the user's mobie number
	 * @param name The new mobile number of the user
	 */
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	/**
	 * Gets the user's name
	 * @return The users name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the user's mobileNumber
	 * @return The users MobileNumber
	 */
	public String getMobileNum() {
		return mobileNum;
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

		// If they are an exact match return true
		if (this.name.equalsIgnoreCase(PERSON.name)) {
			return true;
		}

		return false;
	}

	/**
	 * Returns the user
	 * @return
	 */
	public String toString() {
		return this.name;
	}
}
