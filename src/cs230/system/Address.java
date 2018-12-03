package cs230.system;
import java.io.Serializable;
import java.util.regex.Pattern;


/**
 * address stores and validates an address (physical not email)
 * @author 963257
 * @version 1.0
 */

public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	//the house name or number
	private String houseNumorName;
	//The road name
	private String roadName;
	//The city name
	private String city;
	//The postcode 
	private String postcode;

	/**
	 * Constructor
	 * @param houseNumorName the house name or number
	 * @param roadName the name of the road
	 * @param city the name of the city
	 * @param postcode the postcode
	 */
	public Address(String houseNumorName, String roadName, String city, String postcode) {
		this.houseNumorName = houseNumorName;
		this.roadName = roadName;
		this.city = city;
		this.postcode = postcode;
	}

	/**
	 * sets the house name or number
	 * @param houseNumorName the house name or number
	 */
	public void sethouseNumorName(String houseNumorName) {
		this.houseNumorName = houseNumorName;
	}
	/**
	 * gets the house name or number
	 * @return the house name or number
	 */
	public String gethouseNumorName() {
		return houseNumorName;
	}

	/**
	 * sets the roadName
	 * @param roadName the roadName
	 */
	public void setroadName(String roadName) {
		this.roadName = roadName;
	}
	/**
	 * gets the roadName
	 * @return the roadName
	 */
	public String getroadName() {
		return roadName;
	}

	/**
	 * sets the city
	 * @param city the city
	 */
	public void setcity(String city) {
		this.city = city;
	}
	/**
	 * gets the city
	 * @return the city
	 */
	public String getcity() {
		return city;
	}

	/**
	 * sets the postcode
	 * @param postcode the postcode
	 */
	public void setpostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * gets the postcode
	 * @return the postcode
	 */
	public String getpostcode() {
		return postcode;
	}
	
	/**
	 * pattern extracted from wiki article below
	 * https://en.wikipedia.org/wiki/Postcodes_in_the_United_Kingdom
	 * @param postcode the postcode to be validated
	 * @return true if valid, false otherwise
	 */
	private Boolean validPostcode(String postcode) {
		//1/2 uppercase then 1/2 digits then space then 1 digit then 2 uppercase letters
		String validpostcode = "([A-Z]{1,2}\\d{1,2}? \\d\\\\p{Upper}){2}?";
		Boolean match = Pattern.matches(validpostcode, postcode);
		return match;
	}
	
	// house number/house name, road name, City, Postcode 
	/**
	 * validates the address
	 * @return true if valid, false otherwise
	 */
	public Boolean validAddress() {
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