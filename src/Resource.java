import java.util.LinkedList; 
import java.util.Queue; 

/**
 * @author 959470
 *
 */
public class Resource {
	//the  unique id of the resource
	protected int id ;
	//The title of the resource
	protected String title ;
	//The year the resource was published
	protected int year;
	//The file path to the thumbnail(photo) of the resource
	protected String thumbnail;
	//Number of copies of a specific resource
	protected int numCopies;
	//Number of availabale copies of a specific resource
	protected int availableCopiesNum;
	//The queue of users waiting to recieve a copy of this resource
	protected Queue<Integer> requestQ = new LinkedList<>();
	
	
	
	/**
	 * The constructor of a new Resource
	 * @param id The usnique id of the Resource
	 * @param title The title of the Resource
	 * @param year The year the resource was published
	 * @param thumbnail The file path to the thumbnail of the Resource
	 */
	public Resource (int id, String title , int year, String thumbnail ) {
		this.id= id;
		this.title= title;
		this.year=year;
		this.thumbnail=thumbnail;
		//to-do add numcopies and availablecopies
	}
	
	
	/**
	 * The constructor for a Resource that already exists in the database
	 * @param id The id of the Resource
	 */
	// public Resource (int id) {
		// String sId = Integer.toString(id);
		// String[][] Data =new String[1][6];
		// Data = DatabaseManager.Search("Resource",sId);
		// String[][] queryResult = DatabaseManager.Search("Queue",sId);
		// Queue<Integer> queue = toQueue(queryResult);
		// String title = Data[0][1];
		// String thumbnail =Data [0][2];
		// int year =  Integer.parseInt(Data [0][3]);
		// int numCopies = Integer.parseInt(Data [0][4]);
		// int availableCopiesNum = Integer.parseInt(Data [0][5]);
		// this.id= id;
		// this.title= title;
		// this.year=year;
		// this.thumbnail=thumbnail;
		// this.numCopies=numCopies;
		// this.requestQ=queue;
		// this.availableCopiesNum=availableCopiesNum;
	// }
	/**
	 * Sets the unique id for this resource
	 * @param id of the resource
	 */
	public  void setID (int id) {
		this.id=id;
		String sId = Integer.toString(this.id);
		this.update("Id",sId);
	}
	
	/**
	 * Sets the title 
	 * @param title
	 */
	public  void setTitle (String title) {
		this.title=title;
		this.update("Title",title);
	}
	/**
	 * Sets the year
	 * @param year
	 */
	public  void setYear (int year) {
		this.year=year;
		String sYear = Integer.toString(this.year);
		this.update("Year",sYear);
	}
	/**
	 * Sets the thumbnail
	 * @param thumbnail
	 */
	public  void setThumbnail (String thumbnail) {
		this.thumbnail=thumbnail;
		this.update("Thumbnail",thumbnail);
	}
	
	/**
	 * @return The id 
	 */
	public  int getID () {
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
	 * @return The thumbanil
	 */
	public  String getThumbnail () {
		return this.thumbnail;
	}
	
	/**
	 * Adds a user id 'a' to the current Resource's queue
	 * @param a
	 */
	public void addToQueue (Integer a) {
		this.requestQ.add(a);
		String sId = Integer.toString(this.id);
		String queueData[] =  {sId,a.toString()};
//		DatabaseManager.Create("Queue",queueData);
	}
	
	/**
	 * Removes the front user is from the queue
	 */
	public void removeFromQueue () {
		String sId = Integer.toString(this.id);
//		DatabaseManager.Delete("Queue",this.requestQ.peek());
		this.requestQ.remove();
		
	}
	
	/**
	 * @return The current size of the request queue
	 */
	public int checkQueue () {
		return this.requestQ.size();
	}
	
	
	/**
	 * @return The user id that is in the front of the queue
	 */
	public Integer peekQueue() {
		return this.requestQ.peek();
	}
	
	/**
	 * Takes the query result from the database and constructs the queue based on that
	 * @param queryResult The result of a query to the database, to the Queue table 
	 * @return The request queue for a resource
	 */
	public Queue<Integer> toQueue (String[][] queryResult ){
		int i=0;
		Queue<Integer> queue = new LinkedList<>();
		while (i!=queryResult.length) {
			Integer iInteger = new Integer(Integer.parseInt(queryResult[i][i+1]));
			queue.add(iInteger);
			i++;
		}
		return queue;
	}
	
	/**
	 * Updates a specific attribute in the Resource table of the Database
	 * @param field The field that will be updated
	 * @param attribute The new value of the attribute
	 */
	public void update (String field , String attribute) {
		String sId = Integer.toString(this.id);
//		DatabaseManager.Update("Resource", field,sId,attribute);
	}
	
	public void create () {
	}
	
	public void delete () {
	}
	
	
}
