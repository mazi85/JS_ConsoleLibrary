package pl.mazi85.library.model;

public class Publication {

    private String publisher;
    private int year;
    private String title;

    Publication(String publisher, int year, String title) {
        this.publisher = publisher;
        this.year = year;
        this.title = title;
    }
    String getPublisher() {
        return publisher;
    }
    void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    int getYear() {
        return year;
    }
    void setYear(int year) {
        this.year = year;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }
}
