package mradziewicz.model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
    }
    public void printBooks(){
        if(books.size() == 0)
            System.out.println("Nie ma żadnej książki w bibliotece");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
