package pl.mazi85.library.data;


import pl.mazi85.library.model.Book;

public class Library {

    private final int maxBooks = 1000;
    private Book[] books = new Book[maxBooks];
    private int booksNumber;



    public void addBook(Book book){

        if(booksNumber<maxBooks){
            books[booksNumber]=book;
            booksNumber++;
        }
        else {
            System.out.println("Maksymalna ilość książek została osiągnięta");
        }
    }

    public void printBooks(){
        if (booksNumber==0){
            System.out.println("Brak książek w bibliotece");
        }

        for (int i = 0; i < booksNumber; i++) {
            books[i].printInfo();

        }
    }


    //       final String appName = "Biblioteka v0.8";

//        Book[] books = new Book[1000];
//        DataReader dataReader = new DataReader();

//        books[0]= new Book("W pustyni i w puszczy","Henryk Sienkiewicz",2010,296,"Greg","9788373271890");
//        books[1] = new Book("JAVA. Efektywne programowanie.Wydanie II","Joshua Bloch",2009,352,"Helion","9788324620845D");
//        books[2] = new Book("SCJP Sun Certified Programmer for JAVA 6 Study Guide","Bert Bates, Katherine Sierra",2008,851,
//                "McGraw-Hill Osborne Media");

//        books[0]=dataReader.readAndCreateBook();
//        books[1]=dataReader.readAndCreateBook();
//        dataReader.close();
//
//
//        System.out.println(appName);
//        System.out.println("Książki dostępne w bibliotece: ");
//        books[0].printInfo();
//        books[1].printInfo();
//        System.out.println("System może przechowywać do " + books.length+ " książek");


}