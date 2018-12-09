package cs230.system;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Librarian stores the information for each librarian
 * @author 963257	
 * @version 1.0
 */

public class Librarian extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	//Date the librarian started working in the library
	private LocalDate employmentDate;
	//Uniquely identify each librarian
	private String staffNumber;

	/**
	 * Constructor
	 * @param name the name of the user
         * @param firstname the first name of the user
         * @param lastname the last name of the user
         * @param phonenum the phone number of the user
	 * @param address the address of the user
	 * @param balance how much money the user owes the library
	 * @param avatarFilePath the file path to the user's avatar
	 * @param employmentDate the date the librarian started working in the library
	 * @param staffNumber the identifier for each librarian
	 */
	public Librarian(String name,String firstname, String lastname, String phonenum, Address address,
			 Double balance, String avatarFilePath,
			 LocalDate employmentDate, String staffNumber) {
		super(name, firstname, lastname, phonenum, address, balance, avatarFilePath);
		this.employmentDate = employmentDate;
		this.staffNumber = staffNumber;
	}

	/**
	 * set the date of employment
	 * @param employmentDate the date the librarian started working in the library
	 */
	public void setemploymentDate(LocalDate employmentDate) {
		this.employmentDate = employmentDate;
	}
	/**
	 * get the date of employment
	 * @return the date the librarian started working in the library
	 */
	public LocalDate getemploymentDate() {
		return employmentDate;
	}

	/**
	 * set the staff number
	 * @param staffNumber the staff number of the librarian
	 */
	public void setstaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}
	/**
	 * get the staff number
	 * @return the staff number
	 */
	public String getstaffNumber() {
		return staffNumber;
	}

	public void payfine (Integer userID, Float paid) {
		//Select amount where id = UserID
		//update amount = amount - paid where id = UserID
	}
	
	public void authoriseLoan (Integer userID, Integer copyID) {
		//if the user has negative balance set negative balance to true
		Boolean negativeBalance = false;
		if (negativeBalance) {
			throw new IllegalArgumentException ("That user has unpaid fines and hence cannot take out a book"); 
		}
		else {
			//update copy where id = CopyID, set status to unavailable
			//update resource where id = ResourceID, set availablecopies to -1
			//insert into history where id = copyID, add info
		}
	}

	public void authoriseReturn (Integer loanReferance) {
		//update database to say user has returned copy, changing the status of copy to unavailable and -1 from the available copies in the resource table
	}
}