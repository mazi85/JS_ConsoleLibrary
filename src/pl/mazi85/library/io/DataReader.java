package pl.mazi85.library.io;

import pl.mazi85.library.model.Book;
import pl.mazi85.library.model.Magazine;

import java.util.Scanner;

public class DataReader {

    private Scanner sc =new Scanner(System.in);
    ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public void close(){
        sc.close();
    }

    public Book readAndCreateBook(){

        printer.printLine("Tytuł: ");
        String title = sc.nextLine();
        printer.printLine("Autor: ");
        String author = sc.nextLine();
        printer.printLine("Wydawnictwo: ");
        String publisher=sc.nextLine();
        printer.printLine("ISBN: ");
        String isbn=sc.nextLine();
        printer.printLine("Rok wydania: ");
        int releaseDate=getInt();
        printer.printLine("Liczba stron: ");
        int pages=getInt();


        return new Book(title,author,releaseDate,pages,publisher,isbn);
    }


    public Magazine readAndCreateMagazine(){

        printer.printLine("Tytuł: ");
        String title = sc.nextLine();
        printer.printLine("Wydawnictwo: ");
        String publisher=sc.nextLine();
        printer.printLine("Język: ");
        String language=sc.nextLine();
        printer.printLine("Rok wydania: ");
        int year=getInt();
        printer.printLine("miesiąc: ");
        int month=getInt();
        printer.printLine("day: ");
        int day=getInt();


        return new Magazine(title,publisher,language,year,month,day);
    }


    public int getInt(){
        try{
        return sc.nextInt();}
        finally {
            sc.nextLine();
        }
    }
}
