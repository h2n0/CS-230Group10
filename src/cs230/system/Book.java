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
 	public Book (int id, String title , int year, String thumbnail , String author , String publisher, String genre, String ISBN ,String language ) {
 		super(id,title,year,thumbnail);
 		this.author=author;
 		this.publisher=publisher;
 		this.language=language;
 		this.genre=genre;
 		this.ISBN=ISBN;
 		this.type = "Book";
 //		create();
		
	}
	 	
 	/**
	 * Sets the author
 	 * @param author
 	 */
 	public  void setAuthor (String author) {
 		this.author=author;
 	}
 	/**
 	 */
 	
 	/**
	 * Sets the publisher
 	 * @param publisher
 	 */
 	public  void setPublisher (String publisher) {
 		this.publisher=publisher;
 	}
 	/**
 	 */
 	
 	/**
 	 * Sets the spoken language
 	 * @param language
 	 */
 	public  void setLanguage (String language) {
 		this.language=language;
 	}
 	
 	/**
 	 * Sets the genre
 	 * @param genre
 	 */
 	public  void setGenre (String genre) {
 		this.genre=genre;
 	}
 	
 	/**
 	 * Sets the ISBN
 	 * @param ISBN
 	 */
 	public  void setISBN (String ISBN) {
 		this.ISBN=ISBN;
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
 	public void update () {
 	
 	}
 	
 	/** 
	 * Creates a new entry in the database for a new Book 
	 */
//	public void create () {
		
//		DatabaseManagaer.saveRecord(this,"Book");
//	}
 	
	/**
	 * Deletes all the information associated with this Book from the Database
	 */
//	public void delete () {
		
//	DatabaseManager.deleteRecord(this,"Book");
//	}
 	
	
 }
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	