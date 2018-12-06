package cs230.system;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class models a DVD in the library
 * @author 959470
 */
public class Dvd extends Resource implements Serializable {
	private static final long serialVersionUID = 1L;
	//The director of the Dvd
	private String director;
	//Its Runtime
	private int runtime ;
	//The spoken language of the Dvd
	private String language;
	//The available subtitle languages
	private ArrayList<String> subLanguages ;
	
	/**
	 * Constructs a DVD
	 * @param id The resource ID of the DVD
	 * @param title The title of the DVD
	 * @param year The year the DVD was released
	 * @param thumbnail The DVD's cover
	 * @param director The director of the Dvd
	 * @param runtime The DVD's Runtime
	 * @param language The spoken language of the Dvd
	 * @param subLanguages The available subtitle languages
	 */
	public Dvd (String id, String title , int year, String thumbnail ,
		    String director , int runtime ,String language ,ArrayList<String> subLanguages) {
		super(id,title,year,thumbnail);
		this.director=director;
		this.runtime=runtime;
		this.language=language;
		this.subLanguages=subLanguages;
		this.type = "Dvd";
		create();
	}

	/**
	 * Constructs a Dvd using only the candidate key(s) to search the
	 * database
	 * @param id ID of the dvd
	 */
	public Dvd(String id) {
		super(id);
	}

	/**
	 * Sets the director
	 * @param director The new director
	 */
	public  void setDirector (String director) {
		this.director=director;
		this.update();
	}

	/**
	 * Sets the runtime
	 * @param runtime The new runtime
	 */
	public  void setRuntime (int runtime) {
		this.runtime=runtime;
		this.update();
	}

	/**
	 * Sets the spoken language
	 * @param language The new language
	 */
	public  void setLanguage (String language) {
		this.language=language;
		this.update();
	}

	/**
	 * Sets the subLanguages
	 * @param subLanguages The new subtitles
	 */
	public  void setSubLanguages (ArrayList<String> subLanguages) {
		this.subLanguages=subLanguages;
		this.update();
	}
	
	/**
	 * Gets the director of the film
	 * @return The director
	 */
	public  String getDirector () {
		return this.director;
	}

	/**
	 * Gets the runtime of the film
	 * @return The runtime
	 */
	public  int getRuntime () {
		return this.runtime;
	}

	/**
	 * Gets the language of the film
	 * @return The spoken language
	 */
	public  String getLanguage () {
		return this.language;
	}

	/**
	 * Gets the list of subittle languages available
	 * @return The list of subLanguages
	 */
	public  ArrayList<String> getSubLanguages () {
		return this.subLanguages;
	}

	/**
	 * Updates the DVD in the database with its new attributes
	 */
	@Override
	public void update () {
		DatabaseManager.editRecord(this.getID(),this,"Dvd");
	}
	
	/** 
	 * Creates a new entry in the database for a new Dvd 
	 */
	public void create () {
		DatabaseManager.saveRecord(this,"Dvd");
		DatabaseManager.saveRecord(this.id,"resourceID");
	
	}
	
	/**
	 * Deletes all the information associated with this Dvd from the Database
	 */
	public void delete () {
		DatabaseManager.deleteRecord(this,"Dvd");
		DatabaseManager.deleteRecord(this.id,"ResourceIDs");
	}
	
	/** 
	 * Compares an object to this using the candidate keys to only return
	 * if the object is an exact match to this
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		Dvd dvd = (Dvd) obj;

		if (this.director.equalsIgnoreCase(dvd.director)) {
			return true;
		}
		
		if (this.title.equalsIgnoreCase(dvd.title)) {
			return true;
		}

		if (this.id.equals(dvd.id)) {
			return true;
		}

		if (this.year == dvd.year) {
			return true;
		}
		
		if (this.runtime == dvd.runtime) {
			return true;
		}

		if (this.language.equalsIgnoreCase(dvd.language)) {
			return true;
		}
		
		for (String subtitle1 : this.subLanguages) {
		    for (String subtitle2 : dvd.subLanguages) {
		    	if (subtitle1.equalsIgnoreCase(subtitle2)){
		    		return true;
		    	}
		    }
		}

		return false;
	}
	

}