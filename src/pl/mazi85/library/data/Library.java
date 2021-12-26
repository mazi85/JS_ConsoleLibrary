package pl.mazi85.library.data;


import pl.mazi85.library.model.Book;
import pl.mazi85.library.model.Magazine;
import pl.mazi85.library.model.Publication;

public class Library {


    private static final int MAX_PUBLICATIONS = 2000;
    private Publication[] publications = new Publication[MAX_PUBLICATIONS];
    private int publicationsNumber;


    public void addBook(Book book){

        if(publicationsNumber< MAX_PUBLICATIONS){
            publications[publicationsNumber]=book;
            publicationsNumber++;
        }
        else {
            System.out.println("Maksymalna ilość książek została osiągnięta");
        }
    }

    public void printBooks(){
        int countBook=0;

        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Book){
             ((Book)publications[i]).printInfo();
             countBook++;}
        }
        if (countBook==0){
            System.out.println("Brak książek w bibliotece");
        }


    }

    public void addMagazine(Magazine magazine){

        if(publicationsNumber< MAX_PUBLICATIONS){
            publications[publicationsNumber]=magazine;
            publicationsNumber++;
        }
        else {
            System.out.println("Maksymalna ilość magazynów została osiągnięta");
        }
    }

    public void printMagazines(){
        int countMagazines=0;

        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Magazine){
            ((Magazine)publications[i]).printInfo();
            countMagazines++;}
        }

        if (countMagazines==0){
            System.out.println("Brak magazynów w bibliotece");
        }
    }



}