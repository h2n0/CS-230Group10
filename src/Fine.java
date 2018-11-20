import java.util.Calendar;

/**
 * This class models and handles a fine attached to a user's loan of a resource.
 * @author Scott (960689)
 * @
 */
public class Fine {
	private int historyID;
	private int fixedFine;
	private int totalFine;
	private int overdueDays;
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
	}
	
	public void updateFine() {
	}
}
