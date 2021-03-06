package cs230.system;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * This class communicates and interacts with the database system, providing data
 * through queries and organising it.
 * @author Scott
 * @version 1.0
 *
 */
public class DatabaseManager {
	// Path to the database file in the project
	private static final String DB_PATH = "Database//";
	// Extension for the database files
	private static final String EXTENSION = ".dat";

	/**
	 * Gets all records from a specified table
	 * @param table The table to retrieve
	 * @return The table in the form of an array list
	 */
	public static Object getTable(String table) {
		// Check variable for end of file
		String filePath = compilePath(table);
		Object data;

		try {
			FileInputStream fileWrite =
				new FileInputStream(filePath);
			ObjectInputStream objI =
				new ObjectInputStream(fileWrite);

			// Check if file is empty to prevent IOException
			if (fileWrite.available() != 0) {
				// Cast file content to an arraylist of objects
				data = objI.readObject();
				objI.close();
				return data;
			} else {
				return null;
			}

		} catch (IOException e) {
			displayIOError("getTable");
			e.printStackTrace();
			return null;

		} catch (ClassNotFoundException e) {
			return null;

		}
	}

	/**
	 * Saves a record in object form to the specified table in the database
	 * @param record The object to save to the database
	 * @param table The table to save to
	 * @return True if saved successfully, false otherwise
	 */
	public static boolean saveRecord(Object record, String table) {
		String path = compilePath(table);

		try {
			ArrayList<Object> data;
			FileInputStream fileWrite =
				new FileInputStream(path);

			// Check if file is empty to prevent EOFException
			if (fileWrite.available() != 0) {
				// Get table
				data = getTable(fileWrite);
			} else {
				data = new ArrayList<>();
			}

			// Make sure no record with duplicate name exists
			for (Object item : data) {
				if (item.toString().equalsIgnoreCase(record.toString())) {
					displayDupError(record.toString());
					return false;
				}
			}

			// Add new record to list
			data.add(record);

			// Write data to file
			writeToFile(
				new FileOutputStream(path), data);

			return true;
		} catch (FileNotFoundException e) {
			// If the file is not found, display an error pane
			displayFileError();
			return false;
		} catch (IOException e) {
			// If there is an error writing to file, display an error
			displayIOError("saveRecord");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes a specific record from the specified table
	 * @param record The record to delete
	 * @param table The table to delete from
	 * @return True if successful, false otherwise
	 */
	public static boolean deleteRecord(Object record, String table) {
		String filePath = compilePath(table);

		try {
			FileInputStream fileRead =
				new FileInputStream(filePath);

			// Get table and place in an array
			ArrayList<Object> tableArray = getTable(fileRead);

			// Remove record from the array
			tableArray.remove(record);

			// Write new array to file
			writeToFile(
				new FileOutputStream(filePath), tableArray);
			return true;
		} catch (IOException e) {
			displayIOError("deleteRecord");
			return false;
		}
	}

	/**
	 * Takes a record object and returns all objects in the database that
	 * contain part of that object
	 * @param record The record to compare to
	 * @param table The table to search in
	 * @return An arraylist of matching objects
	 */
	public static Object searchRecord(Object record, String table) {
		// Filepath of the database table
		String filePath = compilePath(table);
		// Arraylist to store matching objects
		ArrayList<Object> output = new ArrayList<>();
		// String to store result from table
		String tableRec;
		// String to store data from the object 'record'
		String compRec = record.toString().toLowerCase();

		try {
			ArrayList<Object> data =
				getTable(new FileInputStream(filePath));

			// If any item in data is contained in the record add
			// it to the output
			for (Object item : data) {
				tableRec = item.toString().toLowerCase();
				if (compRec.contains(tableRec)) {
					output.add(item);
				}
			}

			return output;
		} catch(FileNotFoundException e) {
			displayFileError();
			return null;
		}


	}

	/**
	 * Takes an object and if it exists in the specified table return
	 * True, else False
	 * @param record The record to compare to
	 * @param table The table to search in
	 * @return True if it exists, False otherwise
	 */
	public static boolean exists(Object record,
					 String table) {
		String filePath = compilePath(table);
		ArrayList<Object> tableArray;

		try {
			//Load file
			tableArray = getTable(new FileInputStream(filePath));

			//Find and return the matching record if possible
			for (Object item : tableArray) {
				if (item.equals(record)) {
					return true;
				}
			}

			return false;

		} catch (FileNotFoundException e) {
			displayFileError();
			return false;
		}
	}

	/**
	 * Takes a record object and returns an object that is an exact match
	 * to it
	 * @param record The record to compare to
	 * @param table The table to search in
	 * @return The exact matching record, or null if it cannot be found
	 */
	public static Object searchExact(Object record,
						   String table) {
		String filePath = compilePath(table);
		ArrayList<Object> tableArray;

		try {
			//Load file
			tableArray = getTable(new FileInputStream(filePath));

			//Find and return the matching record if possible
			for (Object item : tableArray) {
				if (item.equals(record)) {
					return item;
				}
			}

			return null;

		} catch (FileNotFoundException e) {
			displayFileError();
			return null;
		}
	}

	/**
	 * Takes a record object and checks if it exists in the specified table
	 * @param record The record to look for
	 * @param table The table to search in
	 * @return True if it exists, False otherwise
	 */
	public static boolean checkForRecord(Object record,
						     String table) {
		String filePath = compilePath(table);

		try {
			//Load file
			ArrayList<Object> data =
				getTable(new FileInputStream(filePath));

			//Check if any record matches
			for (Object item : data) {
				if (record.equals(item)) {
					return true;
				}
			}

			return false;

		} catch (FileNotFoundException e) {
			displayFileError();
			return false;
		}
	}

	/**
	 * Takes in the old object and the new version then replaces it in
	 * the database
	 * @param oldRecord Record to replace
	 * @param newRecord Updated verison of the record
	 * @param table Name of the table to perform this on
	 * @return True if successful, false otherwise
	 */
	public static boolean editRecord(Object oldRecord,
				      Object newRecord, String table) {
		String filePath = compilePath(table);
		int index;

		try {
			// Obtain record to edit
			ArrayList<Object> tableCont =
				getTable(new FileInputStream(filePath));

			for(Object item : tableCont) {
				if (item.toString().equals(oldRecord.toString())) {
					index = tableCont.indexOf(item);
					tableCont.set(index, newRecord);
				}
			}

			// Re write to table
			writeToFile(new FileOutputStream(filePath), tableCont);

			return true;

		} catch (FileNotFoundException e) {
			displayFileError();
			return false;
		}
	}

	/**
	 * Returns the next resource ID to use and increments the ID for next
	 * user
	 * @return The next available resource ID
	 */
	public static String getNextID() {
		String filePath = compilePath("resourceID");
		String ID;
		String newID;

		try {
			// Get ID
			Scanner fileIn = new Scanner(filePath);
			ID = fileIn.next();

			fileIn.close();

			// Increment ID
			newID = String.valueOf(Integer.parseInt(ID) + 1);

			BufferedWriter fileOut =
				new BufferedWriter(new FileWriter(filePath));
			fileOut.write(newID);
			fileOut.close();

			return ID;

		} catch(IOException e) {
			displayIOError("getNextID");
			return null;
		}
	}

	/**
	 * Gets all records from a specified table
	 * @param fileRead File input stream open on desired table
	 * @return The table in the form of an array list
	 */
	private static ArrayList<Object> getTable(FileInputStream fileRead) {
		ArrayList<Object> output = new ArrayList<>();
		// Check variable for end of file

		try {
			// Check if file is empty to prevent IOException
			if (fileRead.available() != 0) {
				ObjectInputStream objI =
					new ObjectInputStream(fileRead);
				// Cast file content to an arraylist of objects
				output = (ArrayList<Object>) objI.readObject();
				objI.close();
				return output;
			} else {
				return output;
			}

		} catch (IOException e) {
			displayIOError("getTable");
			e.printStackTrace();
			return null;

		} catch (ClassNotFoundException e) {
			// Class not found should never be thrown
			return null;

		}
	}

	/**
	 * Displays the file not found error message using a JOptionPane
	 */
	private static void displayFileError() {
		JOptionPane.showMessageDialog(null, "File not found, check file path and try again",
			"File not found", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Displays the input/output error message using a JOptionPane
	 */
	private static void displayIOError(String methodName) {
		JOptionPane.showMessageDialog(null,
			"Error in " + methodName + " while " +
				"writing to file, try again",
			"I/O Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Displays an error pane informing of an already existing record
	 * with the same primary key in the database.
	 * @param name Existing parameter in the database
	 */
	private static void displayDupError(String name) {
		JOptionPane.showMessageDialog(null,
			"Sorry but '" + name + "' already exists in the " +
				"database",
			"Duplicate Entry", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Writes an arraylist to a specified file
	 * @param fileOut Fileoutputstream open on the specified file
	 * @param data The arraylist to write to the file
	 * @return True if succeeded, false otherwise
	 */
	private static boolean writeToFile(FileOutputStream fileOut,
					   ArrayList<Object> data) {
		try {
			ObjectOutputStream objO =
				new ObjectOutputStream(fileOut);
			objO.writeObject(data);
			objO.flush();
			objO.close();
			return true;
		} catch (IOException e) {
			displayIOError("writeToFile");
			return false;
		}
	}

	/**
	 * Compiles the file path of the table passed in
	 * @param table The destination table
	 * @return Compiled file path to the table
	 */
	private static String compilePath(String table) {
		return DB_PATH + table + EXTENSION;
	}

	/**
	 * Sorts a given list
	 * @param list The list to be sorted
	 * @return The sorted list
	 */
	private static ArrayList<Object> sortList(ArrayList<Object> list) {
		return null;
	}

	public static void main(String[] args) {
		/*
		ArrayList<Object> test;
		Fine fine1 = new Fine(1, 20);
		Fine fine2 = new Fine(2, 30);
		saveRecord(fine1, "test");
		saveRecord(fine2, "test");
		test = getTable(new FileInputStream("Database//test.dat"));
		System.out.println("Amount of data: " + test.size());
		System.out.println("Object found: " + searchRecord(fine1,
			"test"));
		deleteRecord(fine1, "test");
		test = getTable(new FileInputStream("Database//test.dat"));
		System.out.println("Amount of data: " + test.size());
		*/

		Address address = new Address("30", "Canal Terrace", "Swansea", "SA9 2LP");
		User user = new User("Joeseph", address, 10.0, null);
		saveRecord(user, "user");
	}
}
