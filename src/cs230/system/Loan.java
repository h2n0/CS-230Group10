package cs230.system;

import java.time.LocalDate;

/**
 * This class models the attributes and functions of a loan in the system.
 * @author Scott Simmons
 * @version 1.0
 */
public class Loan {
	// ID to identify loan
	private final String loanID;
	// Name of the user associated with the loan
	private final String userName;
	// ID of the copy the loan is attatched to
	private final String copyID;
	// Due date
	private LocalDate dueDate = null;
	// Date the copy was borrowed
	private final LocalDate borrowDate;
	// Date the copy was returned
	private LocalDate returnedDate;

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
		dueDate = borrowDate.plusDays(loanDuration);

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
		return loanID.equalsIgnoreCase(loan.loanID);

	}

	public String toString() {
		return userName + ", " + copyID + ", " + getCopyName();
	}

	/**
	 * Returns the unique loan ID
	 * @return The loan ID
	 */
	public String getLoanID() {
		return loanID;
	}

	/**
	 * Returns the username attatched to the loan
	 * @return The user's name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Returns the ID of this copy of the resource
	 * @return The copy ID
	 */
	public String getCopyID() {
		return copyID;
	}

	/**
	 * Returns the date that this resource is due back
	 * @return The due date
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}

	/**
	 * Returns the date this resource was borrowed on
	 * @return The borrow date
	 */
	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	/**
	 * Returns the date this resource was returned on
	 * @return The returned date
	 */
	public LocalDate getReturnedDate() {
		return returnedDate;
	}

	/**
	 * Gets the name of the copy attatched to this loan
	 * @return The name of the copy
	 */
	private String getCopyName() {
		String type;
		Dvd dvdRes;
		Book bookRes;

		// Get resource ID from the copy
		Copy copy = new Copy(copyID);
		copy = (Copy) DatabaseManager.searchExact(copy, "copy");
		type = copy.getResourceType();

		// Get the resource name
		switch (type.toLowerCase()) {
			case "dvd":
				dvdRes =
					(Dvd) DatabaseManager.searchExact(new Dvd(copy.getResourceID()), "dvd");
				return dvdRes.getTitle();
			case "book":
				bookRes =
					(Book) DatabaseManager.searchExact(new Book(copy.getResourceID()), "book");
				return bookRes.getTitle();
			default:
				return null;
		}
	}
}