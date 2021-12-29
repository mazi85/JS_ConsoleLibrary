package pl.mazi85.library.data;


import pl.mazi85.library.model.Book;
import pl.mazi85.library.model.Magazine;
import pl.mazi85.library.model.Publication;

public class Library {


    private static final int MAX_PUBLICATIONS = 2000;
    private Publication[] publications = new Publication[MAX_PUBLICATIONS];
    private int publicationsNumber;

    public Publication[] getPublications() {

        Publication[] result=new Publication[publicationsNumber];
        for (int i = 0; i < publicationsNumber; i++) {
            result[i]=publications[i];
        }
        return publications;
    }

    public void addPublication(Publication publication){

        if(publicationsNumber>= MAX_PUBLICATIONS){
            throw new ArrayIndexOutOfBoundsException("Max publications exceed " + MAX_PUBLICATIONS );
        }
            publications[publicationsNumber]=publication;
            publicationsNumber++;
    }

    public void addBook(Book book){
        addPublication(book);
    }

    public void addMagazine(Magazine magazine){
        addPublication(magazine);
    }



}