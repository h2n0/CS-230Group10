
package cs230.system;
import java.util.Date;

/**
 * cs230.system.Copy class stores the information for each copy
 * @author 963257
 * @version 1.0
 */

public class Copy {
	//Uniquely identify each resource
	private Integer ID;
	//identify which resource the copy is
	private Integer resourceID;
	//say whether the copy is available, on loan, or overdue
	private String status;
	//Book/DVD/Laptop
	private String resourceType;
	//date the resource is due
	private Date dueDate;
	//duration of the loan, in days
	private Integer loanDuration;

	/**
	 * Constructor 
	 * @param ID the identifier for the copy
     * @param resourceID the identifier for the resource
     * @param status says if the copy is available, on loan, or overdue
     * @param resourceType if the copy is a Book, DVD, or Laptop
     * @param dueDate date the resource is due
     * @param loanDuration how long you can borrow the copy for
	 */
	public Copy(Integer ID, Integer resourceID, String status, String resourceType, Date dueDate, Integer loanDuration) {
		this.ID = ID;
		this.resourceID = resourceID;
		this.status = status;
		this.resourceType = resourceType;
		this.dueDate = dueDate;
		this.loanDuration = loanDuration;
	}
	
	/**
	 * Constructor when Copy exists in database 
	 * @param ID the identifier for the copy
	*/
	public Copy(Integer ID) {
		//Object[] data = getDataFromDatabase ();
		//Copy(ID,data)
	}
	
	/**
	 * gets the ID
	 * @return the Copy ID
	 */
	public Integer getID() {
		return ID;
	}

	/**
	 * set resource ID
	 * @param resourceID the identifier for the resource
	 */
	public void setresourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}
	/**
	 * get resource ID
	 * @return the Resource ID
	 */
	public Integer getresourceID() {
		return resourceID;
	}

	/**
	 * set the status
	 * @param status say whether the copy is available, on loan, or overdue 
	 */
	public void setstatus(String status) {
		this.status = status;
	}
	/**
	 * get the status
	 * @return the status of the copy
	 */
	public String getstatus() {
		return status;
	}

	/**
	 * set the resource type
	 * @param resourceType says whether the copy is a Book/DVD/Laptop
	 */
	public void setresourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	/**
	 * get the resource type
	 * @return the resource type
	 */
	public String getresourceType() {
		return resourceType;
	}

	/**
	 * set due date
	 * @param dueDate the date the copy is to be returned by
	 */
	public void setdueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * get due date
	 * @return the date the copy is to be returned by
	 */
	public Date getdueDate() {
		return dueDate;
	}

	/**
	 * set loan duration
	 * @param loanDuration the minimum time the resource is loaned for, in days
	 */
	public void setloanDuration(Integer loanDuration) {
		this.loanDuration = loanDuration;
	}
	/**
	 * get loan duration
	 * @return the minimum time the resource is loaned for, in days
	 */
	public Integer getloanDuration() {
		return loanDuration;
	}
	
	public void save () {
		//commit attributes to database 
	}
}