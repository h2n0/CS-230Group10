
/**
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
	
	@Override 
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		final User PERSON = (User) obj;
		if (this.name == PERSON.getname()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
}