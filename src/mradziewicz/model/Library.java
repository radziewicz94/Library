package mradziewicz.model;

import mradziewicz.exception.UserAlreadyExsistException;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {
    private Map<String, Publication> pub = new HashMap<>();
    private Map<String, LibraryUser> users= new HashMap<>();
    private LibraryUser libraryUser = null;

    public void addUser(LibraryUser user){
        if(users.containsKey(user.getPesel())){
            throw new UserAlreadyExsistException("Użytwkonik już istnieje w bazie");
        }
        users.put(user.getPesel(), user);
    }
    public Map<String, LibraryUser> getUsers(){
        return users;
    }
    public void addPublication(Publication publication){
        pub.put(publication.getTitle() ,publication);
    }
    public void deletePublication(Publication publication){
        if(pub.containsValue(publication)) {
            pub.remove(publication.getTitle());
            System.out.println("Książka została usunięte");
        }
        System.out.println("Nie ma takiej książki");
    }
    public void rentBook(Map<String, Publication> bookTitle){
       // LibraryUser libraryUser = users.get(users.containsKey(userPesel));

     //   libraryUser.addBorrowPublication();

    }
    public Map<String, Publication> getPublication(){
        return pub;
    }
}
