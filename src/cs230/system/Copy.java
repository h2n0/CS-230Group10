
package cs230.system;
import java.io.Serializable;
import java.util.Date;

/**
 * cs230.system.Copy class stores the information for each copy
 * @author 963257
 * @version 1.0
 */

public class Copy implements Serializable {
	//Default serial ID
	private static final long serialVersionUID = 1L;
	//Uniquely identify each resource
	private String ID;
	//identify which resource the copy is
	private String resourceID;
	//say whether the copy is available, on loan, or overdue
	private String status;
	//Book/DVD/Laptop
	private String resourceType;
	//duration of the loan, in days
	private int loanDuration;

	/**
	 * Constructor 
	 * @param ID the identifier for the copy
     * @param resourceID the identifier for the resource
     * @param status says if the copy is available, on loan, or overdue
     * @param resourceType if the copy is a Book, DVD, or Laptop
     * @param loanDuration how long you can borrow the copy for
	 */
	public Copy(String ID, String resourceID, String status,
		    String resourceType, int loanDuration) {
		this.ID = ID;
		this.resourceID = resourceID;
		this.status = status;
		this.resourceType = resourceType;
		this.loanDuration = loanDuration;
	}
	
	/**
	 * Constructor when Copy exists in database 
	 * @param ID the identifier for the copy
	*/
	public Copy(String ID) {
		//Object[] data = getDataFromDatabase ();
		//Copy(ID,data)
	}
	
	/**
	 * gets the ID
	 * @return the Copy ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * get resource ID
	 * @return the Resource ID
	 */
	public String getResourceID() {
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
	public String getResourceType() {
		return resourceType;
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
	public Integer getLoanDuration() {
		return loanDuration;
	}
	
	public void save () {
		//commit attributes to database 
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Copy copy = (Copy) obj;

		// If they are an exact match return true
		if (this.ID.equalsIgnoreCase(copy.ID)) {
			return true;
		}

		return false;
	}
}