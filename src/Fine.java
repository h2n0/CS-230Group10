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
		int DaysPassed = calcDays(lastDay);
		totalFine += fixedFine * DaysPassed;
		overdueDays += DaysPassed;
	}
	
	/**
	 * Calculates difference between last day recorded and current day
	 * @param lastDay The last day recorded
	 * @return Difference between current and last recorded day
	 */
	private static int calcDays(int lastDay) {
		int DaysInYear = 365;
		
		if (lastDay < Calendar.DAY_OF_YEAR) {
			int x = lastDay;
			lastDay = Calendar.DAY_OF_YEAR;
			return Calendar.DAY_OF_YEAR - lastDay;
			
		} else if (Calendar.DAY_OF_YEAR < lastDay) {
			// In case it's a new year
			lastDay = Calendar.DAY_OF_YEAR;
			return (DaysInYear - lastDay) + Calendar.DAY_OF_YEAR;
			
		} else {
			return 0;
		}
	}
}