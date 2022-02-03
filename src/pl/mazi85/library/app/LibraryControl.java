package pl.mazi85.library.app;

import pl.mazi85.library.exception.*;
import pl.mazi85.library.io.file.FileManager;
import pl.mazi85.library.io.file.FileManagerBuilder;
import pl.mazi85.library.model.*;
import pl.mazi85.library.io.ConsolePrinter;
import pl.mazi85.library.io.DataReader;
import pl.mazi85.library.model.comparator.AlphabeticalTitleComparator;

import java.util.Comparator;
import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private Library library;

    public LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();

        try {
            library = fileManager.importData();
            printer.printLine("Zaimportowane dane z pliku.");
        }catch (DataImportException | InvalidDataException e){
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjonowano nową bazę.");
            library = new Library();
        }
    }



    public void controlLoop(){
        Option option;

        do {
            printOptions();
            option=getOption();

            switch (option){

                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINES:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case DELETE_BOOKS:
                    deleteBook();
                    break;
                case DELETE_MAGAZINES:
                    deleteMagazine();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case PRINT_USER:
                    printUsers();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("nie ma takiej opcji wprowadz ponownie: ");
            }
        }while (option != Option.EXIT);

    }

    private void printUsers() {
        printer.printUsers(library.getSortedUsers(Comparator.comparing(User::getLastName)));
    }

    private void addUser() {
       LibraryUser libraryUser =  dataReader.createLibraryUser();
       try{
        library.addUser(libraryUser);}
       catch (UserAlreadyExistsException e){
           printer.printLine(e.getMessage());
       }
    }

    private void printOptions() {
        printer.printLine("Wybierz opcję: ");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }

    }

    private Option getOption(){
        boolean optionOk = false;
        Option option = null;
        while (!optionOk){
            try{
            option=Option.crateFromInt(dataReader.getInt());
            optionOk = true;}
            catch (NoSuchOptionException e){
                printer.printLine(e.getMessage() + ", podaj ponownie:");
            }
            catch (InputMismatchException ignored){
                printer.printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie: ");
            }
        }
        return option;
    }

    private void exit() {
        try{
        fileManager.exportData(library);
        printer.printLine("Eksport danych do pliku zakończona powodzeniem");}
        catch (DataExportException e){
            e.getMessage();
        }
        printer.printLine("Koniec programu, papa!");
        dataReader.close();
    }

    private void printBooks() {

        printer.printBooks(library.getSortedPublications(
                Comparator.comparing(Publication::getTitle,String.CASE_INSENSITIVE_ORDER)));
    }

    private void addBook() {
        try{
        Book book = dataReader.readAndCreateBook();
        library.addPublication(book);}
        catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć książki, niepoprawne dane");
        }
        catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit pojemności,  nie można dodac kolejnej ksiązki");
        }
    }

    private void deleteBook() {

        try {
            Book book = dataReader.readAndCreateBook();
            if (library.removePublication(book)) {
                printer.printLine("usunięto książkę");
            } else {
                printer.printLine("brak wskazanej książki");
            }
        }catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć książki, niepoprawne dane");
        }

    }

    private void printMagazines() {

        printer.printMagazines(library.getSortedPublications((p1,p2)->p1.getTitle().compareToIgnoreCase(p2.getTitle())));
    }
    private void addMagazine() {
        try{
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);}
        catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć magazynu, niepoprawne dane");
        }
        catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit pojemności, nie można dodac kolejnego magazynu");
        }
    }

    private void deleteMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            if (library.removePublication(magazine)) {
                printer.printLine("usunięto magazyn");
            } else {
                printer.printLine("brak wskazanego magazynu");
            }
        }catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć magazynu, niepoprawne dane");
        }

    }

    private enum Option {
        EXIT(0, "wyjście z programu"),
        ADD_BOOK(1, "dodanie nowej książki"),
        ADD_MAGAZINES(2, "dodanie nowego magazynu"),
        PRINT_BOOKS(3, "wyświetl dostępne książki"),
        PRINT_MAGAZINES(4, "wyświetl dostępne magazyny"),
        DELETE_BOOKS(5, "usuń książke"),
        DELETE_MAGAZINES(6, "usuń magazyn"),
        ADD_USER(7,"dodaj czytelnika"),
        PRINT_USER(8, "Wyświetl czytelników");


        private final int number;
        private final String description;


        Option(int number, String description) {
            this.number = number;
            this.description = description;
        }


        public int getNumber() {
            return number;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return number + " - " + description;
        }

        public static Option crateFromInt(int option) throws NoSuchOptionException {

            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id " + option);
            }
        }
    }

}
