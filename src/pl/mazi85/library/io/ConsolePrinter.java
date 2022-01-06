package pl.mazi85.library.io;

import pl.mazi85.library.model.Book;
import pl.mazi85.library.model.LibraryUser;
import pl.mazi85.library.model.Magazine;
import pl.mazi85.library.model.Publication;

import java.util.Collection;

public class ConsolePrinter {

    public void printBooks(Collection<Publication> publications){
        int countBook=0;

        for (Publication publication: publications) {
            if (publication instanceof Book){
                printLine(publication.toString());
                countBook++;}
        }
        if (countBook==0){
            printLine("Brak książek w bibliotece");
        }

    }

    public void printMagazines(Collection<Publication> publications){
        int countMagazine=0;

        for (Publication publication: publications) {
            if (publication instanceof Magazine){
                printLine(publication.toString());
                countMagazine++;}
        }
        if (countMagazine==0){
            printLine("Brak magazynów w bibliotece");
        }

    }

    public void printUsers (Collection<LibraryUser> users){
        for (LibraryUser user : users) {
            printLine(user.toString());

        }
    }

    public void printLine(String string){
        System.out.println(string);
    }





}
