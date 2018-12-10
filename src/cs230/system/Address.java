package cs230.system;
import java.io.Serializable;
import java.util.regex.Pattern;


/**
 * Address stores and validates an address (physical not email)
 * @author 963257
 * @version 1.0
 */
public class Address implements Serializable {
	    private static final long serialVersionUID = 1L;
	    //the house name or number
	    private String houseNumberName;
	    //The road name
	    private String roadName;
	    //The city name
	    private String city;
	    //The postcode 
	    private String postcode;
	    
	    /**
	     * Constructor
	     * @param houseNumborName The house name or number
	     * @param roadName The name of the road
	     * @param city The name of the city
	     * @param postcode The postcode
	     */
	    public Address(String houseNumberName, String roadName, String city, String postcode) {
	            this.houseNumberName = houseNumberName;
	            this.roadName = roadName;
	            this.city = city;
	            this.postcode = postcode;
	    }

	    /**
	     * Sets the house name or number
	     * @param houseNumorName The house name or number
	     */
	    public void setHouseNumberName(String houseNumberName) {
	            this.houseNumberName = houseNumberName;
	    }
	    /**
	     * Gets the house name or number
	     * @return The house name or number
	     */
	    public String getHouseNumberName() {
	            return houseNumberName;
	    }

	    /**
	     * Sets the roadName
	     * @param roadName The road name
	     */
	    public void setRoadName(String roadName) {
	            this.roadName = roadName;
	    }
	    /**
	     * Gets the road name
	     * @return The road name
	     */
	    public String getRoadName() {
	            return roadName;
	    }

	    /**
	     * Sets the city
	     * @param city The city
	     */
	    public void setCity(String city) {
	            this.city = city;
	    }
	    /**
	     * Gets the city
	     * @return The city
	     */
	    public String getCity() {
	            return city;
	    }

	    /**
	     * Sets the postcode
	     * @param postcode The postcode
	     */
	    public void setPostcode(String postcode) {
	        	this.postcode = postcode;
	    }

	    /**
	     * Gets the postcode
	     * @return The postcode
	     */
	    public String getPostcode() {
	            return postcode;
	    }
	
	    /**
	     * Pattern extracted from wiki article below
	     * https://en.wikipedia.org/wiki/Postcodes_in_the_United_Kingdom
	     * @param postcode The postcode to be validated
	     * @return True if valid, False otherwise
	     */
	    private Boolean validPostcode(String postcode) {
	            //1/2 uppercase then 1/2 digits then space then 1 digit then 2 uppercase letters
	            String validpostcode = "([A-Z]{1,2}\\d{1,2}? \\d\\\\p{Upper}){2}?";
	            Boolean match = Pattern.matches(validpostcode, postcode);
	            return match;
	    }

	    /**
	     * Validates the enitre address
	     * @return True if valid, False otherwise
	     */
	    public Boolean validAddress() {
	            //a lower or upper case letter or a space any amount of times
	            String letterOrSpace = "([a-zA-Z[\\p{Blank}]])*";
	            
	            //letterOrSpace or a number any amount of times
	            Boolean validHouseNumorName = Pattern.matches(letterOrSpace, houseNumberName) || Pattern.matches("\\d*", houseNumberName);
	            
	            //letterOrSpace
	            Boolean validRoadName = Pattern.matches(letterOrSpace, roadName);
	            
	            //letterOrSpace
	            Boolean validCity = Pattern.matches(letterOrSpace, city);
	            
	            //only valid if all parts are valid
	            return validHouseNumorName && validRoadName && validCity && validPostcode(postcode);
	    }
}