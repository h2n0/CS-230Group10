import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
	private static final String DB_PATH = "//Database//";
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
	
	private static ArrayList<Object> getTable(String table) {
		try {
			FileOutputStream fileWrite =
					new FileOutputStream(DB_PATH + table + ".dat");
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
			FileOutputStream fileWrite = 
					new FileOutputStream(DB_PATH + table + ".dat");
			ObjectOutputStream objO =
					new ObjectOutputStream(fileWrite);
			
			objO.writeObject(record);

			// Shut the writers
			objO.close();
			fileWrite.close();
			
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
			
			
			
		}
		
		
		return true;
	}
	
	public static String searchRecord() {
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
	}
}