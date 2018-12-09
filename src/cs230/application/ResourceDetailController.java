package cs230.application;

import cs230.system.*;
import cs230.system.Copy.Status;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
 * 
 * @author 901306
 */
public class ResourceDetailController {
        
        private final int MAX_BOOK_DVD_FINE= 25;
        private final int MAX_LAPTOP_FINE= 25;
        private final int DAILY_BOOK_DVD_FINE = 2;
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

        @FXML
        private Button returnButton;

        @FXML
        private Button returnCancelButton;

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

        private Resource originalResource;

        private Resource showedResource;

        private ArrayList<Copy> resourceCopies;

        private Boolean isBook = false;

        private Boolean isDvd = false;

        private Boolean isLaptop = false;

        private String shownResourceId;

        private String newThumbnailPath = "";
        
        private String loanUsername = "";

        public ResourceDetailController() {
                setResourceInfo(shownResourceId);
        }

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

        public void setResource(String id) {
                shownResourceId = id;
        }

        private void setResourceInfo(String resourceId) {
                ArrayList<Resource> allResources = new ArrayList<Resource>();
                try {
                        allResources = (ArrayList<Resource>) DatabaseManager.getTable("Resource");
                } catch (ClassCastException e) {
                        // DBERROR
                }
                allResources.removeIf((s -> !s.getID().equals(resourceId)));
                showedResource = allResources.get(0);
                originalResource = showedResource;
                if (showedResource.getType().equals("Dvd")) {
                        showedResource = (Dvd) showedResource;
                        isDvd = true;
                } else if (showedResource.getType().equals("Laptop")) {
                        showedResource = (Laptop) showedResource;
                        isLaptop = true;
                } else {
                        showedResource = (Book) showedResource;
                        isBook = true;
                }
                ArrayList<Copy> allCopies = new ArrayList<Copy>();
                try {
                        allCopies = (ArrayList<Copy>) DatabaseManager.getTable("Copy");
                } catch (ClassCastException e) {
                        // DBERROR
                }
                resourceCopies = allCopies;
                resourceCopies.removeIf((s -> !s.getResourceID().equals(resourceId)));
        }

        private void initializeGui() {
                showLoanCreate.setVisible(false);
                showReturnBox.setVisible(false);
                String showedResourceID = showedResource.getID();
                resourceID.textProperty().set(showedResourceID);
                titleLabel.textProperty().set(showedResource.getTitle());
                titleTextBox.setText(showedResource.getTitle());
                yearLabel.textProperty().set(Integer.toString(showedResource.getYear()));
                yearTextBox.setText(Integer.toString(showedResource.getYear()));
                Image thumbnail = new Image(showedResource.getThumbnail());
                thumbnailShow.setImage(thumbnail);
                numOfCopiesLabel.textProperty().set(Integer.toString(showedResource.getNumCopies()));
                populateCopyTable();
        }

        private void populateCopyTable() {
                copyIdColumn.setCellValueFactory(new PropertyValueFactory<Copy, String>("ID"));
                copyDurationColumn.setCellValueFactory(new PropertyValueFactory<Copy, String>("loanDuration"));
                moreInfoColumn.setCellFactory(
                                ActionButtonTableCell.<Copy>forTableColumn("Edit", (Copy c) -> showCopyInfo(c)));

                // if the list of users isnt null
                if (resourceCopies != null) {
                        // populate the columns
                        copyTable.getItems().setAll(resourceCopies);
                }
        }

