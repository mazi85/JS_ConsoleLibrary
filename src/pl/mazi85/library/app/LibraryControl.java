package pl.mazi85.library.app;

import pl.mazi85.library.data.Library;
import pl.mazi85.library.io.DataReader;

public class LibraryControl {

    private final int exit=0;
    private final int addBook=1;
    private final int printBooks=2;

    private DataReader dataReader = new DataReader();

    private Library library = new Library();

    public void controlLoop(){
        int option;

        do {
            printOptions();
            option=dataReader.getInt();

            switch (option){

                case addBook:
                    addBook();
                    break;
                case printBooks:
                    printBooks();
                    break;
                case exit:
                    exit();
                    break;
                default:
                    System.out.println("nie ma takiej opcji wprowadz ponownie: ");
            }
        }while (option != exit);

    }

    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        System.out.println(exit + "- wyjście z programu");
        System.out.println(addBook + "- dodanie nowej książki");
        System.out.println(printBooks + "- wyświetl dostępne książki");
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

}
