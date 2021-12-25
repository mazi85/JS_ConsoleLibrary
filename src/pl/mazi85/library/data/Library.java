package pl.mazi85.library.data;


import pl.mazi85.library.model.Book;

public class Library {

    private static final int MAX_BOOKS = 1000;
    private Book[] books = new Book[MAX_BOOKS];
    private int booksNumber;



    public void addBook(Book book){

        if(booksNumber< MAX_BOOKS){
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


}