        private Copy showCopyInfo(Copy c) {
                Popup popup = new Popup();
                ResourceCopyPageController controller =
                                new ResourceCopyPageController();
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

        private void addDvdGui() {
                dvdGrid.setVisible(true);
                Dvd currentDvd = (Dvd) showedResource;
                directorLabel.textProperty().set(currentDvd.getDirector());
                directorTextBox.setText(currentDvd.getDirector());
                runtimeLabel.textProperty().set(Integer.toString(currentDvd.getRuntime()));
                runtimeTextBox.setText(Integer.toString(currentDvd.getRuntime()));
                dvdLanguageLabel.textProperty().set(currentDvd.getLanguage());
                dvdLanguageTextBox.setText(currentDvd.getLanguage());
                saveButton.setOnAction((new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                                handleDvdSave();
                        }
                }));
        }

        private void addLaptopGui() {
                laptopGrid.setVisible(true);
                Laptop currentLaptop = (Laptop) showedResource;
                manufacturerLabel.textProperty().set(currentLaptop.getManufacturer());
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
                Book currentBook = (Book) showedResource;
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
                // Show file select
                saveButton.setVisible(!saveButton.visibleProperty().get());
                if (editButton.getText().equals("Cancel")) {
                        editButton.setText("Edit Details");
                } else {
                        editButton.setText("Cancel");
                }
                copyTable.setEditable(!copyTable.editableProperty().get());
        }

        private void laptopInfoEdit() {
                boolean manufacturerTBShow = manufacturerTextBox.visibleProperty().get();
                manufacturerTextBox.visibleProperty().set(!manufacturerTBShow);
                boolean manufacturerLabelShow = manufacturerLabel.visibleProperty().get();
                manufacturerLabel.visibleProperty().set(!manufacturerLabelShow);
                modelTextBox.visibleProperty().set(!modelTextBox.visibleProperty().get());
                modelLabel.visibleProperty().set(!modelLabel.visibleProperty().get());
                osLabel.visibleProperty().set(!osLabel.visibleProperty().get());
                osTextBox.visibleProperty().set(!osTextBox.visibleProperty().get());
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
                                ExtensionFilter("TXT files (*.txt)", "*.txt");
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
                DatabaseManager.deleteRecord(originalResource, "Resource");
                // load fine page fxml
                VBox root = null;
                try {
                        root = (VBox) FXMLLoader.load(
                                        getClass()
                                        .getClassLoader()
                                        .getResource("cs230/application"
                                                        + "/ResourceList.fxml"));
                } catch (IOException e) {
                        e.printStackTrace();
                }

                // show fine page
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
                Book oldBook = (Book) showedResource;
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
                if (!genreTextBox.getText().isEmpty()) {
                        genreAdd = genreTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (!isbnTextBox.getText().isEmpty()) {
                        isbnAdd = isbnTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (!bookLanguageTextBox.getText().isEmpty()) {
                        languageAdd = bookLanguageTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (canAdd) {
                        Book newBook = new Book(shownResourceId, titleAdd,
                                        yearAdd, thumbnailAdd, authorAdd,
                                        publisherAdd, genreAdd,
                                        isbnAdd, languageAdd);
                        DatabaseManager.editRecord(oldBook, newBook, "book");
                        showedResource = (Resource) newBook;
                        incorrectFieldLabel.setVisible(false);
                        setResourceInfo(newBook.getID());
                        initialize();

                } else {
                        incorrectFieldLabel.setVisible(true);
                }
        }

        @FXML
        private void handleDvdSave() {
                Dvd oldDvd = (Dvd) showedResource;
                boolean canAdd = false;
                String titleAdd = "";
                int yearAdd = 0;
                String thumbnailAdd = "";
                String directorAdd = "";
                int runtimeAdd = 0;
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
                if (!dvdLanguageTextBox.getText().isEmpty()) {
                        languageAdd = dvdLanguageTextBox.getText();
                } else {
                        canAdd = false;
                }
                if (canAdd) {
                        Dvd newDvd = new Dvd(shownResourceId, titleAdd, yearAdd, thumbnailAdd, directorAdd, runtimeAdd,
                                        languageAdd, oldDvd.getSubLanguages());
                        DatabaseManager.editRecord(oldDvd, newDvd, "dvd");
                        showedResource = (Resource) newDvd;
                        incorrectFieldLabel.setVisible(false);
                        setResourceInfo(newDvd.getID());
                        initialize();

                } else {
                        incorrectFieldLabel.setVisible(true);
                }
        }

        @FXML
        private void handleLaptopSave() {
                Laptop oldLaptop = (Laptop) showedResource;
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
                        showedResource = (Resource) newLaptop;
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
                                .checkForRecord(borrowing, "user");
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

                if (exists) {
                        borrowingUser = (User)DatabaseManager.searchRecord(borrowingUser, "user");
                        if(borrowingUser.getBalance()> 0)
                        {
                                if (showedResource.checkQueue() == 0) {
                                        loanResource(nextId, inputUsername);
                                } else if (showedResource.peekQueue().equals(inputUsername)) {
                                        loanResource(nextId, inputUsername);
                                        Resource resource = new Resource(showedResource.getID());
                                        ArrayList<Resource> resources = 
                                                        (ArrayList<Resource>)DatabaseManager
                                                        .searchRecord(resource, "resource");
                                        resource = resources.get(0);
                                        resource.removeFromQueue();
                                        DatabaseManager.editRecord(showedResource, resource, "resource");
                                }  else {
                                        showReserveOption(inputUsername);
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
                Resource resource = new Resource(showedResource.getID());
                ArrayList<Resource> resources = (ArrayList<Resource>)DatabaseManager.searchRecord(resource, "resource");
                resource = resources.get(0); 
                resource.addToQueue(loanUsername);
                DatabaseManager.editRecord(showedResource, resource, "resource");
                setDueDates();
                initialize();
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
                                .equals(showedResource.getID()));
                if(!loans.isEmpty()) {
                        loans.removeIf(l -> !l.getResourceID()
                                .equals(showedResource.getID()) && (l.getDueDate() != null));
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
                                DatabaseManager.editRecord(loanToChange, newLoan, "loan")
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
                                .equals(showedResource.getID())
                        && c.getstatus() != Status.AVAILABLE);
                copyList.removeIf(c -> !c.getResourceID()
                                .equals(showedResource.getID()));
                int firstIndex = 0;
                LocalDate now = LocalDate.now();
                if (!copyList.isEmpty()) {
                        Loan newLoan = new Loan(nextId,
                                        inputUsername,
                                        copyList.get(firstIndex)
                                        .getID(),
                                showedResource.getID(), now);
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
                User returningUser = new User(inputUsername, null, null, null, tempAddress, 0.0, "");
                boolean exists = DatabaseManager.checkForRecord(returningUser, "user");
                ArrayList<Loan> loanTable = (ArrayList<Loan>) DatabaseManager.getTable("Loan");
                loanTable.removeIf(l -> !l.getUserName().equals(inputUsername)
                                && !l.getResourceID().equals(showedResource.getID())
                                && !l.getCopyID().equals(inputCopyId));
                Loan oldLoan = new Loan("", "", "", "", LocalDate.now());
                ArrayList<Copy> copyTable = (ArrayList<Copy>) DatabaseManager.getTable("copy");
                copyTable.removeIf(c -> !c.getID().equals(inputCopyId));
                if (!loanTable.isEmpty()) {
                        for (Loan loan : loanTable) {
                                if (loan.getReturnedDate() == null) {
                                        oldLoan = loan;
                                }
                        }
                        LocalDate now = LocalDate.now();
                        Loan newLoan = oldLoan;
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
                                                Fine newFine = new Fine(newLoan.getLoanID(), fineAmount);
                                                DatabaseManager.saveRecord(newFine, "fine");
                                                ArrayList<User> returningUsers = 
                                                                (ArrayList<User>) DatabaseManager
                                                                .searchRecord(returningUser, "user");
                                                returningUser = returningUsers
                                                                .get(0);
                                                User newUser = returningUser;
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
