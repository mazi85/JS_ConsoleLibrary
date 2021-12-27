package pl.mazi85.library.app;

import pl.mazi85.library.data.Library;
import pl.mazi85.library.io.DataReader;

public class LibraryControl {

    private static final int EXIT =0;
    private static final int ADD_BOOK =1;
    private static final int ADD_MAGAZINES =2;
    private static final int PRINT_BOOKS =3;
    private static final int PRINT_MAGAZINES =4;

    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop(){
        Option option;

        do {
            printOptions();
            option=Option.crateFromInt(dataReader.getInt());

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
                    System.out.println("nie ma takiej opcji wprowadz ponownie: ");
            }
        }while (option != Option.EXIT);

    }

    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        for (Option option : Option.values()) {
            System.out.println(option);
        }

    }

    private void exit() {
        System.out.println("Koniec programu, papa!");
        dataReader.close();
    }

    private void printBooks() {
        library.printBooks();
    }

    private void addBook() {
        library.addBook(dataReader.readAndCreateBook());
    }
    private void printMagazines() {
        library.printMagazines();
    }

    private void addMagazine() {
        library.addMagazine(dataReader.readAndCreateMagazine());
    }

}
