package cs230.system;

 /**
  * This class models the attributes and functions of a book
  * @author 908112
  */
public class Book extends Resource {
	//The director of the Book
 	private String author;
 	//The publisher of the Book
 	private String publisher ;
 	//The spoken language of the Book
 	private String language;
 	//The isbn of the book
 	private String isbn;
 	//The genre of the book
 	private String genre;
 	
 	/**
 	 * The constructor of a new Book
 	 * @param ID The resourceID of the book
 	 * @param title The title of the book
 	 * @param year The year the book was published
 	 * @param thumbnail The cover of the book
 	 * @param isbn The isbn number of the book
 	 * @param genre The genre of the book
 	 * @param author The author of the Book
 	 * @param language The spoken language of the Book
 	 */
 	public Book (String ID, String title , int year, String thumbnail ,
		     String author , String publisher, String genre, String isbn ,String language ) {
 		super(ID,title,year,thumbnail);
 		this.author = author;
 		this.publisher = publisher;
 		this.language = language;
 		this.genre = genre;
 		this.isbn = isbn;
		this.type = "book";
	}

	/**
	 * Constructs a book using only its candidate keys for database
	 * searching purposes
	 * @param ID The ID of the book
	 *
	 */
	public Book(String ID) {
 		super(ID);
	}
	 	
 	/**
	 * Sets the author
 	 * @param author The new author of the book
 	 */
 	public  void setAuthor (String author) {
 		this.author = author;
 	}
 	/**
 	 */

 	/**
	 * Sets the publisher
 	 * @param publisher The new publisher of the book
 	 */
 	public  void setPublisher (String publisher) {
 		this.publisher = publisher;
 	}
 	/**
 	 */
 	
 	/**
 	 * Sets the spoken language
 	 * @param language The new language of the book
 	 */
 	public  void setLanguage (String language) {
 		this.language = language;
 	}
 	
 	/**
 	 * Sets the genre
 	 * @param genre The new genre of the book
 	 */
 	public  void setGenre (String genre) {
 		this.genre = genre;
 	}

 	/**
 	 * Sets the isbn
 	 * @param isbn The new isbn no. of the book
 	 */
 	public  void setIsbn(String isbn) {
 		this.isbn = isbn;
 	}

	/**
	 * Gets the author of the book
 	 * @return The author of the book
 	 */
 	public  String getAuthor () {
 		return author;
 	}

 	/**
	 * Gets the publisher of the book
 	 * @return The publisher of the book
 	 */
 	public  String getPublisher () {
 		return publisher;
 	}
 	
 	/**
	 * Gets the language of the book
 	 * @return The spoken language of the book
 	 */
 	public  String getLanguage () {
 		return language;
 	}
 	
 	/**
	 * Gets the genre of the book
 	 * @return The Genre of the book
 	 */
 	public  String getGenre () {
 		return genre;
 	}
 	
 	/**
	 * Gets the isbn no. of the book
 	 * @return The isbn no. of the book
 	 */
 	public  String getIsbn() {
 		return isbn;
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
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
