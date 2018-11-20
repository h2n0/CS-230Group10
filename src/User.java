//Imports go here

/**
 * Class Desc
 * @author 
 * @version 
 */

public class User {
	//CommentHere
	private String userID;
	//CommentHere
	private String name;
	//CommentHere
	private String address;
	//CommentHere
	private Float balance;
	//CommentHere
	private String avatarFilePath;

	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public User(String userID, String name, String address, Float balance, String avatarFilePath) {
		this.userID = userID;
		this.name = name;
		this.address = address;
		this.balance = balance;
		this.avatarFilePath = avatarFilePath;
	}

	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public void setuserID(String userID) {
		this.userID = userID;
	}
	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public String getuserID() {
		return userID;
	}

	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public String getname() {
		return name;
	}

	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public void setaddress(String address) {
		this.address = address;
	}
	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public String getaddress() {
		return address;
	}

	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public void setbalance(Float balance) {
		this.balance = balance;
	}
	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public Float getbalance() {
		return balance;
	}

	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public void setavatarFilePath(String avatarFilePath) {
		this.avatarFilePath = avatarFilePath;
	}
	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public String getavatarFilePath() {
		return avatarFilePath;
	}

}