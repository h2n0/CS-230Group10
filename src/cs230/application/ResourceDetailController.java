package cs230.application;

import cs230.system.*;
import cs230.system.Copy.Status;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * This class controls the resource detail page.
 * @author 901306
 * @version 1.1
 */
public class ResourceDetailController {
        //max amount of fines on an overdue book or dvd
        private final int MAX_BOOK_DVD_FINE= 25;
        //max amount of fines on an overdue laptop
        private final int MAX_LAPTOP_FINE= 100;
        //daily fine for a dvd/book 
        private final int DAILY_BOOK_DVD_FINE = 2;
        //daily fine for a laptop
        private final int DAILY_LAPTOP_FINE = 10;

        // The edit button shown for librarians
        @FXML
        private Button editButton;

        // The save button shown after the edit button is pressed
        @FXML
        private Button saveButton;

        // The delete button shown to librarians to delete the resource
        @FXML
        private Button deleteButton;

        // The button to save a loan
        @FXML
        private Button saveLoanButton;

        // The button to cancel the loan
        @FXML
        private Button cancelLoanButton;

        // The button to change the thumbnail of the resource
        @FXML
        private Button changeThumbnailButton;

        // The loan book button
        @FXML
        private Button loanButton;
        
     // The new copy button
        @FXML
        private Button newCopyButton;
        
        // The return button
        @FXML
        private Button returnButton;

        // The return cancel button
        @FXML
        private Button returnCancelButton;

        // The return cancel button
        @FXML
        private Button returnShowButton;

        // The table showing copies
        @FXML
        private TableView copyTable;
        
        @FXML
        private VBox reserveOptions;
        
        @FXML
        private Label userCannotLoanLabel;

        @FXML
        TableColumn<String, String> subLanguagesColumn;

        @FXML
        private TableColumn<Copy, String> copyIdColumn;

        @FXML
        private TableColumn<Copy, String> copyDurationColumn;

        @FXML
        private TableColumn<Copy, Button> moreInfoColumn;
        
        @FXML
        private TableColumn<Copy, Button> editColumn;

        @FXML
        private VBox showLoanCreate;

        @FXML
        private TextField userLoanTextBox;

        @FXML
        private Label userNotFoundLoanLabel;

        @FXML
        private VBox showReturnBox;

        @FXML
        private TextField returnUsernameTextBox;

        @FXML
        private TextField returnCopyIdTextBox;

        @FXML
        private Label userNotFoundReturnLabel;

        @FXML
        private Label noLoanFoundLabel;

        @FXML
        private Label resourceID;

        @FXML
        private Label titleLabel;

        @FXML
        private TextField titleTextBox;

        @FXML
        private Label yearLabel;

        @FXML
        private TextField yearTextBox;

        @FXML
        private HBox imageBox;

        @FXML
        private ImageView thumbnailShow;

        @FXML
        private Label numOfCopiesLabel;

        @FXML
        private GridPane bookGrid;

        @FXML
        private Label authorLabel;

        @FXML
        private TextField authorTextBox;

        @FXML
        private Label publisherLabel;

        @FXML
        private TextField publisherTextBox;

        @FXML
        private Label genreLabel;

        @FXML
        private TextField genreTextBox;

        @FXML
        private Label isbnLabel;

        @FXML
        private TextField isbnTextBox;

        @FXML
        private Label bookLanguageLabel;

        @FXML
        private TextField bookLanguageTextBox;

        @FXML
        private GridPane dvdGrid;

        @FXML
        private Label directorLabel;

        @FXML
        private TextField directorTextBox;

        @FXML
        private Label runtimeLabel;

        @FXML
        private TextField runtimeTextBox;

        @FXML
        private Label dvdLanguageLabel;

        @FXML
        private TextField dvdLanguageTextBox;

        @FXML
        private GridPane laptopGrid;

        @FXML
        private Label manufacturerLabel;

        @FXML
        private TextField manufacturerTextBox;

        @FXML
        private Label modelLabel;

        @FXML
        private TextField modelTextBox;

        @FXML
        private Label osLabel;

        @FXML
        private TextField osTextBox;

        @FXML
        private HBox deleteSaveBox;

        @FXML
        private Label incorrectFieldLabel;
        
        @FXML
        private TableView subtitleLabelTable;

        private Resource originalResource;

        private Dvd showedDvdResource;
        
        private Laptop showedLaptopResource;
        
        private Book showedBookResource;

        private ArrayList<Copy> resourceCopies;

        private Boolean isBook = false;

        private Boolean isDvd = false;

        private Boolean isLaptop = false;

        private String shownResourceId;

        private String newThumbnailPath = "";
        
        private String loanUsername = "";

