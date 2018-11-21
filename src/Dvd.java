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
	 * The constructor of a Dvd that already exists in the Database
	 * @param id The id of the Resource
	 */
	// public Dvd (int id) {
		// super(id);
		// String sId = Integer.toString(id);
		// String[][] Data =new String[1][5];
		// Data = DatabaseManager.Search("Dvd",sId);
		// String director = Data [0][1];
		// int runtime = Integer.parseInt(Data [0][2]);
		// String language = Data [0] [3];
		// ArrayList<String> subLanguages = toArray(Data [0][4]);
		// this.director=director;
		// this.runtime=runtime;
		// this.language=language;
		// this.subLanguages=subLanguages;
		
	// }
	
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
		String sId = Integer.toString(this.id);
		String sYear = Integer.toString(this.year);
		String sNumCopies = Integer.toString(this.numCopies);
		String sAvailableCopies = Integer.toString(this.availableCopiesNum);
		String sRuntime = Integer.toString(this.runtime);
		String dvdData[] = {this.director,sRuntime,this.language,this.subLanguages.toString()};
		String resourceData[] = {sId,this.title,sYear,this.thumbnail,sNumCopies,sAvailableCopies};
//		DatabaseManager.Create("Dvd",dvdData);
//		DatabaseManager.Create("Resource",resourceData);
		Queue<Integer> queue = this.requestQ;
		while (!queue.isEmpty()) {
			String queueData[] =  {sId,queue.peek().toString()};
//			DatabaseManager.Create("Queue",queueData);
			queue.remove();
		}
	}
	
	/**
	 * Deletes all the information associated with this Dvd from the Database
	 */
	public void delete () {
		String sId = Integer.toString(this.id);
//		DatabaseManager.Delete("Resource",sId);
//		DatabaseManager.Delete("Dvd",sId);
//		DatabaseManager.Delete("Queue",sId);
	}
	
	
	/**
	 * Takes the string representation of an ArrayList generated by the default toString() method and constructs the ArrayList
	 * @param x the string representation of the arraylist generated by the default toString() method
	 * @return the constructed ArrayList<String>
	 */
	public  ArrayList<String> toArray (String x){
		x=x.replaceAll("\\s+","");
		x=x.substring(1, x.length()-1);
		String[] subArray = x.split(",");
		ArrayList<String> subLang = new ArrayList<String>(); 
		int i=0;
		
		while ( i != (subArray.length)) {
			subLang.add(subArray[i]);
			i++;
		}
		return subLang;
	}

}
