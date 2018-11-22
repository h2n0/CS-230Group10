import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DatabaseManager {
	
	/**
	 * Displays the file not found error message using a JOptionPane
	 */
	private static void displayFileError() {
		JOptionPane.showMessageDialog(null, "File not found, check "
				+ "file path and try again", 
				"File not found", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Takes the record and parses it to CSV format
	 * @param record The record to parse
	 * @return Parsed record
	 */
	private static String compileRecord(String[] record) {
		String compiledRecord;
		compiledRecord = record[0] + ",";
		
		for (int i = 1 ; i < record.length - 1 ; i++) {
			compiledRecord += record[i] + ",";
		}
		
		compiledRecord += record[record.length - 1];
		
		return compiledRecord;
	}
	
	/**
	 * Saves a record to the specified table in the database
	 * @param table Name of the table to save to
	 * @param record The record to place in the table
	 */
	public static void saveRecord(String table, String[] record) {
		try {
			// Variable to store parsed record
			String data;
			
			PrintWriter fileWrite = new PrintWriter(table + ".dat");
			
			// Compile and write record
			data = compileRecord(record);
			fileWrite.println(data);

			// Shut the writer
			fileWrite.close();
		} catch (FileNotFoundException e) {
			// If the file is not found, display an error pane
			displayFileError();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		saveRecord("Test", new String[]{"Joe", "Wilkins"});
	}
}