        /**
         * The Constructor of the controller
         */
        public ResourceDetailController() {
                //Sets the info to show
                
                if (isDvd) {
                        dvdInfoEdit();
                } else if (isLaptop) {
                        laptopInfoEdit();
                } else {
                        bookInfoEdit();
                }
                setResourceInfo(shownResourceId);
        }

        /**
         * Initialises the gui for the resource detail
         */
        @FXML
        public void initialize() {
                initializeGui();
                if (isDvd) {
                        addDvdGui();
                } else if (isLaptop) {
                        addLaptopGui();
                } else {
                        addBookGui();
                }
                if (!SharedData.getIsLibrarian()) {
                        saveButton.visibleProperty().set(false);
                        editButton.visibleProperty().set(false);
                        deleteButton.visibleProperty().set(false);
                        changeThumbnailButton.visibleProperty().set(false);
                        copyTable.visibleProperty().set(false);
                        loanButton.setVisible(true);
                } else {
                        saveButton.visibleProperty().set(true);
                        editButton.visibleProperty().set(true);
                        deleteButton.visibleProperty().set(true);
                        changeThumbnailButton.visibleProperty().set(true);
                        copyTable.visibleProperty().set(true);
                        loanButton.setVisible(true);
                }
        }

        /**
         *  Sets  resource to be shown.
         * @param dvd The resource
         */
        public void setDvdResource(Dvd dvd) {
                shownResourceId = dvd.getID();
                showedDvdResource = dvd;
        }
        
        /**
         *  Sets  resource to be shown.
         * @param laptop The resource
         */
        public void setLaptopResource(Laptop laptop) {
                shownResourceId = laptop.getID();
                showedLaptopResource = laptop;
        }
        
        /**
         *  Sets  resource to be shown.
         * @param book The resource
         */
        public void setBookResource(Book book) {
                shownResourceId = book.getID();
                showedBookResource = book;
        }

        private void setResourceInfo(String resourceId) {
                ArrayList<Dvd> allDvds = new ArrayList<Dvd>();
                allDvds = (ArrayList<Dvd>) DatabaseManager.getTable("dvd");
                allDvds.removeIf((s -> !s.getID().equals(resourceId)));
                
                ArrayList<Book> allBooks = new ArrayList<Book>();
                allBooks = (ArrayList<Book>) DatabaseManager.getTable("book");
                
                ArrayList<Laptop> allLaptops = new ArrayList<Laptop>();
                allLaptops = (ArrayList<Laptop>) DatabaseManager.getTable("laptop");
                if(!allDvds.isEmpty())
                {
                        showedDvdResource = allDvds.get(0);
                        isDvd = true;
                } else if (!allBooks.isEmpty()) {
                        showedBookResource = allBooks.get(0);
                        isBook = true;
                } else {
                        showedLaptopResource = allLaptops.get(0);
                        isLaptop = true;
                }
                ArrayList<Copy> allCopies = new ArrayList<Copy>();
                try {
                        allCopies = (ArrayList<Copy>) DatabaseManager
                                        .getTable("copy");
                } catch (ClassCastException e) {
                        // DBERROR
                }
                resourceCopies = allCopies;
                resourceCopies.removeIf((s -> !s.getResourceID()
                                .equals(resourceId)));
        }

        private void initializeGui() {
                showLoanCreate.setVisible(false);
                showReturnBox.setVisible(false);
                if(isDvd) {
                        shownResourceId = showedDvdResource.getID();
                        resourceID.textProperty().set(shownResourceId);
                        titleLabel.textProperty().set(showedDvdResource.getTitle());
                        titleTextBox.setText(showedDvdResource.getTitle());
                        yearLabel.textProperty().set(Integer
                                        .toString(showedDvdResource.getYear()));
                        yearTextBox.setText(Integer.toString(showedDvdResource.getYear()));
                        Image thumbnail = new Image(showedDvdResource.getThumbnail());
                        thumbnailShow.setImage(thumbnail);
                        numOfCopiesLabel.textProperty().set(Integer
                                        .toString(showedDvdResource.getNumCopies()));
                } else if(isLaptop) {
                        shownResourceId = showedLaptopResource.getID();
                        resourceID.textProperty().set(shownResourceId);
                        titleLabel.textProperty().set(showedLaptopResource.getTitle());
                        titleTextBox.setText(showedLaptopResource.getTitle());
                        yearLabel.textProperty().set(Integer
                                        .toString(showedLaptopResource.getYear()));
                        yearTextBox.setText(Integer.toString(showedLaptopResource.getYear()));
                        Image thumbnail = new Image(showedLaptopResource.getThumbnail());
                        thumbnailShow.setImage(thumbnail);
                        numOfCopiesLabel.textProperty().set(Integer
                                        .toString(showedLaptopResource.getNumCopies()));
                } else {
                        shownResourceId = showedBookResource.getID();
                        resourceID.textProperty().set(shownResourceId);
                        titleLabel.textProperty().set(showedBookResource.getTitle());
                        titleTextBox.setText(showedBookResource.getTitle());
                        yearLabel.textProperty().set(Integer
                                        .toString(showedBookResource.getYear()));
                        yearTextBox.setText(Integer.toString(showedBookResource.getYear()));
                        Image thumbnail = new Image(showedBookResource.getThumbnail());
                        thumbnailShow.setImage(thumbnail);
                        numOfCopiesLabel.textProperty().set(Integer
                                        .toString(showedBookResource.getNumCopies()));
                }
                
                
                populateCopyTable();
        }

