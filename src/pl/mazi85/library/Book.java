package pl.mazi85.library;

public class Book {

    public Book(String title, String author, int releaseDate, int pages, String publisher, String isbn) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.pages = pages;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    String title;
    String author;
    int releaseDate;
    int pages;
    String publisher;
    String isbn;


    void printInfo(){

        String info = title + "; " + author + ": " + releaseDate + "; "
        + pages + "; " + publisher+ "; "+ isbn;
        System.out.println(info);

    }


}
