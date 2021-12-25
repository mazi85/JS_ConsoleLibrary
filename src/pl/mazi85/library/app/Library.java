package pl.mazi85.library.app;

import pl.mazi85.library.model.Book;

public class Library {

    public static void main(String[] args) {

        final String appName = "Biblioteka v0.5";

        Book book1 = new Book("W pustyni i w puszczy","Henryk Sienkiewicz",2010,296,"Greg","9788373271890");
        Book book2 = new Book("JAVA. Efektywne programowanie.Wydanie II","Joshua Bloch",2009,352,"Helion","9788324620845D");
        Book book3 = new Book("SCJP Sun Certified Programmer for JAVA 6 Study Guide","Bert Bates, Katherine Sierra",2008,851,
                "McGraw-Hill Osborne Media");

        System.out.println(appName);
        System.out.println("Książki dostępne w bibliotece: ");
        book1.printInfo();
        book2.printInfo();
        book3.printInfo();



    }




}