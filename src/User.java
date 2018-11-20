import java.util.regex.Pattern;


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
	private String address;
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
	public User(Integer userID, String name, String address, Float balance, String avatarFilePath) {
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
	public void setaddress(String houseNumorName, String roadName, String city, String postcode) {
		if (validAddress(houseNumorName, roadName, city, postcode)) {
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
	public String getaddress() {
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
	
	//https://en.wikipedia.org/wiki/Postcodes_in_the_United_Kingdom
	private Boolean validPostcode(String postcode) {
		//1/2 uppercase then 1/2 digits then space then 1 digit then 2 uppercase letters
		String validpostcode = "(\\p{Upper}){1,2}?\\d{1,2}? \\d\\\\p{Upper}){2}?";
		Boolean match = Pattern.matches(validpostcode, postcode);
		return match;
	}
	
	// house number/house name, road name, City, Postcode 
	private Boolean validAddress(String houseNumorName, String roadName, String city, String postcode) {
		//a lower or upper case letter or a space any amount of times
		String letterOrSpace = "([a-zA-Z[\\p{Blank}]])*";
		
		//letterOrSpace or a number any amount of times
		Boolean validHouseNumorName = Pattern.matches(letterOrSpace, houseNumorName) || Pattern.matches("\\d*", houseNumorName);
		
		//letterOrSpace
		Boolean validRoadName = Pattern.matches(letterOrSpace, roadName);
		
		//letterOrSpace
		Boolean validCity = Pattern.matches(letterOrSpace, city);
		
		//only valid if all parts are valid
		Boolean validAddress = validHouseNumorName && validRoadName && validCity && validPostcode(postcode);
		
		return validAddress;
	}
}