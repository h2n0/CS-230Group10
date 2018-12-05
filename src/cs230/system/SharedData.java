package cs230.system;

/**
 * This class stores session information such as username to be used
 * by each GUI form.
 * @author Scott Simmons
 * @version 1.0
 */
public class SharedData {
	// User currently logged in
	private static User activeUser;

	/**
	 * Sets the current active user
	 * @param user The current active user
	 */
	public static void setUser(User user) {
		activeUser = user;
	}

	/**
	 * Gets the name of the active user
	 * @return The name of the acive user
	 */
	public static String getUsername() {
		return activeUser.getName();
	}

	/**
	 * Gets the balance of the active user
	 * @return The balance of the active user
	 */
	public static double getBalance() {
		return activeUser.getBalance();
	}

	/**
	 * Gets the address of the active user
	 * @return The address of the active user
	 */
	public static Address getAddress() {
		return activeUser.getAddress();
	}
}
