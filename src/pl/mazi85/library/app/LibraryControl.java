package pl.mazi85.library.app;

import pl.mazi85.library.data.Library;
import pl.mazi85.library.exception.NoSuchOptionException;
import pl.mazi85.library.io.ConsolePrinter;
import pl.mazi85.library.io.DataReader;
import pl.mazi85.library.model.Book;
import pl.mazi85.library.model.Magazine;
import pl.mazi85.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);

    private Library library = new Library();

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
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("nie ma takiej opcji wprowadz ponownie: ");
            }
        }while (option != Option.EXIT);

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
        printer.printLine("Koniec programu, papa!");
        dataReader.close();
    }

    private void printBooks() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
    }

    private void addBook() {
        try{
        Book book = dataReader.readAndCreateBook();
        library.addBook(book);}
        catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć książki, niepoprawne dane");
        }
        catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit pojemności,  nie można dodac kolejnej ksiązki");
        }
    }
    private void printMagazines() {
        Publication[] publications = library.getPublications();
        printer.printMagazines(publications);
    }

    private void addMagazine() {
        try{
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addMagazine(magazine);}
        catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć magazynu, niepoprawne dane");
        }
        catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit pojemności, nie można dodac kolejnego magazynu");
        }
    }

    private enum Option {
        EXIT(0, "wyjście z programu"),
        ADD_BOOK(1, "dodanie nowej książki"),
        ADD_MAGAZINES(2, "dodanie nowego magazynu"),
        PRINT_BOOKS(3, "wyświetl dostępne książki"),
        PRINT_MAGAZINES(4, "wyświetl dostępne magazyny");


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
