package Resources;
 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.Queue;
 /**
  * @author 908112
  *
  */
 
 public class Laptop extends Resource {
 	//The manufacturer of the Laptop
 	private String manufacturer;
 	//The model of the Laptop
 	private String model ;
 	//The operating system of the Laptop
 	private String operatingSystem ;
 	
 	/**
 	 * The constructor of a new Laptop
 	 * @param id
 	 * @param title
 	 * @param manufacture year
 	 * @param thumbnail
 	 * @param manufacturer
 	 * @param operating system
 	 * @param model
 	 */
 	public Laptop (int id, String title , int year, String thumbnail, String manufacturer , String model, String operatingSystem ) {
 		super(id,title,year,thumbnail);
 		this.manufacturer=manufacturer;
 		this.model=model;
 		this.operatingSystem=operatingSystem;
 		this.type = "Laptop";
// 		create();
		
	}
 	
 	
 	
 	/**
	 * Sets the manufacturer
 	 * @param manufacturer
 	 */
 	public  void setManufacturer (String manufcaturer) {
 		this.manufacturer=manufacturer;
 	}
 	/**
 	 */
 	
 	/**
	 * Sets the publisher
 	 * @param publisher
 	 */
 	public  void setModel (String model) {
 		this.model=model;
 	}
 	/**
 	 */
 	
 	/**
 	 * Sets the operating system
 	 * @param operatingSystem
 	 */
 	public  void setOperatingSystem (String operatingSysytem) {
 		this.operatingSystem=operatingSystem;
 	}
 	
 	
 	/**
 	 * @return The manufacturer
 	 */
 	public  String getManufacturer () {
 		return this.manufacturer;
 	}
 	
 	/**
 	 * @return The model
 	 */
 	public  String getModel () {
 		return this.model;
 	}
 	
 	/**
 	 * @return The operating system
 	 */
 	public  String getOperatingSystem () {
 		return this.operatingSystem;
 	}
 	
 	/**
 	 * Updates a specific attribute in the Laptop table of the Database
 	 * @param field The field that will be updated
 	 * @param attribute The new value of the attribute
 	 */
 	@Override
 	public void update () {

 	}
 	
 	
 	/** 
	 * Creates a new entry in the database for a new Laptop 
	 */
	public void create () {
		
		DatabaseManager.saveRecord(this,"Laptop");
	}
 	
	/**
	 * Deletes all the information associated with this Laptop from the Database
	 */
	public void delete () {
		
		DatabaseManager.deleteRecord(this,"Laptop");
	}
 	
	
 }
 	
 	
 	
 	
 	