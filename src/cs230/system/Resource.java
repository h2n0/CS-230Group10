package cs230.system;


import java.io.Serializable;
import java.util.LinkedList; 
import java.util.Queue;



/**
 * @author 959470
 * This class is an abstract class. It is the masterclass of the common attributes of all the types of Resources stored in the library.  
 */
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;
	//the  unique id of the resource
	protected String id ;
	//The title of the resource
	protected String title ;
	//The year the resource was published
	protected int year;
	//The file path to the thumbnail(photo) of the resource
	protected String thumbnail;
	//The type of the resource
	protected String type;
	//Number of copies of a specific resource
	protected int numCopies;
	//Number of availabale copies of a specific resource
	protected int availableCopiesNum;
	//The queue of users waiting to recieve a copy of this resource
	protected Queue<String> requestQ = new LinkedList<>();
	
	
	
	/**
	 * The constructor of a new Resource
	 * @param id The usnique id of the Resource
	 * @param title The title of the Resource
	 * @param year The year the resource was published
	 * @param thumbnail The file path to the thumbnail of the Resource
	 */
	public Resource (String id, String title , int year,
			 String thumbnail ) {
		this.id= id;
		this.title= title;
		this.year=year;
		this.thumbnail=thumbnail;
		this.numCopies = 0;
		this.availableCopiesNum = 0;
	}

	/**
	 * Minimal constructor only taking candidate key for database searching
	 * @param id The ID of the resource
	 */
	public Resource(String id) {
		this.id = id;
	}
	
	
	/**
	 * Sets the unique id for this resource
	 * @param id of the resource
	 */
	public  void setID (String id) {
		this.id=id;
	}
	
	/**
	 * Sets the title 
	 * @param title
	 */
	public  void setTitle (String title) {
		this.title=title;
		this.update();
	}
	/**
	 * Sets the year
	 * @param year
	 */
	public  void setYear (int year) {
		this.year=year;
		this.update();
	}
	/**
	 * Sets the thumbnail
	 * @param thumbnail filepath to the thumbnail
	 */
	public  void setThumbnail (String thumbnail) {
		this.thumbnail=thumbnail;
		this.update();
	}
	
	/**
	 * @return The id 
	 */
	public  String getID () {
		return this.id;
	}
	/**
	 * @return The title
	 */
	public  String getTitle () {
		return this.title;
	}
	/**
	 * @return The year
	 */
	public  int getYear () {
		return this.year;
	}
	
	/**
	 * @return The number of copies for that resource
	 */
	public  int getNumCopies () {
		return this.numCopies;
	}
	
	/**
	 * Increments the number of copies by 1
	 */
	public void incNumCopies() {
		this.numCopies ++;	
	}
	
	/**
	 * Decrements the number of copies by 1
	 */
	public void decNumCopies() {
		this.numCopies --;	
	}
	
	
	
	/**
	 * @return The number of available copies for that resource
	 */
	public  int getAvailableNumCopies () {
		return this.availableCopiesNum;
	}
	
	/**
	 * Increments the number of available copies by 1
	 */
	public void incAvailableNumCopies() {
		this.availableCopiesNum ++;	
	}
	
	/**
	 * Decrements the number of available copies by 1
	 */
	public void decAvailableNumCopies() {
		this.availableCopiesNum --;	
	}
	
	/**
	 * @return The thumbanil
	 */
	public  String getThumbnail () {
		return this.thumbnail;
	}
	/**
	 * @return The type of the Resource(Book,Dvd or Laptop)
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Adds a username 'a' to the current Resource's queue
	 * @param a
	 */
	public void addToQueue (String a) {
		this.requestQ.add(a);
		this.update();
	}
	
	/**
	 * Removes the front user is from the queue
	 */
	public void removeFromQueue () {
		this.requestQ.remove();
		this.update();
	}
	
	/**
	 * @return The current size of the request queue
	 */
	public int checkQueue () {
		return this.requestQ.size();
	}
	
	
	/**
	 * @return The username that is in the front of the queue
	 */
	public String peekQueue() {
		return this.requestQ.peek();
	}
	

	/**
	 * Updates a specific attribute in the Resource table of the Database
	 */
	public void update () {
		DatabaseManager.editRecord(this.id,this,type);
	}


	public void create () {
	}
	
	public void delete () {
	}
	
	public String toString() {
		return this.title;
	}
	
	
	
}