        private void populateCopyTable() {
                copyIdColumn.setCellValueFactory
                (new PropertyValueFactory<Copy
                                , String>("ID"));
                copyDurationColumn
                .setCellValueFactory(new PropertyValueFactory<Copy,
                                String>("loanDuration"));
                moreInfoColumn.setCellFactory(
                                ActionButtonTableCell
                                .<Copy>forTableColumn("Edit",
                                                (Copy c) -> showCopyInfo(c)));
                
                editColumn.setCellFactory(
                                ActionButtonTableCell
                                .<Copy>forTableColumn("Edit",
                                                (Copy c) -> showEditInfo(c)));

                // if the list of copies isnt null
                if (resourceCopies != null) {
                        // populate the columns
                        copyTable.getItems().setAll(resourceCopies);
                }
        }

        private Copy showCopyInfo(Copy c) {
                Popup popup = new Popup();
                CopyHistoryController controller =
                                new CopyHistoryController();
                controller.setCopyId(c.getID());
                FXMLLoader loader = new 
                                FXMLLoader(getClass().getResource("Copy.fxml"));
                loader.setController(controller);
                try {
                        popup.getContent().add((Parent)loader.load());
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return c;
        }
        
        private Copy showEditInfo(Copy c)
        {
                Popup popup = new Popup();
                CopyEditController controller =
                                new CopyEditController();
                controller.setCopyID(c.getID());
                controller.setResourceID(c.getResourceID());
                controller.setDuration(c.getLoanDuration());
                controller.setCopyStatus(c.getstatus());
                if(isLaptop) {
                        controller.setIsLaptop();
                } else if (isBook) {
                        controller.setIsBook();
                } else {
                        controller.setIsDvd();
                }
                FXMLLoader loader = new 
                                FXMLLoader(getClass().getResource("CopyEdit.fxml"));
                loader.setController(controller);
                try {
                        popup.getContent().add((Parent)loader.load());
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return c;
        }
        
        @FXML
        private void handleNewCopy(ActionEvent event) {
                Popup popup = new Popup();
                CopyEditController controller =
                                new CopyEditController();
                controller.setResourceID(shownResourceId);
                FXMLLoader loader = new 
                                FXMLLoader(getClass().getResource("CopyEdit.fxml"));
                loader.setController(controller);
                try {
                        popup.getContent().add((Parent)loader.load());
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private void addDvdGui() {
                dvdGrid.setVisible(true);
                Dvd currentDvd = showedDvdResource;
                directorLabel.textProperty().set(currentDvd.getDirector());
                directorTextBox.setText(currentDvd.getDirector());
                runtimeLabel.textProperty()
                .set(Integer.toString(currentDvd.getRuntime()));
                runtimeTextBox.setText(Integer.toString(currentDvd.getRuntime()));
                dvdLanguageLabel.textProperty().set(currentDvd.getLanguage());
                dvdLanguageTextBox.setText(currentDvd.getLanguage());
                populateDvdTable(currentDvd);
                saveButton.setOnAction((new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                                handleDvdSave();
                        }
                }));
        }
        
        private void  populateDvdTable(Dvd currentDvd)
        {
                ArrayList<String> subLanguages = currentDvd.getSubLanguages();
                subLanguagesColumn.setCellValueFactory(c -> new SimpleStringProperty());
                subtitleLabelTable.getItems().addAll(subtitleLabelTable);
        }

        private void addLaptopGui() {
                laptopGrid.setVisible(true);
                Laptop currentLaptop = showedLaptopResource;
                manufacturerLabel.textProperty()
                .set(currentLaptop.getManufacturer());
                manufacturerTextBox.setText(currentLaptop.getManufacturer());
                modelLabel.textProperty().set(currentLaptop.getModel());
                modelTextBox.setText(currentLaptop.getModel());
                osLabel.textProperty().set(currentLaptop.getOperatingSystem());
                osTextBox.setText(currentLaptop.getOperatingSystem());
                saveButton.setOnAction((new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                                handleLaptopSave();
                        }
                }));

        }

