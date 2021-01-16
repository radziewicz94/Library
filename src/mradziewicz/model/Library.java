package mradziewicz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    List<Publication> pub = new ArrayList<>();

//    public void addBook(Book book){
//        pub.add(book);
//    }
//    public void addCourse(Course course){
//        pub.add(course);
//    }
    public void addPublication(Publication publication){
            pub.add(publication);
    }
    public List<Publication> getPublication(){
        return pub;
    }
}
