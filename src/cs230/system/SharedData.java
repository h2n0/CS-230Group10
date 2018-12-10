package cs230.system;

import javafx.scene.image.Image;

import java.io.File;

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
        }

        /**
         * Returns the active user object
         */
        public static User getUser() {
                return activeUser;
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

        /**
         * Returns the user's avatar image
         * @return The active user's avatar
         */
        public static Image getAvatar() {
                //Image image = new Image(activeUser.getAvatarFilePath());
                File file = new File(activeUser.getAvatarFilePath());
                return new Image(file.toURI().toString());
        }


		public static void setIsLibrarian(boolean b ) {
			isLibrarian = b;
			
		}
}