        private void addBookGui() {
                bookGrid.setVisible(true);
                Book currentBook = showedBookResource;
                authorLabel.textProperty().set(currentBook.getAuthor());
                authorTextBox.setText(currentBook.getAuthor());
                genreLabel.textProperty().set(currentBook.getGenre());
                genreTextBox.setText(currentBook.getGenre());
                isbnLabel.textProperty().set(currentBook.getIsbn());
                isbnTextBox.setText(currentBook.getIsbn());
                bookLanguageLabel.textProperty().set(currentBook.getLanguage());
                bookLanguageTextBox.setText(currentBook.getLanguage());
                saveButton.setOnAction((new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                                handleBookSave();
                        }
                }));
        }

        private void sharedInfoEditToggle() {
                boolean titleTextboxShow = titleTextBox.visibleProperty().get();
                titleTextBox.visibleProperty().set(!titleTextboxShow);
                titleLabel.setVisible(!titleLabel.visibleProperty().get());
                boolean yearTextBoxShow = yearTextBox.visibleProperty().get();
                yearTextBox.visibleProperty().set(!yearTextBoxShow);
                yearLabel.setVisible(!yearLabel.visibleProperty().get());
                thumbnailShow.setVisible(!thumbnailShow.visibleProperty().get());
                saveButton.setVisible(!saveButton.visibleProperty().get());
                if (editButton.getText().equals("Cancel")) {
                        editButton.setText("Edit Details");
                } else {
                        editButton.setText("Cancel");
                }
                copyTable.setEditable(!copyTable.editableProperty().get());
        }

        private void laptopInfoEdit() {
                boolean manufacturerTBShow =
                                manufacturerTextBox.visibleProperty().get();
                manufacturerTextBox.visibleProperty().set(!manufacturerTBShow);
                boolean manufacturerLabelShow =
                                manufacturerLabel.visibleProperty().get();
                manufacturerLabel.visibleProperty().set(!manufacturerLabelShow);
                modelTextBox.visibleProperty()
                .set(!modelTextBox.visibleProperty().get());
                modelLabel.visibleProperty()
                .set(!modelLabel.visibleProperty().get());
                osLabel.visibleProperty().set(!osLabel.visibleProperty().get());
                osTextBox.visibleProperty()
                .set(!osTextBox.visibleProperty().get());
        }

        private void dvdInfoEdit() {
                directorLabel.visibleProperty()
                .set(!directorLabel.visibleProperty().get());
                directorTextBox.visibleProperty()
                .set(!directorTextBox.visibleProperty().get());
                runtimeLabel.visibleProperty()
                .set(!runtimeLabel.visibleProperty().get());
                runtimeTextBox.visibleProperty()
                .set(!runtimeTextBox.visibleProperty().get());
                dvdLanguageLabel.visibleProperty()
                .set(!dvdLanguageLabel.visibleProperty().get());
                dvdLanguageTextBox.visibleProperty()
                .set(!dvdLanguageTextBox.visibleProperty().get());
                subtitleLabelTable
                .setEditable(!subtitleLabelTable.editableProperty().get());
                // DVD TABLE
        }

        private void bookInfoEdit() {
                authorLabel.visibleProperty()
                .set(!authorLabel.visibleProperty().get());
                authorTextBox.visibleProperty()
                .set(!authorTextBox.visibleProperty().get());
                publisherLabel.visibleProperty()
                .set(!publisherLabel.visibleProperty().get());
                publisherTextBox.visibleProperty()
                .set(!publisherTextBox.visibleProperty().get());
                genreTextBox.visibleProperty()
                .set(!genreTextBox.visibleProperty().get());
                genreLabel.visibleProperty()
                .set(!genreLabel.visibleProperty().get());
                isbnLabel.visibleProperty()
                .set(!isbnLabel.visibleProperty().get());
                isbnTextBox.visibleProperty()
                .set(!isbnTextBox.visibleProperty().get());
                bookLanguageLabel.visibleProperty()
                .set(!bookLanguageLabel.visibleProperty().get());
                boolean bookLanguageTBShow =
                                bookLanguageTextBox.visibleProperty().get();
                bookLanguageTextBox.visibleProperty().set(!bookLanguageTBShow);
        }

        @FXML
        private void handleThumbnail(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter =
                                new FileChooser.
                                ExtensionFilter("PNG files (*.png)", "*.png");
                fileChooser.getExtensionFilters().add(extFilter);
                Stage stage = (Stage) numOfCopiesLabel.getScene().getWindow();
                File file = fileChooser.showOpenDialog(stage);
                newThumbnailPath = file.getPath();
        }

        @FXML
        private void handleEditAction(ActionEvent event) {
                sharedInfoEditToggle();
                if (isDvd) {
                        dvdInfoEdit();
                } else if (isLaptop) {
                        laptopInfoEdit();
                } else {
                        bookInfoEdit();
                }
        }

        @FXML
        private void handleDeleteAction(ActionEvent event) {
                if (isDvd) {
                        DatabaseManager.deleteRecord(showedDvdResource, "dvd");
                } else if (isLaptop) {
                        DatabaseManager.deleteRecord(showedLaptopResource, "laptop");
                } else {
                        DatabaseManager.deleteRecord(showedLaptopResource, "book");
                }
                
                VBox root = null;
                try {
                        root = (VBox) FXMLLoader.load(
                                        getClass()
                                        .getClassLoader()
                                        .getResource("cs230/application"
                                                        + "/MainPage.fxml"));
                } catch (IOException e) {
                        e.printStackTrace();
                }
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass()
                                .getClassLoader()
                                .getResource("cs230/application/application.css")
                                .toExternalForm());
                Stage stage = (Stage) deleteButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
        }

        @FXML
        private void handleBookSave() {
                Book oldBook = showedBookResource;
                boolean canAdd = false;
                String titleAdd = "";
                int yearAdd = 0;
                String thumbnailAdd = "";
                String authorAdd = "";
                String publisherAdd = "";
                String genreAdd = "";
                String isbnAdd = "";
                String languageAdd = "";
                if (!titleTextBox.getText().isEmpty()) {
                        titleAdd = titleTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (!yearTextBox.getText().isEmpty()) {
                        try {
                                yearAdd = Integer.parseInt(yearTextBox.getText());
                        } catch (NumberFormatException e) {
                                canAdd = false;
                        }
                }
                if (newThumbnailPath != null && !newThumbnailPath.isEmpty()) {
                        thumbnailAdd = oldBook.getThumbnail();
                } else {
                        thumbnailAdd = newThumbnailPath;
                }
                if (!authorTextBox.getText().isEmpty()) {
                        authorAdd = authorTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (!publisherTextBox.getText().isEmpty()) {
                        publisherAdd = publisherTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (canAdd) {
                        Book newBook = new Book(shownResourceId, titleAdd,
                                        yearAdd, thumbnailAdd, authorAdd,
                                        publisherAdd, genreAdd,
                                        isbnAdd, languageAdd);
                        DatabaseManager.editRecord(oldBook, newBook, "book");
                        showedBookResource = newBook;
                        incorrectFieldLabel.setVisible(false);
                        setResourceInfo(newBook.getID());
                        initialize();

                } else {
                        incorrectFieldLabel.setVisible(true);
                }
        }

        @FXML
        private void handleDvdSave() {
                Dvd oldDvd = showedDvdResource;
                boolean canAdd = false;
                String titleAdd = "";
                int yearAdd = 0;
                String thumbnailAdd = "";
                String directorAdd = "";
                int runtimeAdd = 0;
                String languageAdd = "";
                ArrayList<String> subLangsAdd = (ArrayList<String>) subtitleLabelTable.getItems();

                if (!titleTextBox.getText().isEmpty()) {
                        titleAdd = titleTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (!yearTextBox.getText().isEmpty()) {
                        try {
                                yearAdd = Integer.parseInt(yearTextBox.getText());
                        } catch (NumberFormatException e) {
                                canAdd = false;
                        }
                }
                if (newThumbnailPath != null && !newThumbnailPath.isEmpty()) {
                        thumbnailAdd = oldDvd.getThumbnail();
                } else {
                        thumbnailAdd = newThumbnailPath;
                }
                if (!directorTextBox.getText().isEmpty()) {
                        directorAdd = directorTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (!runtimeTextBox.getText().isEmpty()) {
                        try {
                                runtimeAdd = Integer.parseInt(runtimeTextBox.getText());
                        } catch (NumberFormatException e) {
                                canAdd = false;
                        }
                }
                if (canAdd) {
                        Dvd newDvd = new Dvd(shownResourceId, titleAdd, yearAdd, thumbnailAdd, directorAdd, runtimeAdd,
                                        languageAdd, subLangsAdd);
                        DatabaseManager.editRecord(oldDvd, newDvd, "dvd");
                        showedDvdResource = newDvd;
                        incorrectFieldLabel.setVisible(false);
                        setResourceInfo(newDvd.getID());
                        initialize();

                } else {
                        incorrectFieldLabel.setVisible(true);
                }
        }

        @FXML
        private void handleLaptopSave() {
                Laptop oldLaptop = showedLaptopResource;
                boolean canAdd = false;
                String titleAdd = "";
                int yearAdd = 0;
                String thumbnailAdd = "";
                String manufacturerAdd = "";
                String osAdd = "";
                String modelAdd = "";
                if (!titleTextBox.getText().isEmpty()) {
                        titleAdd = titleTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (!yearTextBox.getText().isEmpty()) {
                        try {
                                yearAdd = Integer.parseInt(yearTextBox.getText());
                        } catch (NumberFormatException e) {
                                canAdd = false;
                        }
                }
                if (newThumbnailPath != null && !newThumbnailPath.isEmpty()) {
                        thumbnailAdd = oldLaptop.getThumbnail();
                } else {
                        thumbnailAdd = newThumbnailPath;
                }
                if (!manufacturerTextBox.getText().isEmpty()) {
                        manufacturerAdd = manufacturerTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (!osTextBox.getText().isEmpty()) {
                        osAdd = osTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (!modelTextBox.getText().isEmpty()) {
                        modelAdd = modelTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (canAdd) {
                        Laptop newLaptop = new Laptop(shownResourceId, titleAdd, yearAdd, thumbnailAdd, manufacturerAdd,
                                        modelAdd, osAdd);
                        DatabaseManager.editRecord(oldLaptop, newLaptop, "laptop");
                        showedLaptopResource =  newLaptop;
                        incorrectFieldLabel.setVisible(false);
                        setResourceInfo(newLaptop.getID());
                        initialize();

                } else {
                        incorrectFieldLabel.setVisible(true);
                }
        }

        @FXML
        private void handleLoanAction(ActionEvent event) {
                showLoanCreate.setVisible(true);
                userNotFoundLoanLabel.setVisible(false);
        }

        @FXML
        private void handleLoanSaveAction(ActionEvent event) {
                String inputUsername = userLoanTextBox.getText();
                Address tempAddress = new Address("", "", "", "");
                User borrowingUser =
                                new User(inputUsername, 
                                                null, null,
                                                null, tempAddress, 0.0, "");
                boolean exists = DatabaseManager
                                .checkForRecord(borrowingUser, "user");
                ArrayList<Loan> loanTable =
                                (ArrayList<Loan>) DatabaseManager.getTable("Loan");
                String nextId = "";
                if (loanTable.isEmpty()) {
                        nextId = "1";
                } else {
                        int maxId = 0;
                        for (Loan loan : loanTable) {
                                int loanInt = Integer.parseInt(loan.getLoanID());
                                if (Integer.parseInt(loan.getLoanID()) > maxId) {
                                        maxId = loanInt;
                                }
                        }
                        maxId++;
                        nextId = Integer.toString(maxId);
                }
                boolean canLoan = true;
                for(Loan loan: loanTable)
                {
                        if(loan.getUserName().equals(inputUsername) 
                                        && loan.getDueDate()
                                        .isBefore(LocalDate.now()))
                        {
                                canLoan = false;
                        }
                }

                if (exists) {
                        borrowingUser = (User)DatabaseManager
                                        .searchRecord(borrowingUser, "user");
                        if(borrowingUser.getBalance()> 0 && canLoan)
                        {
                                if(isBook){
                                        handleBookReserve(nextId, inputUsername);
                                } else if (isLaptop) {
                                        handleLaptopReserve(nextId,inputUsername);
                                } else {
                                        handleDvdReserve(nextId, inputUsername);
                                }
                        } else { 
                                userCannotLoanLabel.setVisible(true);
                        }
                        
                } else {
                        userNotFoundLoanLabel.setVisible(true);
                }
        }
        
        @FXML
        private void handleReserveYes(ActionEvent event) {
                if(isBook){
                        handleBookReserveYes();
                } else if (isLaptop) {
                        handleLaptopReserveYes();
                } else {
                        handleDvdReserveYes();
                }
                setDueDates();
                initialize();
        }
        
       private void handleBookReserveYes()
       {
               Resource resource = new Resource(showedBookResource.getID());
               ArrayList<Book> resources =
                               (ArrayList<Book>)DatabaseManager
                               .searchRecord(resource, "book");
               resource = resources.get(0); 
               resource.addToQueue(loanUsername);
               DatabaseManager.editRecord(showedBookResource, resource, "book");
       }
       
       private void handleLaptopReserveYes()
       {
               Resource resource = new Resource(showedLaptopResource.getID());
               ArrayList<Laptop> resources =
                               (ArrayList<Laptop>)DatabaseManager
                               .searchRecord(resource, "laptop");
               resource = resources.get(0); 
               resource.addToQueue(loanUsername);
               DatabaseManager.editRecord(showedBookResource, resource, "laptop");
       }
       
       private void handleDvdReserveYes()
       {
               Resource resource = new Resource(showedDvdResource.getID());
               ArrayList<Dvd> resources =
                               (ArrayList<Dvd>)DatabaseManager
                               .searchRecord(resource, "dvd");
               resource = resources.get(0); 
               resource.addToQueue(loanUsername);
               DatabaseManager.editRecord(showedBookResource, resource, "dvd");
       }
        
        @FXML
        private void handleBookReserve (String nextId, String inputUsername)
        {
                if (showedBookResource.checkQueue() == 0) {
                        loanResource(nextId, inputUsername);
                } else if (showedBookResource.peekQueue()
                                .equals(inputUsername)) {
                        loanResource(nextId, inputUsername);
                        Book resource 
                        = new Book(showedBookResource.getID());
                        ArrayList<Book> resources = 
                                        (ArrayList<Book>)DatabaseManager
                                        .searchRecord(resource, "book");
                        resource = resources.get(0);
                        resource.removeFromQueue();
                        DatabaseManager
                        .editRecord(showedBookResource,
                                        resource, "book");
                        showedBookResource = resource;
                }  else {
                        showReserveOption(inputUsername);
                }
        }
        
        @FXML
        private void handleDvdReserve (String nextId, String inputUsername)
        {
                if (showedDvdResource.checkQueue() == 0) {
                        loanResource(nextId, inputUsername);
                } else if (showedDvdResource.peekQueue()
                                .equals(inputUsername)) {
                        loanResource(nextId, inputUsername);
                        Dvd resource 
                        = new Dvd(showedDvdResource.getID());
                        ArrayList<Dvd> resources = 
                                        (ArrayList<Dvd>)DatabaseManager
                                        .searchRecord(resource, "dvd");
                        resource = resources.get(0);
                        resource.removeFromQueue();
                        DatabaseManager
                        .editRecord(showedBookResource,
                                        resource, "dvd");
                        showedDvdResource = resource;
                }  else {
                        showReserveOption(inputUsername);
                }
        }
        
        @FXML
        private void handleLaptopReserve (String nextId, String inputUsername)
        {
                if (showedLaptopResource.checkQueue() == 0) {
                        loanResource(nextId, inputUsername);
                } else if (showedLaptopResource.peekQueue()
                                .equals(inputUsername)) {
                        loanResource(nextId, inputUsername);
                        Laptop resource 
                        = new Laptop(showedLaptopResource.getID());
                        ArrayList<Laptop> resources = 
                                        (ArrayList<Laptop>)DatabaseManager
                                        .searchRecord(resource, "laptop");
                        resource = resources.get(0);
                        resource.removeFromQueue();
                        DatabaseManager
                        .editRecord(showedLaptopResource,
                                        resource, "laptop");
                        showedLaptopResource = resource;
                }  else {
                        showReserveOption(inputUsername);
                }
        }
        
        @FXML
        private void handleReserveNo(ActionEvent event) {
                initialize();
        }
        
        private void showReserveOption(String inputUsername)
        {
                userLoanTextBox.setVisible(false);
                loanUsername = inputUsername;
                reserveOptions.setVisible(true);
        }
        
        private void setDueDates()
        {
                ArrayList<Copy> copies = (ArrayList<Copy>)DatabaseManager
                                .getTable("copy");
                ArrayList<Loan> loans = (ArrayList<Loan>)DatabaseManager
                                .getTable("loan");
                copies.removeIf(c -> !c.getResourceID()
                                .equals(shownResourceId));
                if(!loans.isEmpty()) {
                        loans.removeIf(l -> !l.getResourceID()
                                .equals(shownResourceId) && (l.getDueDate() != null));
                }
                if(!loans.isEmpty()) {
                        loans.sort((Loan l1, Loan l2) -> l1.getDueDate()
                                                .compareTo(l1.getBorrowDate()));
                        Loan loanToChange = loans.get(0);
                        copies.removeIf(c -> !c.getID().equals(loanToChange
                                                .getCopyID()));
                        if(!copies.isEmpty()) {
                                Copy copyToChange = copies.get(0);
                                Loan newLoan = new Loan
                                (loanToChange.getLoanID(),
                                                loanToChange.getUserName(),
                                                loanToChange.getCopyID(),
                                                loanToChange.getResourceID(),
                                                loanToChange.getBorrowDate());
                                Long copyDuration = (long) copyToChange
                                                        .getLoanDuration();
                                LocalDate dateToComplete = 
                                                        loanToChange
                                                        .getBorrowDate()
                                                        .plusDays(copyDuration);
                                if( dateToComplete.isBefore(LocalDate.now())) {
                                                newLoan.setDueDate(LocalDate.now().plusDays(1));
                                } else {
                                        newLoan.setDueDate(dateToComplete);       
                                }
                                DatabaseManager.editRecord(loanToChange, newLoan, "loan");
                        }
                }    
        }
        
        private void loanResource(String nextId, String inputUsername)
        {
                ArrayList<Copy> availableCopyList 
                = (ArrayList<Copy>) DatabaseManager
                .getTable("copy");
                ArrayList<Copy> copyList 
                = (ArrayList<Copy>) DatabaseManager
                .getTable("copy");
                availableCopyList.removeIf
                (c -> !c.getResourceID()
                                .equals(shownResourceId)
                        && c.getstatus() != Status.AVAILABLE);
                copyList.removeIf(c -> !c.getResourceID()
                                .equals(shownResourceId));
                int firstIndex = 0;
                LocalDate now = LocalDate.now();
                if (!copyList.isEmpty()) {
                        Loan newLoan = new Loan(nextId,
                                        inputUsername,
                                        copyList.get(firstIndex)
                                        .getID(),
                                        shownResourceId, now);
                        DatabaseManager
                        .saveRecord(newLoan, "loan");
                        initialize();
                }
        }

        @FXML
        private void handleLoanCancelAction(ActionEvent event) {
                userLoanTextBox.setText("");
                userNotFoundLoanLabel.setVisible(false);
                showLoanCreate.setVisible(false);
        }

        @FXML
        private void handleReturnShowAction(ActionEvent event) {
                showReturnBox.setVisible(true);
                userNotFoundReturnLabel.setVisible(false);
                noLoanFoundLabel.setVisible(false);
        }

        @FXML
        private void handleReturnSaveAction(ActionEvent event) {
                String inputUsername = returnUsernameTextBox.getText();
                String inputCopyId = returnCopyIdTextBox.getText();
                Address tempAddress = new Address("", "", "", "");
                User returningUser = new User(inputUsername,
                                null, null, null, tempAddress, 0.0, "");
                boolean exists = DatabaseManager.checkForRecord(returningUser, "user");
                ArrayList<Loan> loanTable = (ArrayList<Loan>) 
                                DatabaseManager.getTable("loan");
                loanTable.removeIf(l -> !l.getUserName().equals(inputUsername)
                                && !l.getResourceID().equals(shownResourceId)
                                && !l.getCopyID().equals(inputCopyId));
                Loan oldLoan = new Loan("", "", "", "", LocalDate.now());
                ArrayList<Copy> copyTable = (ArrayList<Copy>)
                                DatabaseManager.getTable("copy");
                copyTable.removeIf(c -> !c.getID().equals(inputCopyId));
                if (!loanTable.isEmpty()) {
                        for (Loan loan : loanTable) {
                                if (loan.getReturnedDate() == null) {
                                        oldLoan = loan;
                                }
                        }
                        LocalDate now = LocalDate.now();
                        Loan newLoan = new Loan(oldLoan.getLoanID(),
                                        oldLoan.getUserName(),
                                        oldLoan.getCopyID(), 
                                        oldLoan.getResourceID(), now);
                        ArrayList<Loan> loans= (ArrayList<Loan>)
                                        DatabaseManager.searchRecord(newLoan,"loan");
                        newLoan = loans.get(0);
                        newLoan.setReturnedDate(now);
                        DatabaseManager.editRecord(oldLoan, newLoan, "loan");
                        if (!oldLoan.getDueDate().equals(null)) {
                                if (oldLoan.getDueDate().isBefore(now)) {
                                        Integer fineAmount = 0;
                                        Long daysLate = Duration.between(oldLoan
                                                        .getDueDate()
                                                        .atStartOfDay(), 
                                                        LocalDate.now()
                                                        .atStartOfDay())
                                                        .toDays();
                                        if(daysLate > 0) {
                                                fineAmount = calculateFine(daysLate);
                                                //Fine change
                                                Fine newFine = new Fine(newLoan.getLoanID()
                                                                , fineAmount);
                                                DatabaseManager.saveRecord(newFine, "fine");
                                                ArrayList<User> returningUsers = 
                                                                (ArrayList<User>) DatabaseManager
                                                                .searchRecord(returningUser, "user");
                                                returningUser = returningUsers
                                                                .get(0);
                                                User newUser 
                                                = new User(returningUser.getName()
                                                                ,null, null, null,
                                                                tempAddress, 0.0, "");
                                                ArrayList<User> users = 
                                                                (ArrayList<User>)DatabaseManager
                                                                .searchRecord(newUser, "user");
                                                newUser = users.get(0);
                                                Double currentB = returningUser.getBalance();
                                                newUser.setBalance(fineAmount.doubleValue());
                                                DatabaseManager.editRecord(returningUser, newUser, "user");
                                        } 
                                }
                        }
                        initialize();
                } else {
                        noLoanFoundLabel.setVisible(true);
                }
        }
        
        private int calculateFine(Long daysLate)
        {
                int fineAmount = 0;
                if(isBook || isDvd)
                {
                        fineAmount = (int) (daysLate*DAILY_BOOK_DVD_FINE);
                        if(fineAmount > MAX_BOOK_DVD_FINE) {
                                fineAmount = MAX_BOOK_DVD_FINE;
                        }
                } else {
                        fineAmount = (int) (daysLate*DAILY_LAPTOP_FINE);
                        if(fineAmount > MAX_LAPTOP_FINE)
                        {
                                fineAmount = MAX_LAPTOP_FINE;
                        }
                }
                return fineAmount;
        }

        @FXML
        private void handleReturnCancelAction(ActionEvent event) {
                showReturnBox.setVisible(true);
                userNotFoundReturnLabel.setVisible(false);
                noLoanFoundLabel.setVisible(false);
        }
}
