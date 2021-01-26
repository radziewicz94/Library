package mradziewicz.io;

import mradziewicz.model.Book;
import mradziewicz.model.Course;
import mradziewicz.model.LibraryUser;
import mradziewicz.model.Publication;

import java.util.List;
import java.util.Map;

public class ConsolePrinter {
    public void printBooks(Map<String, Publication> pub){
        int bookCount = 0;
        for (Publication book : pub.values()) {
            if(book instanceof Book) {
                System.out.println(book);
                bookCount++;
            }
        }
        if(bookCount == 0) {
            System.out.println("Nie ma żadnej książki w bibliotece");
        }
    }
    public void printCourses(Map<String, Publication> pub){
        int courseCount = 0;
        for (Publication course : pub.values()) {
            if(course instanceof Course) {
                System.out.println(course);
                courseCount++;
            }
        }
        if(courseCount == 0) {
            System.out.println("Nie ma żadnego kursu w bibliotece");
        }
    }
    public void printUser(Map<String,LibraryUser> users){
        int userCount = 0;
        for(LibraryUser user : users.values()){
            System.out.println(user);
            userCount++;
        }
        if(userCount == 0){
            System.out.println("brak użytkowników w bazie danych");
        }
    }

}
