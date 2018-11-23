import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * This class communicates and interacts with the database system, providing data
 * through queries and organising it.
 * @author Scott
 *
 */
public class DatabaseManager {
	// Path to the database file in the project
	private static final String DB_PATH = "Database//";
	// Extension for the database files
	private static final String EXTENSION = ".dat";


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
	 * Gets all records from a specified table
	 * @param fileWrite File input stream open on desired table
	 * @return The table in the form of an array list
	 */
	private static ArrayList<Object> getTable(FileInputStream fileWrite) {
		ArrayList<Object> output = new ArrayList<>();
		// Check variable for end of file

		try {
			// Check if file is empty to prevent IOException
			if (fileWrite.available() != 0) {
				ObjectInputStream objI =
					new ObjectInputStream(fileWrite);
				// Cast file content to an arraylist of objects
				output = (ArrayList<Object>) objI.readObject();
				objI.close();
				return output;
			} else {
				return output;
			}

		} catch (IOException e) {
			displayIOError("getTable");
			System.out.println("WHy am I here");
			return null;

		} catch (ClassNotFoundException e) {
			// ADD HANDLE CODE HERE
			return null;

		}
	}

	/**
	 * Writes an arraylist of objects to a specified file
	 * @param objI ObjectOutputStream opened on specified file
	 * @param data Objects to be written to file
	 * @throws IOException Thrown if file is not serialised
	 */
	private static void rewrite(ObjectOutputStream objI,
				    ArrayList<Object> data) throws IOException{
		for (Object item : data) {
			objI.writeObject(item);
		}
	}

	private static boolean writeToFile(ObjectOutputStream objO,
					ArrayList<Object> data) {
		try {
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
	 * Saves a record in object form to the specified table in the database
	 * @param record The object to save to the database
	 * @param table The table to save to
	 * @return True if saved successfully, false otherwise
	 */
	public static boolean saveRecord(Object record, String table) {

		try {
			ArrayList<Object> data;
			FileInputStream fileWrite =
				new FileInputStream(DB_PATH + table + EXTENSION);

			// Check if file is empty to prevent EOFException
			if (fileWrite.available() != 0) {
				// Get table
				data =
					getTable( new FileInputStream(DB_PATH + table + EXTENSION));
			} else {
				data = new ArrayList<>();
			}

			// Add new record to list
			data.add(record);

			System.out.println("Amount of data: " + data.size());

			// Write data to file
			writeToFile(new ObjectOutputStream(
				new FileOutputStream(DB_PATH + table + EXTENSION)), data);

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

	public static boolean deleteRecord(Object record, String table) {
		try {
			FileInputStream fileWrite =
				new FileInputStream(DB_PATH + table + EXTENSION);

			// Get table and place in an array
			ArrayList<Object> tableArray = getTable(fileWrite);

			ArrayList<Object> newTable = new ArrayList<>();

			// Add all objects aside from object to delete to list
			for (Object item: tableArray) {
				if (!item.equals(record)) {
					newTable.add(item);
				}
			}

			// Clear and rewrite file using new array
			fileWrite.close();
			ObjectInputStream newObjI =
				new ObjectInputStream(new FileInputStream(DB_PATH + table + EXTENSION));



		} catch (IOException e) {
			displayIOError("deleteRecord");
		}


		return true;
	}

	public static Object searchRecord() {
		return null;
	}

	public static void main(String[] args) {
		saveRecord(new Fine(1, 20), "test");
	}
}