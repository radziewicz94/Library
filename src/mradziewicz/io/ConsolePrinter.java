package mradziewicz.io;

import mradziewicz.model.Book;
import mradziewicz.model.Course;
import mradziewicz.model.Publication;

import java.util.List;

public class ConsolePrinter {
    public void printBooks(List<Publication> pub){
        int bookCount = 0;
        for (Publication book : pub) {
            if(book instanceof Book) {
                System.out.println(book);
                bookCount++;
            }
        }
        if(bookCount == 0) {
            System.out.println("Nie ma żadnej książki w bibliotece");
        }
    }
    public void printCourses(List<Publication> pub){
        int courseCount = 0;
        for (Publication course : pub) {
            if(course instanceof Course) {
                System.out.println(course);
                courseCount++;
            }
        }
        if(courseCount == 0) {
            System.out.println("Nie ma żadnego kursu w bibliotece");
        }
    }

}
