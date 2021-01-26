package mradziewicz.model;

import java.util.HashMap;
import java.util.Map;

public class LibraryUser extends User{
    private Map<String, Publication> publicationHistory = new HashMap<>();
    private Map<String, Publication> borrowedPublication = new HashMap<>();

    public LibraryUser(String firstName, String lastName, String pesel) {
        super(firstName, lastName, pesel);
    }

    @Override
    public String toCsv() {
        return getFirstName() + ";" + getLastName() + ";" + getPesel();
    }

    public void addPublicationToHistory(Publication publication){
        publicationHistory.put(publication.getTitle(), publication);
    }
    public void addBorrowPublication(Publication publication){
            borrowedPublication.put(publication.getTitle(), publication);
            addPublicationToHistory(publication);

    }

}
