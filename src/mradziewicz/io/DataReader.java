package mradziewicz.io;

import mradziewicz.model.Book;
import mradziewicz.model.Course;
import mradziewicz.model.LibraryUser;

import java.util.InputMismatchException;
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

        return new Book(title, author, pages, publisher, releaseDate, isbn);
    }
    public Course createCourse(){
        System.out.println("Podaj tytuł kursu");
        String title = scanner.nextLine();
        System.out.println("Podaj autora kursu");
        String author = scanner.nextLine();
        System.out.println("podaj stronę publikacji");
        String publisher = scanner.nextLine();
        System.out.println("Podaj ostatnią aktualizację kursu");
        String lastUpdate = scanner.nextLine();
        System.out.println("Podaj czas trwania kursu");
        double hour = getDouble();
        System.out.println("Podaj ocenę");
        double rate = getDouble();

        return new Course(title, author, publisher, hour, lastUpdate, rate);
    }
    public LibraryUser createUser(){
        System.out.println("Podaj imie");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko");
        String lastName = scanner.nextLine();
        System.out.println("Podaj pesel");
        String pesel = scanner.nextLine();

        return new LibraryUser(firstName, lastName, pesel);
    }
    public int getInt(){
        try {
            return scanner.nextInt();
        }catch (InputMismatchException e){
            throw new InputMismatchException("błędnie zapisany dane, spróbuj jeszcze raz");
        }
        finally {
            scanner.nextLine();
        }

    }
    public String getString(){
        return scanner.nextLine();
    }
    private double getDouble(){
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}
