package mradziewicz.app;

import mradziewicz.exception.*;
import mradziewicz.io.ConsolePrinter;
import mradziewicz.io.DataReader;
import mradziewicz.io.file.FileManager;
import mradziewicz.io.file.FileManagerBuilder;
import mradziewicz.model.*;

import java.util.*;

public class AppControl {
    private Library library;
    private DataReader dataReader = new DataReader();
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private FileManager fileManager;

    public AppControl(){
        fileManager = new FileManagerBuilder(dataReader).build();
        try{
            library = fileManager.importData();
            System.out.println("Zaimportowano nową bazę");
        }catch (DataImportException | NoSuchTypeException e){
            System.out.println(e.getMessage());
            System.out.println("zainiciowano nową bazę");
            library = new Library();
        }
    }
    public void loopControl(){
        Options options;
        do {
            System.out.println("Wybierz opcję");
            Options.printOption();
            options = chooseOption();

            switch (options) {
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_COURSE:
                    addCourse();
                    break;
                case PRINT_BOOK:
                    printBooks();
                    break;
                case PRINT_COURSE:
                    printCourse();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case DELETE_COURSE:
                    deleteCourse();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case PRINT_USER:
                    printUser();
                    break;
                case EXIT:
                    exitApp();
                    break;
                case RENTAL_PUBLICATION:
                    rentBook();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji");
            }
        }while(options != Options.EXIT);

    }

    private void deleteCourse() {
        Course course = dataReader.createCourse();
        library.deletePublication(course);
    }

    private void rentBook() {
       // String userPesel = selectUser();
        Map<String, Publication> bookTitle = selectBook();
        library.rentBook(bookTitle);
    }

    private Map<String, Publication> selectBook() {
        System.out.println("Podaj tytuł książki z dostepnych ponizej");
        printBooks();
        String title = dataReader.getString();
        return library.getPublication();
    }

    private String selectUser() {
        System.out.println("Podaj Pesel podanego użytkownika");
        consolePrinter.printUser(library.getUsers());
        System.out.println("Podaj pesel");
        String pesel = dataReader.getString();
        return pesel;
    }

    private void deleteBook() {
        Book book = dataReader.createBook();
        library.deletePublication(book);
    }

    private void addUser() {
        LibraryUser user = dataReader.createUser();
        try {
            library.addUser(user);
        }catch (UserAlreadyExsistException e){
            System.out.println(e.getMessage());
        }
    }

    private void printUser(){
        consolePrinter.printUser(library.getUsers());
    }

    private Options chooseOption() {
        boolean optionOk = false;
        Options option = null;
        while(!optionOk){
            try{
                option = Options.getOption(dataReader.getInt());
                optionOk = true;
            }catch (NoSuchOptionException e){
                System.out.println(e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("blednie podana opcja, podaj jeszcze raz");
            }
        }
        return option;
    }

    private void addBook(){
        try {
            Book book = dataReader.createBook();
            library.addPublication(book);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }
    private void addCourse(){
        try {
            Course course = dataReader.createCourse();
            library.addPublication(course);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }
    private void printBooks(){
      //  Map<String, Publication> sortedBooks = library.getPublication();
       // sortedBooks.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
        consolePrinter.printBooks(library.getPublication());
    }
    private void printCourse(){
      //  Map<String, Publication> sortedCourses = library.getPublication();
      //  sortedCourses.sort((p1, p2) -> p1.getTitle().compareToIgnoreCase(p2.getTitle()));
        consolePrinter.printCourses(library.getPublication());
    }
    private void exitApp(){
        try {
            fileManager.exportData(library);
            System.out.println("Export danych zakończony powodzeniem");
        }catch (DataExportException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Wychodzę z programu");
        System.exit(0);
    }
    enum Options{

        EXIT(0, "Wyjście z programu"),
        ADD_BOOK(1, "Dodaj książke"),
        ADD_COURSE(2, "Dodaj kurs"),
        PRINT_BOOK(3, "Wyświetl książki dostępne w bibliotece"),
        PRINT_COURSE(4, "Wyświetl kursy dostępne w bibliotece"),
        DELETE_BOOK(5, "Usuń książkę"),
        DELETE_COURSE(6, "Usuń kurs"),
        ADD_USER(7, "Dodaj czytelnika"),
        PRINT_USER(8, "Wyświetl czytelników"),
        RENTAL_PUBLICATION(9, "Wypożycz książkę lub kurs");

        Options(int value, String description) {
            this.value = value;
            this.description = description;
        }
        int value;
        String description;

        static void printOption(){
            for (Options value : Options.values()) {
                System.out.println(value);
            }
        }
        static Options getOption(int value) throws NoSuchOptionException {
            try {
                return Options.values()[value];
            }catch (ArrayIndexOutOfBoundsException e){
                throw new NoSuchOptionException("Nie ma takiej opcji o id " + value + ", podaj jeszcze raz");
            }

        }

        @Override
        public String toString() {
            return value + " - " + description;
        }
    }
}
