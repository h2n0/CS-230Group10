package cs230.system;

import java.util.Date;
import java.util.TimerTask;
import java.time.LocalDate;
import java.util.ArrayList;
import cs230.system.Loan;
import cs230.system.Copy;
import cs230.system.DatabaseManager;
import cs230.system.User;
import cs230.system.Copy.Status;

/**
 * @author 963257
 * @version 1.0
 */

public class UpdateDatabase extends TimerTask {
	
	//code to be ran in the main by a schedule
	public void run() {
		//get history and see if any books are overdue
                if (DatabaseManager.getTable("loan") != null) {
                        @SuppressWarnings("unchecked")
                        ArrayList<Loan> allHistory = (ArrayList<Loan>) DatabaseManager.getTable("loan");

                        LocalDate today = LocalDate.now();
                        //remove all loans that have been returned or arent overdue
                        allHistory.removeIf(s -> ((s.getReturnedDate() != null) || (s.getDueDate().isAfter(today))));

                        //only left with those that are overdue, hence increase fine
                        for (Loan i : allHistory) {
                                //gets the user and the copy
                                String uname = i.getUserName();
                                String cid = i.getCopyID();
                                String rid = i.getResourceID();

                                User tempUser = new User(uname, null, null, null, null, null, null);
                                Copy tempCopy = new Copy(cid, rid);

                                User u = (User) DatabaseManager.searchRecord(tempUser, "user");
                                Copy c = (Copy) DatabaseManager.searchRecord(tempCopy, "copy");

                                //get how much each day overdue costs
                                Double overdueAmount = c.getOverdueCost();

                                //calculate the new balance
                                Double newBalance = u.getBalance() + overdueAmount;

                                //save to the database
                                User newBalUser = u;
                                newBalUser.setBalance(newBalance);
                                DatabaseManager.editRecord(u, newBalUser, "user");
                        }
                }
		
	}
}
