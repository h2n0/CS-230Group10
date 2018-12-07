package cs230.system;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This class models and handles a fine attached to a user's loan of a resource.
 * @author Scott (960689)
 *
 */
public class Fine implements Serializable {
	private static final long serialVersionUID = 1L;
	// ID of the associated loan
	private final int loanID;
	// The fixed fine of the resource
	private final int fixedFine;
	// The total fine accumulated so far
	private int totalFine = 0;
	// Days the book has been overdue for
	private int overdueDays = 0;
	// Last day the fine was checked + updated**
	private int lastDay;
	
	/**
	 * Takes in ID of the loan to attach to and the resource's fixed fine
	 * @param loanID ID of the loan
	 * @param fixedFine Fixed fine of the resource
	 */
	public Fine(int loanID, int fixedFine) {
		this.loanID = loanID;
		this.fixedFine = fixedFine;
		lastDay = Calendar.DAY_OF_YEAR;
	}

	/**
	 * Reduces fine by a specified value 'amount'
	 * @param amount The amount to pay off
	 */
	public void payFine(int amount) {
		totalFine -= amount;
		updateFine();
		lastDay = Calendar.DAY_OF_YEAR;
	}
	
	/**
	 * Updates the total fine accumulated and overdue days
	 */
	public void updateFine() {
		int daysPassed = calcDays(lastDay);
		totalFine += fixedFine * daysPassed;
		overdueDays += daysPassed;
	}
	
	/**
	 * Calculates difference between last day recorded and current day
	 * @param lastDay The last day recorded
	 * @return Difference between current and last recorded day
	 */
	private static int calcDays(int lastDay) {
		int daysInYear = 365;
		int leapYear = 366;
		int currentDay = Calendar.DAY_OF_YEAR;
		
		if (lastDay < currentDay) {
			return currentDay - lastDay;
			
		} else if (currentDay < lastDay) {
			// In case it's a new year
			if (isLeapYear(Calendar.YEAR)) {
				// Leap year
				return (leapYear - lastDay) + currentDay;
			} else {
				// Non leap year
				return (daysInYear - lastDay) + currentDay;
			}
		}
		
		// If no difference
		return 0;
	}
	
	/**
	 * Determines if year is a leap year
	 * @param year Year to check
	 * @return True for leap year, false otherwise
	 */
	private static boolean isLeapYear(int year) {
		if (year % 4 == 0) {
			return true;
		}
		
		return false;
	}

	/**
	 * Checks if this fine is equal to another object
	 * @param obj The object to check against
	 * @return True if equal, false otherwise
	 */
	public boolean equals(Object obj) {
		// If object is null, can't be equal
		if (obj == null) {
			return false;
		}

		// If object is exactly this, they must be equal
		if (obj == this) {
			return true;
		}

		// If object is not of type cs230.system.Fine, cannot be this
		if (!(obj instanceof Fine)) {
			return false;
		}

		// If all checks passed, compared historyIDs
		Fine checkFine = (Fine) obj;
		return checkFine.loanID == this.loanID;
	}

	/**
	 * Returns accumulated fine
	 * @return fine
	 */
	public int getFine() {
		return totalFine;
	}
	
	/**
	 * Returns associated ID
	 * @return loanID
	 */
	public int getLoanID() {
		return loanID;
	}
	
	/**
	 * Returns days overdue for
	 * @return Days overdue
	 */
	public int getOverdueDays() {
		return overdueDays;
	}
}