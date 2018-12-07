package cs230.system;

import java.util.ArrayList;

/**
 * This class stores session information such as username to be used by each GUI
 * form.
 * 
 * @author Scott Simmons
 * @version 1.0
 */
public class SharedData {
        // User currently logged in
        private static User activeUser;
        private static boolean isLibrarian;

        /**
         * Sets the current active user and checks if they are a librarian
         * 
         * @param user The current active user
         */
        public static void setUser(User user) {
                activeUser = user;
//                ArrayList<User> allLibrarians = (ArrayList<User>) DatabaseManager.getTable("librarian");
//                allLibrarians.removeIf(l -> !l.getName().equals(activeUser.getName()));
//                if (allLibrarians.size()>0) {
//                        isLibrarian = false;
//                } else {
//                        isLibrarian = true;
//                }
                isLibrarian = true;
        }

        /**
         * Gets the name of the active user
         * 
         * @return The name of the active user
         */
        public static String getUsername() {
                return activeUser.getName();
        }

        /**
         * Gets the balance of the active user
         * 
         * @return The balance of the active user
         */
        public static double getBalance() {
                return activeUser.getBalance();
        }

        /**
         * Gets the address of the active user
         * 
         * @return The address of the active user
         */
        public static Address getAddress() {
                return activeUser.getAddress();
        }
        
        /**
         * Gets whether the current user is a librarian.
         * 
         * @return True/False depending if the user is a librarian
         */
        public static boolean getIsLibrarian() {
                return isLibrarian;
        }
}
