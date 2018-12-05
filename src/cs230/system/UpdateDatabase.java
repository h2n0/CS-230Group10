package cs230.system;
import java.util.Date;
import java.util.TimerTask;

import cs230.application.History;
import cs230.system.Copy;
import cs230.system.DatabaseManager;
import cs230.system.User;

/**
 * @author 963257
 * @version 1.0
 */

public class UpdateDatabase extends TimerTask {
	
	//code to be ran in the main by a schedule
	public void run() {
		//get history and see if any books are overdue
		History allHistory = DatabaseManager.getTable("loan");

		Date today = new Date();
		//remove all loans that have been returned or arent overdue
		allHistory.removeIf(s -> ((s.getReturnDate() != null) || (s.getDueDate().before(today))));
		
		//only left with those that are overdue, hence increase fine
		for (History i : allHistory) {
			//gets the user and the copy
			User u = i.getUser();
			Copy c = i.getCopy();
			
			//get how much each day overdue costs
			Double overdueAmount = c.getOverDueAmount();
			
			//calculate the new balance
			Double newBalance = u.getBalance() + overdueAmount ; 
			
			//save to the database
			User newBalUser = u;
			newBalUser.setBalance(newBalance);
			DatabaseManager.editRecord(u, newBalUser, "user");
		}
		
	}
}