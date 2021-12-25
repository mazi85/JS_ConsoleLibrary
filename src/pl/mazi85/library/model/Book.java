package pl.mazi85.library.model;

public class Book extends Publication {


    private String author;
    private int pages;
    private String isbn;

    public Book(String title, String author, int releaseDate, int pages, String publisher) {
        setTitle(title);
        this.author = author;
        setYear(releaseDate);
        this.pages = pages;
        setPublisher(publisher);
    }

    public Book(String title, String author, int releaseDate, int pages, String publisher, String isbn) {
       this(title,author,releaseDate,pages,publisher);
        this.isbn = isbn;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public void printInfo(){

        String info = getTitle() + "; " + author + ": " + getYear() + "; "
        + pages + "; " + getPublisher();
        if (isbn != null)
        {info = info + "; " + isbn; }
        System.out.println(info);

    }


}
