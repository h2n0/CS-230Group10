import java.util.Date;

/**
 * Librarian stores the information for each librarian
 * @author 963257	
 * @version 1.0
 */

public class Librarian extends User {
	//Date the librarian started working in the library
	private Date employmentDate;
	//Uniquely identify each librarian
	private Integer staffNumber;

	/**
	 * Constructor
	 * @param userID the identifier for the copy
	 * @param name the name of the user
	 * @param address the address of the user
	 * @param balance how much money the user owes the library
	 * @param avatarFilePath the file path to the user's avatar
	 * @param employmentDate the date the librarian started working in the library
	 * @param staffnumber the identifier for each librarian
	 */
	public Librarian(Integer userID, String name, String address, Float balance, String avatarFilePath,Date employmentDate, Integer staffNumber) {
		super(userID, name, address, balance, avatarFilePath);
		this.employmentDate = employmentDate;
		this.staffNumber = staffNumber;
	}

	/**
	 * set the date of employment
	 * @param employmentDate the date the librarian started working in the library
	 */
	public void setemploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}
	/**
	 * get the date of employment
	 * @return the date the librarian started working in the library
	 */
	public Date getemploymentDate() {
		return employmentDate;
	}

	/**
	 * set the staff number
	 * @param staffNumber the staff number of the librarian
	 */
	public void setstaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}
	/**
	 * get the staff number
	 * @return the staff number
	 */
	public Integer getstaffNumber() {
		return staffNumber;
	}

	public void payfine (Integer userID, Float amount) {
		//update db to reduce fine by amount where userID 
	}
	
	public void authoriseLoan (Integer userID, Integer copyID) {
		
	}

	public void authoriseReturn (Integer loanReferance) {
		
	}
}