package pl.mazi85.library.model;


import pl.mazi85.library.exception.PublicationAlreadyExistsException;
import pl.mazi85.library.exception.UserAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {


    private Map<String,Publication> publications = new HashMap<>();
    private Map<String,LibraryUser> users = new HashMap<>();

    public Optional<Publication> getPublicationByTitle(String title){
        return Optional.ofNullable(publications.get(title));
    }


    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Collection<Publication> getSortedPublications (Comparator <Publication> comparator){
        ArrayList<Publication> list = new ArrayList<>(this.publications.values());
        list.sort(comparator);
        return list;
    }

    public Map<String, LibraryUser> getUsers() {
        return users;
    }


    public Collection<LibraryUser> getSortedUsers (Comparator <LibraryUser> comparator){
        ArrayList<LibraryUser> list = new ArrayList<>(this.users.values());
        list.sort(comparator);
        return list;
    }

    public void addPublication(Publication publication){
            if(publications.containsKey(publication.getTitle())){
                throw new PublicationAlreadyExistsException("Publikacja o takim tytule istnieje");
            }

            publications.put(publication.getTitle(),publication);
    }

    public void addUser(LibraryUser user){
            if(users.containsKey(user.getPesel())){
                throw new UserAlreadyExistsException("Użytkownik ze wskazanym peselem już istnieje " + user.getPesel());
            }
            users.put(user.getPesel(), user);
    }

    public boolean removePublication(Publication pub){
       if (publications.containsValue(pub)){
           publications.remove(pub.getTitle());
           return true;
       }
       return false;
    }

}