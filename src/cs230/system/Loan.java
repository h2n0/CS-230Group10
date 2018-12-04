package cs230.system;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * This class models the attributes and functions of a loan in the system.
 */
public class Loan {
	// ID to identify loan
	private String loanID;
	// Name of the user associated with the loan
	private String userName;
	// ID of the copy the loan is attatched to
	private String copyID;
	// Due date
	private LocalDate dueDate = null;
	// Date the copy was borrowed
	private LocalDate borrowDate;
	// Date the copy was returned
	private LocalDate returnedDate;
	// Format for all date values
	private static final String DATE_FORMAT = "dd-MM-yyyy";

	/**
	 * The constructor for a loan without a due date already set
	 * @param loanID The ID of the loan
	 * @param userName The username attatched to the loan
	 * @param copyID The ID of the copy attatched to the loan
	 * @param borrowDate The date the loan was borrowed
	 */
	public Loan(String loanID, String userName, String copyID,
		    LocalDate borrowDate) {

		// Get copy from database
		Copy searchCopy = new Copy(copyID);
		searchCopy = (Copy) DatabaseManager.searchExact(searchCopy,
			"copy");


		// Set all attributes
		// Due date
		int loanDuration = searchCopy.getLoanDuration();
		this.dueDate = borrowDate.plusDays(loanDuration);

		// Others
		this.loanID = loanID;
		this.copyID = copyID;
		this.userName = userName;
		this.borrowDate = borrowDate;
	}

	/**
	 * The constructor for a loan without a due set
	 * @param loanID The ID of the loan
	 * @param userName The username attatched to the loan
	 * @param copyID The ID of the copy attatched to the loan
	 * @param borrowDate The date the loan was borrowed
	 */
	public Loan(String loanID, String userName, String copyID,
		    LocalDate borrowDate, LocalDate dueDate) {
		// Set attributes
		this.dueDate = dueDate;
		this.loanID = loanID;
		this.copyID = copyID;
		this.userName = userName;
		this.borrowDate = borrowDate;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Loan loan = (Loan) obj;

		// If they are an exact match return true
		if (this.loanID.equalsIgnoreCase(loan.loanID)) {
			return true;
		}

		return false;
	}

	public String toString() {
		return userName + ", " + copyID;
	}
}
