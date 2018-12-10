package cs230.system;

import java.io.Serializable;

/**
 * @author 908112
 *
 */

public class Laptop extends Resource implements Serializable {
		private static final long serialVersionUID = 1L;
        // The manufacturer of the Laptop
        private String manufacturer;
        // The model of the Laptop
        private String model;
        // The operating system of the Laptop
        private String operatingSystem;

        /**
         * The constructor of a new Laptop
         * @param id
         * @param title
         * @param manufacturer year
         * @param thumbnail
         * @param manufacturer
         * @param operatingSystem
         * @param model
         */
        public Laptop(String id, String title, int year, String thumbnail, String manufacturer, String model,
                        String operatingSystem) {
                super(id, title, year, thumbnail);
                this.manufacturer = manufacturer;
                this.model = model;
                this.operatingSystem = operatingSystem;
                this.type = "Laptop";
        }

        /**
         * Constructs a laptop only using its candidate keys for database searching
         * purposes
         * 
         * @param id The ID of the laptop
         */
        public Laptop(String id) {
                super(id);
        }

        /**
         * Sets the manufacturer
         * @param manufacturer the manufacturer
         */
        public void setManufacturer(String manufacturer) {
                this.manufacturer = manufacturer;
        }

        /** 
         * Sets the model
         * @param model the model
         */
        public void setModel(String model) {
                this.model = model;
        }

        /**
         * Sets the operating system
         * @param operatingSystem the os
         */
        public void setOperatingSystem(String operatingSystem) {
                this.operatingSystem = operatingSystem;
        }

        /**
         * @return The manufacturer
         */
        public String getManufacturer() {
                return this.manufacturer;
        }

        /**
         * @return The model
         */
        public String getModel() {
                return this.model;
        }

        /**
         * @return The operating system
         */
        public String getOperatingSystem() {
                return this.operatingSystem;
        }

        /**
         * Creates a new entry in the database for a new Laptop
         */
        public void create() {

                DatabaseManager.saveRecord(this, "Laptop");
        }

        /**
         * Deletes all the information associated with this Laptop from the Database
         */
        public void delete() {

                DatabaseManager.deleteRecord(this, "Laptop");
        }
        
        /**
         * Updates the Laptop in the database with its new attributes
         */
        public void update(Laptop old, Laptop newL) {
                DatabaseManager.editRecord(old, newL, "Laptop");
        }
}
