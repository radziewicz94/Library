package mradziewicz.model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Publisher> pub = new ArrayList<>();

    public void addBook(Book book){
        pub.add(book);
    }
    public void addCourse(Course course){
        pub.add(course);
    }
    public void printBooks(){
        int bookCount = 0;
            for (Publisher book : pub) {
                if(book instanceof Book) {
                    System.out.println(book);
                bookCount++;
            }
        }
        if(bookCount == 0) {
            System.out.println("Nie ma żadnej książki w bibliotece");
        }
    }
    public void printCourses(){
        int courseCount = 0;
            for (Publisher course : pub) {
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
