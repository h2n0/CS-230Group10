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
		
	}
	/**
	 * The constructor of a Laptop that already exists in the Database
	 * @param id The id of the Resource
	 */
	// public Laptop (int id) {
		// super(id);
		// String sId = Integer.toString(id);
		// String[][] Data =new String[1][5];
		// Data = DatabaseManager.Search("Laptop",sId);
		// String manufacturer = Data [0][1];
		// String model = Data [0][1];
 		// String operatingSystem = operatingSystem [0][1];
		// this.manufacturer=manufacturer;
		// this.model=model;
 		// this.operatingSystem=operatingSystem;
	
	// }
 	
 	/**
	 * Sets the manufacturer
 	 * @param manufacturer
 	 */
 	public  void setManufacturer (String manufcaturer) {
 		this.manufacturer=manufacturer;
 		this.update("Manufacturer",manufacturer);
 	}
 	/**
 	 */
 	
 	/**
	 * Sets the publisher
 	 * @param publisher
 	 */
 	public  void setModel (String model) {
 		this.model=model;
 		this.update("Model",model);
 	}
 	/**
 	 */
 	
 	/**
 	 * Sets the operating system
 	 * @param operatingSystem
 	 */
 	public  void setOperatingSystem (String operatingSysytem) {
 		this.operatingSystem=operatingSystem;
 		this.update("Operating System",operatingSystem);
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
 	public void update (String field , String attribute) {
 		String sId = Integer.toString(this.id);
 //		DatabaseManager.Update("Laptop", field,sId,attribute);
 	}
 	
 	
 	/** 
	 * Creates a new entry in the database for a new Laptop 
	 */
	public void create () {
		
		String sId = Integer.toString(this.id);
		String sYear = Integer.toString(this.year);
		String sNumCopies = Integer.toString(this.numCopies);
		String sAvailableCopies = Integer.toString(this.availableCopiesNum);
		String laptopData[] = {this.manufacturer,this.model,this.operatingSystem};
		String resourceData[] = {sId,this.title,sYear,this.thumbnail,sNumCopies,sAvailableCopies};
//		DatabaseManager.Create("Laptop",laptopData);
//		DatabaseManager.Create("Resource",resourceData);
		Queue<Integer> queue = this.requestQ;
		while (!queue.isEmpty()) {
			String queueData[] =  {sId,queue.peek().toString()};
//			DatabaseManager.Create("Queue",queueData);
			queue.remove();
		}
	}
 	
	/**
	 * Deletes all the information associated with this Laptop from the Database
	 */
	public void delete () {
		
		String sId = Integer.toString(this.id);
//		DatabaseManager.Delete("Resource",sId);
//		DatabaseManager.Delete("Laptop",sId);
//		DatabaseManager.Delete("Queue",sId);
	}
 	
	
 }
 	
 	
 	
 	
 	