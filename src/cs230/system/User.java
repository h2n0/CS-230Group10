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
        //The user's username
        private String username;
        //The user's address
        private Address address;
        //The user's balance 
        private Double balance;
        //The filepath to the user's avatar
        private String avatarFilePath;
        //The user's first name
        private String firstName;
        //The user's last name
        private String lastName;
        //The user's telephone number
        private String phoneNum;
		
        /**
	     * Constructor for a new user
	     * @param username the username of the user
	     * @param firstName the first name of the user
	     * @param lastName the last name of the user
	     * @param phoneNum the phone number of the user
	     * @param address the address of the user
	     * @param balance how much money the user owes the library
	     * @param avatarFilePath the file path to the user's avatar
	     */
        public User(String username, String firstName, String lastName, String phoneNum, Address address, Double balance, String avatarFilePath) {
                this.username = username;
                this.firstName = firstName;
                this.lastName = lastName;
                this.phoneNum = phoneNum;
                this.address = address;
                this.balance = balance;
                this.avatarFilePath = avatarFilePath;
	    }
        
        /**
         * Constructor to copy a user
         * @param username the username of the user
         */
        public void User(User user)
        {
                this.username = user.getName();
        }
	
        /**
	     * Sets the username
	     * @param name The new username of the user
	     */
        public void setName(String username) {
                this.username = username;
	    }
        /**
	     * Gets the username
	     * @return The username
	     */
        public String getName() {
                return username;
	    }
        /**
         * Sets the firstName
         * @param name The new firstName of the user
         */
        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }
        /**
         * Gets the firstName
         * @return The firstName
         */
        public String getfirstName() {
                return firstName;
        }
        
        /**
         * Sets the lastName
         * @param name The new lastName of the user
         */
        public void setlastName(String lastName) {
                this.lastName = lastName;
        }
        /**
         * Gets the lastName
         * @return The lastName
         */
        public String getlastName() {
                return lastName;
        }
        
        /**
         * Sets the phoneNum
         * @param name The new phoneNum of the user
         */
        public void setphoneNum(String phoneNum) {
                this.phoneNum = phoneNum;
        }
        /**
         * Gets the phoneNum
         * @return The phoneNum
         */
        public String getphoneNum() {
                return phoneNum;
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
                if (this.username.equalsIgnoreCase(PERSON.getName())) {
                        return true;
			    }
                return false;
	    }

        /**
	     * Returns the user name
	     * @return
	     */
        public String toString() {
                return this.username;
	    }
}
