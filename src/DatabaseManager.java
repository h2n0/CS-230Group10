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
	private static void displayIOError() {
		JOptionPane.showMessageDialog(null, "Error while writing to file, try again",
				"I/O Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Gets all records from a specified table
	 * @param ObjI Object input stream open on desired table
	 * @return Table in the form of an array list
	 */
	private static ArrayList<Object> getTable(ObjectInputStream ObjI) {
                ArrayList<Object> output = new ArrayList<>();
                Object record;

		try {
                        while ((record = ObjI.readObject()) != null) {
				output.add(record);
                        }

                        return output;

		} catch (IOException e) {
		        displayIOError();
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
	private void rewrite(ObjectOutputStream objI, ArrayList<Object> data) throws IOException{
		for (Object item : data) {
			objI.writeObject(item);
		}
	}

	/**
	 * Saves a record in object form to the specified table in the database
	 * @param record The object to save to the database
	 * @param table The table to save to
	 * @return True if saved successfully, false otherwise
	 */
	public static boolean saveRecord(Object record, String table) {
		ArrayList<Object> data = new ArrayList<>();

		try {
			// Get table
			data = getTable(new ObjectInputStream(new FileInputStream(DB_PATH + table + "")));
			FileOutputStream fileWrite = 
					new FileOutputStream(DB_PATH +
                                                table + ".dat");
			ObjectOutputStream objO =
					new ObjectOutputStream(fileWrite);

			// Write object then close writer
			objO.writeObject(record);
			objO.flush();
			objO.close();
			
			return true;
		} catch (FileNotFoundException e) {
			// If the file is not found, display an error pane
			displayFileError();
			return false;
		} catch (IOException e) {
			// If there is an error writing to file, display an error
			displayIOError();
			return false;
		}
	}
	
	public static boolean deleteRecord(Object record, String table) {
		try {
			FileInputStream fileWrite =
				new FileInputStream(DB_PATH + table + EXTENSION);
			ObjectInputStream objI =
				new ObjectInputStream(fileWrite);

			// Get table and place in an array
			ArrayList<Object> tableArray = getTable(objI);

			ArrayList<Object> newTable = new ArrayList<>();

			// Add all objects aside from object to delete to list
			for (Object item: tableArray) {
				if (!item.equals(record)) {
					newTable.add(item);
				}
			}

			// Clear and rewrite file using new array
			fileWrite.close();
			FileInputStream newWrite =
				new FileInputStream(DB_PATH + table + EXTENSION);
			ObjectInputStream newObjI =
				new ObjectInputStream(fileWrite);

			
		} catch (IOException e) {
			displayIOError();
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