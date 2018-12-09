package cs230.system;

import java.util.Date;

/**
 * Models the transaction history, 
 * @author 963257
 *
 */
public class Transaction {
	// ID of the transaction
	private final int transactionID;
	// User involved in the transaction
	private final User user;
	// date of the transaction
	private final Date date;
	// type of transaction (payment or fine)
	private final String type;
	// amount either payed or added on depending on type
	private final double amount;
	
	/**
	 * constructor for a transaction
	 * @param transactionID identifies the transaction
	 * @param user the user involved
	 * @param date the date of the transaction
	 * @param type the type of transaction
	 * @param amount the amount paid/owed 
	 */
	public Transaction(int transactionID, User user, Date date, String type, double amount) {
	        this.transactionID = transactionID;
	        this.user = user;
	        this.date = date;
	        this.type = type;
	        this.amount = amount;
	}
	
	/**
     * gets the transactionID
     * @return transactionID the ID of the transaction
     */
    public int getTransactionID() {
        return transactionID;
    }
    
    /**
     * gets the user
     * @return the user
     */
    public User getUser() {
        return user;
    }
    
    /**
     * gets the date of the transaction
     * @return date the date of the transaction
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * gets the type of transaction
     * @return type the type of transaction
     */
    public String getType() {
        return type;
    }
    
    /**
     * gets the amount of transaction
     * @return amount the amount of transaction
     */
    public double getAmount() {
        return amount;
    }
    
    

	
}