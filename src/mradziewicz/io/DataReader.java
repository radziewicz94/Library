package mradziewicz.io;

import mradziewicz.model.Book;

import java.util.Scanner;

public class DataReader {
    Scanner scanner = new Scanner(System.in);

    public Book createBook(){
        System.out.println("Podaj tytuł książki");
        String title = scanner.nextLine();
        System.out.println("Podaj autora książki");
        String author = scanner.nextLine();
        System.out.println("Podaj datę wydania");
        int releaseDate = getInt();
        System.out.println("Ilość stron");
        int pages = getInt();
        System.out.println("Podaj Wydawnictwo");
        String publisher = scanner.nextLine();
        System.out.println("Podaj numer isbn");
        String isbn = scanner.nextLine();

        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }
    private int getInt(){
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
