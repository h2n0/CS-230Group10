import java.util.Date;

/**
 * Class Desc
 * @author 
 * @version 
 */

public class Librarian {
	//CommentHere
	private Date employmentDate;
	//CommentHere
	private Integer staffNumber;

	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public Librarian(Date employmentDate, Integer staffNumber) {
		this.employmentDate = employmentDate;
		this.staffNumber = staffNumber;
	}

	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public void setemploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}
	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public Date getemploymentDate() {
		return employmentDate;
	}

	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public void setstaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}
	/**
	 * Method Desc
	 * @param 
	 * @return 
	 */
	public Integer getstaffNumber() {
		return staffNumber;
	}

}