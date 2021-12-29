package pl.mazi85.library.io;

import pl.mazi85.library.model.Book;
import pl.mazi85.library.model.Magazine;
import pl.mazi85.library.model.Publication;

public class ConsolePrinter {

    public void printBooks(Publication[] publications){
        int countBook=0;

        for (Publication publication: publications) {
            if (publication instanceof Book){
                printLine(publication.toString());
                countBook++;}
        }
        if (countBook==0){
            System.out.println("Brak książek w bibliotece");
        }

    }

    public void printMagazines(Publication[] publications){
        int countMagazine=0;

        for (Publication publication: publications) {
            if (publication instanceof Magazine){
                printLine(publication.toString());
                countMagazine++;}
        }
        if (countMagazine==0){
            System.out.println("Brak magazynów w bibliotece");
        }

    }

    public void printLine(String string){
        System.out.println(string);
    }





}
