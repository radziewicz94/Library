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
    public void addPublication(Publisher publisher){
            pub.add(publisher);
    }
    public List<Publisher> getPublication(){
        return pub;
    }
}
