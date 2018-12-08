package cs230.system;
import java.io.Serializable;

/**
 * Copy class stores the information for each copy
 * @author 963257
 * @version 1.1
 */
public class Copy implements Serializable {
        //Default serial ID
        private static final long serialVersionUID = 1L;
        //Uniquely identify each resource
        private String ID;
        //identify which resource the copy is
        private String resourceID;
        //say whether the copy is available, on loan, or overdue
        private Status status;
        //Book/DVD/Laptop
        private String resourceType;
        //duration of the loan, in days
        private int loanDuration;

        /**
	     * Constructs a copy entity
	     * @param ID the identifier for the copy
         * @param resourceID the identifier for the resource
         * @param status says if the copy is available, on loan, or overdue
         * @param resourceType if the copy is a Book, DVD, or Laptop
         * @param loanDuration how long you can borrow the copy for
	     */
        public Copy(String ID, String resourceID, Status status,
                String resourceType, int loanDuration) {
                this.ID = ID;
                this.resourceID = resourceID;
                this.status = status;
                this.resourceType = resourceType;
                this.loanDuration = loanDuration;
	    }
	
        /**
	     * Constructs only the candidate keys of the copy for database
	     * searching purposes
	     * @param ID The ID of the copy
	     * @param resourceID The ID of the resource the copy is of
	     */
        public Copy(String ID, String resourceID) {
                this.ID = ID;
                this.resourceID = resourceID;
                this.status = status;
        }
	
        /**
	     * Gets the ID
	     * @return The copy ID
	     */
        public String getID() {
                return ID;
	    }

        /**
	     * Gets the resource ID
	     * @return The resource ID
	     */
        public String getResourceID() {
                return resourceID;
        }

        /**
	     * Sets the status
	     * @param status Either AVAILABLE, ON_LOAN or OVERDUE
	     */
        public void setstatus(Status status) {
                this.status = status;
	    }

        /**
	     * Gets the status
	     * @return The status of the copy
	     */
        public Status getstatus() {
                return status;
	    }

        /**
	     * Set the resource type
	     * @param resourceType Says whether the copy is a Book/DVD/Laptop
	     */
        public void setresourceType(String resourceType) {
                this.resourceType = resourceType;
	    }

	    /**
	     * Gets the resource type
	     * @return The type of resource
	     */
        public String getResourceType() {
                return resourceType;
	    }

        /**
	     * Sets the loan duration
	     * @param loanDuration The minimum time the resource can be loaned for,
	     * in days
	     */
        public void setloanDuration(int loanDuration) {
                this.loanDuration = loanDuration;
	    }

        /**
	     * Gets the loan duration
	     * @return The minimum time the resource can be loaned for, in days
	     */
        public int getLoanDuration() {
                return loanDuration;
	    }

        /**
	     * Saves this copy to the database
	     */
        public void save() {
                DatabaseManager.saveRecord(this, "copy");
        }

        /**
	     * Checks if another object is an exact match to this one, so it
	     * compares the candidate keys
	     * @param obj The object to compare this to
	     * @return True if they match, False otherwise
	     */
        public boolean equals(Object obj) {
                if (obj == null) {
                        return false;
                }

                Copy copy = (Copy) obj;

                // If candidate keys match return true
                return ID.equalsIgnoreCase(copy.getID()) && resourceID.equalsIgnoreCase(copy.getResourceID());
	    }

        /**
	     * The possible statuses a copy can be; Available, On_loan or overdue
	     */
        public enum Status {
                AVAILABLE,
                ON_LOAN,
                OVERDUE
	    }
}