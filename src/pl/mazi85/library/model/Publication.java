package pl.mazi85.library.model;

import java.io.Serializable;
import java.util.Objects;

abstract public class Publication implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return year == that.year && Objects.equals(publisher, that.publisher) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisher, year, title);
    }

    @Override
    public String toString() {
        return title+ ", " + publisher + ", " + year;
    }
}
