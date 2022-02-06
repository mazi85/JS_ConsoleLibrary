package pl.mazi85.library.io;

import pl.mazi85.library.model.*;

import java.util.Collection;

public class ConsolePrinter {

    public void printBooks(Collection<Publication> publications){

        long count = publications.stream()
                .filter(p -> p instanceof Book)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();

        if (count==0){
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
        users.stream()
                .map(User::toString)
                .forEach(this::printLine);

    }

    public void printLine(String string){
        System.out.println(string);
    }





}
