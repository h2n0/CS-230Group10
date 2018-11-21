import java.util.Calendar;

/**
 * This class models and handles a fine attached to a user's loan of a resource.
 * @author Scott (960689)
 * @
 */
public class Fine {
	// ID of the associated loan
	private int historyID;
	// The fixed fine of the resource
	private int fixedFine;
	// The total fine accumulated so far
	private int totalFine = 0;
	// Days the book has been overdue for
	private int overdueDays = 0;
	// Last day the fine was checked + updated
	private int lastDay;
	
	/**
	 * Takes in ID of the history to attach to and the resource's fixed fine
	 * @param historyID ID of the history
	 * @param fixedFine Fixed fine of the resource
	 */
	public Fine(int historyID, int fixedFine) {
		this.historyID = historyID;
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
}