package pl.mazi85.library.model;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

abstract public class Publication implements Serializable,Comparable<Publication>,CsvConvertible {

    private String publisher;
    private Year year;
    private String title;

    Publication(String title, int year, String publisher) {
        this.publisher = publisher;
        this.year = Year.of(year);
        this.title = title;
    }
    String getPublisher() {
        return publisher;
    }
    void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public Year getYear() {
        return year;
    }

    void setYear(Year year) {
        this.year = year;
    }

    public String getTitle() {
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
        return Objects.equals(publisher, that.publisher) && Objects.equals(year, that.year) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisher, year, title);
    }

    @Override
    public String toString() {
        return title+ ", " + publisher + ", " + year;
    }

    @Override
    public int compareTo(Publication publication) {
        return title.compareToIgnoreCase(publication.title);
    }
}
