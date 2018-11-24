 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.Queue;
 /**
  * @author 908112
  *
  */
 
 public class Book extends Resource {
 	//The director of the Book
 	private String author;
 	//The publisher of the Book
 	private String publisher ;
 	//The spoken language of the Book
 	private String language;
 	//The ISBN of the book
 	private String ISBN;
 	//The genre of the book
 	private String genre;
 	
 	/**
 	 * The constructor of a new Book
 	 * @param id
 	 * @param title
 	 * @param year
 	 * @param thumbnail
 	 * @param ISBN
 	 * @param genre
 	 * @param author The author of the Book
 	 * @param language The spoken language of the Book
 	 */
 	public Book (int id, String title , int year, String thumbnail , String author , String publisher ,String language ) {
 		super(id,title,year,thumbnail);
 		this.author=author;
 		this.publisher=publisher;
 		this.language=language;
 		this.genre=genre;
 		this.ISBN=ISBN;
		
	}
	/**
	 * The constructor of a Book that already exists in the Database
	 * @param id The id of the Resource
	 */
	// public Book (int id) {
		// super(id);
		// String sId = Integer.toString(id);
		// String[][] Data =new String[1][5];
		// Data = DatabaseManager.Search("Book",sId);
		// String author = Data [0][1];
		// String publisher = Data [0][1];
		// String language = Data [0] [3];
		// this.author=author;
		// this.publisher=publisher;
		// this.language=language;
	
		
	// }
 	
 	/**
	 * Sets the author
 	 * @param author
 	 */
 	public  void setAuthor (String author) {
 		this.author=author;
 		this.update("Author",author);
 	}
 	/**
 	 */
 	
 	/**
	 * Sets the publisher
 	 * @param publisher
 	 */
 	public  void setPublisher (String publisher) {
 		this.publisher=publisher;
 		this.update("Publisher",publisher);
 	}
 	/**
 	 */
 	
 	/**
 	 * Sets the spoken language
 	 * @param language
 	 */
 	public  void setLanguage (String language) {
 		this.language=language;
 		this.update("Language",language);
 	}
 	
 	/**
 	 * Sets the genre
 	 * @param genre
 	 */
 	public  void setGenre (String genre) {
 		this.genre=genre;
 		this.update("Genre",genre);
 	}
 	
 	/**
 	 * Sets the ISBN
 	 * @param ISBN
 	 */
 	public  void setISBN (String ISBN) {
 		this.ISBN=ISBN;
 		this.update("ISBN",ISBN);
 	}
 	


	/**
 	 * @return The author
 	 */
 	public  String getAuthor () {
 		return this.author;
 	}
 	
 	/**
 	 * @return The publisher
 	 */
 	public  String getPublisher () {
 		return this.publisher;
 	}
 	
 	/**
 	 * @return The spoken language
 	 */
 	public  String getLanguage () {
 		return this.language;
 	}
 	
 	/**
 	 * @return The Genre
 	 */
 	public  String getGenre () {
 		return this.genre;
 	}
 	
 	/**
 	 * @return ISBN
 	 */
 	public  String getISBN () {
 		return this.ISBN;
 	}
 	
 	/**
 	 * Updates a specific attribute in the Book table of the Database
 	 * @param field The field that will be updated
 	 * @param attribute The new value of the attribute
 	 */
 	@Override
 	public void update (String field , String attribute) {
 		String sId = Integer.toString(this.id);
 //		DatabaseManager.Update("Book", field,sId,attribute);
 	}
 	
 	
 	/** 
	 * Creates a new entry in the database for a new Book 
	 */
	public void create () {
		
		String sId = Integer.toString(this.id);
		String sYear = Integer.toString(this.year);
		String sNumCopies = Integer.toString(this.numCopies);
		String sAvailableCopies = Integer.toString(this.availableCopiesNum);
		String bookData[] = {this.author,this.publisher,this.language, this.genre,this.ISBN};
		String resourceData[] = {sId,this.title,sYear,this.thumbnail,sNumCopies,sAvailableCopies};
//		DatabaseManager.Create("Book",bookData);
//		DatabaseManager.Create("Resource",resourceData);
		Queue<Integer> queue = this.requestQ;
		while (!queue.isEmpty()) {
			String queueData[] =  {sId,queue.peek().toString()};
//			DatabaseManager.Create("Queue",queueData);
			queue.remove();
		}
	}
 	
	/**
	 * Deletes all the information associated with this Book from the Database
	 */
	public void delete () {
		
		String sId = Integer.toString(this.id);
//		DatabaseManager.Delete("Resource",sId);
//		DatabaseManager.Delete("Book",sId);
//		DatabaseManager.Delete("Queue",sId);
	}
 	
	
 }
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	