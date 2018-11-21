import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * @author 959470
 *
 */

public class Dvd extends Resource {
	//The director of the Dvd
	private String director;
	//Its Runtime
	private int runtime ;
	//The spoken language of the Dvd
	private String language;
	//The available subLanguages
	private ArrayList<String> subLanguages ;
	
	/**
	 * The constructor of a new Dvd
	 * @param id
	 * @param title
	 * @param year
	 * @param thumbnail
	 * @param director The director of the Dvd
	 * @param runtime Its Runtime
	 * @param language The spoken language of the Dvd
	 * @param subLanguages The available subLanguages
	 */
	public Dvd (int id, String title , int year, String thumbnail , String director , int runtime ,String language ,ArrayList<String> subLanguages) {
		super(id,title,year,thumbnail);
		this.director=director;
		this.runtime=runtime;
		this.language=language;
		this.subLanguages=subLanguages;
	}
	
	
	/**
	 * Sets the director
	 * @param director
	 */
	public  void setDirector (String director) {
		this.director=director;
		this.update("Director",director);
	}
	/**
	 * Sets the runtime
	 * @param runtime
	 */
	public  void setRuntime (int runtime) {
		this.runtime=runtime;
		String sRuntime = Integer.toString(this.runtime);
		this.update("Runtime",sRuntime);
	}
	/**
	 * Sets the spoken language
	 * @param language
	 */
	public  void setLanguage (String language) {
		this.language=language;
		this.update("Language",language);
	}
	/**
	 * Sets the subLanguages
	 * @param subLanguages
	 */
	public  void setSubLanguages (ArrayList<String> subLanguages) {
		this.subLanguages=subLanguages;
		this.update("SubLanguages",subLanguages.toString());
	}
	
	/**
	 * @return The director
	 */
	public  String getDirector () {
		return this.director;
	}
	/**
	 * @return The runtime
	 */
	public  int getRuntime () {
		return this.runtime;
	}
	/**
	 * @return The spoken language
	 */
	public  String getLanguage () {
		return this.language;
	}
	/**
	 * @return The list of subLanguages
	 */
	public  ArrayList<String> getSubLanguages () {
		return this.subLanguages;
	}
	
	
	
	/**
	 * Updates a specific attribute in the Dvd table of the Database
	 * @param field The field that will be updated
	 * @param attribute The new value of the attribute
	 */
	@Override
	public void update (String field , String attribute) {
		String sId = Integer.toString(this.id);
//		DatabaseManager.Update("Dvd", field,sId,attribute);
	}
	
	/** 
	 * Creates a new entry in the database for a new Dvd 
	 */
	public void create () {
		
	}
	
	/**
	 * Deletes all the information associated with this Dvd from the Database
	 */
	public void delete () {
		
	}
	
	
	

}
