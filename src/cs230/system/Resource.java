package cs230.system;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 959470
 *
 */
public class Resource implements Serializable {
        private static final long serialVersionUID = 1L;
        // the unique id of the resource
        protected String id;
        // The title of the resource
        protected String title;
        // The year the resource was published
        protected int year;
        // The file path to the thumbnail(photo) of the resource
        protected String thumbnail;
        // The type of the resource
        protected String type;
        // Number of copies of a specific resource
        protected int numCopies;
        // Number of availabale copies of a specific resource
        protected int availableCopiesNum;
        // The queue of users waiting to recieve a copy of this resource
        protected Queue<Integer> requestQ = new LinkedList<>();

        /**
         * The constructor of a new Resource
         * 
         * @param id        The usnique id of the Resource
         * @param title     The title of the Resource
         * @param year      The year the resource was published
         * @param thumbnail The file path to the thumbnail of the Resource
         */
        public Resource(String id, String title, int year, String thumbnail) {
                this.id = id;
                this.title = title;
                this.year = year;
                this.thumbnail = thumbnail;
                this.numCopies = 0;
                this.availableCopiesNum = 0;
        }

        /**
         * Minimal constructor only taking candidate key for database searching
         * 
         * @param id The ID of the resource
         */
        public Resource(String id) {
                this.id = id;
        }

        /**
         * Sets the unique id for this resource
         * 
         * @param id of the resource
         */
        public void setID(String id) {
                this.id = id;
        }

        /**
         * Sets the title
         * 
         * @param title
         */
        public void setTitle(String title) {
                Resource old = this;
                this.title = title;
                Resource newR = this;
                this.update(old, newR);
        }

        /**
         * Sets the year
         * 
         * @param year
         */
        public void setYear(int year) {
                Resource old = this;
                this.year = year;
                Resource newR = this;
                this.update(old, newR);
        }

        /**
         * Sets the thumbnail
         * 
         * @param thumbnail filepath to the thumbnail
         */
        public void setThumbnail(String thumbnail) {
                Resource old = this;
                this.thumbnail = thumbnail;
                Resource newR = this;
                this.update(old, newR);
        }

        /**
         * @return The id
         */
        public String getID() {
                return this.id;
        }

        /**
         * @return The title
         */
        public String getTitle() {
                return this.title;
        }

        /**
         * @return The year
         */
        public int getYear() {
                return this.year;
        }

        /**
         * @return The number of copies for that resource
         */
        public int getNumCopies() {
                return this.numCopies;
        }

        /**
         * Increments the number of copies by 1
         */
        public void incNumCopies() {
                this.numCopies++;
        }

        /**
         * Decrements the number of copies by 1
         */
        public void decNumCopies() {
                this.numCopies--;
        }

        /**
         * @return The number of available copies for that resource
         */
        public int getAvailableNumCopies() {
                return this.availableCopiesNum;
        }

        /**
         * Increments the number of available copies by 1
         */
        public void incAvailableNumCopies() {
                this.availableCopiesNum++;
        }

        /**
         * Decrements the number of available copies by 1
         */
        public void decAvailableNumCopies() {
                this.availableCopiesNum--;
        }

        /**
         * @return The thumbanil
         */
        public String getThumbnail() {
                return this.thumbnail;
        }

        /**
         * @return The type of the Resource(Book,Dvd or Laptop)
         */
        public String getType() {
                return this.type;
        }

        /**
         * Adds a user id 'a' to the current Resource's queue
         * 
         * @param a
         */
        public void addToQueue(Integer a) {
                Resource old = this;
                this.requestQ.add(a);
                Resource newR = this;
                this.update(old, newR);
        }

        /**
         * Removes the front user is from the queue
         */
        public void removeFromQueue() {
                Resource old = this;
                this.requestQ.remove();
                Resource newR = this;
                this.update(old, newR);
        }

        /**
         * @return The current size of the request queue
         */
        public int checkQueue() {
                return this.requestQ.size();
        }

        /**
         * @return The user id that is in the front of the queue
         */
        public Integer peekQueue() {
                return this.requestQ.peek();
        }

        /**
         * Takes the query result from the database and constructs the queue based on
         * that
         * 
         * @param queryResult The result of a query to the database, to the Queue table
         * @return The request queue for a resource
         */
        public Queue<Integer> toQueue(String[][] queryResult) {
                int i = 0;
                Queue<Integer> queue = new LinkedList<>();
                while (i != queryResult.length) {
                        Integer iInteger = new Integer(Integer.parseInt(queryResult[i][i + 1]));
                        queue.add(iInteger);
                        i++;
                }
                return queue;
        }

        /**
         * Updates a specific attribute in the Resource table of the Database
         */
        public void update(Resource old, Resource newR) {
                DatabaseManager.editRecord(old, newR, "Resource");
        }


        /**
         * Returns the string value of this resource 
         */
        public String toString() {
                return this.title;
        }

}
