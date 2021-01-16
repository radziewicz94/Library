package mradziewicz.app;

import mradziewicz.exception.DataExportException;
import mradziewicz.exception.DataImportException;
import mradziewicz.exception.NoSuchOptionException;
import mradziewicz.exception.NoSuchTypeException;
import mradziewicz.io.ConsolePrinter;
import mradziewicz.io.DataReader;
import mradziewicz.io.file.FileManager;
import mradziewicz.io.file.FileManagerBuilder;
import mradziewicz.model.Book;
import mradziewicz.model.Course;
import mradziewicz.model.Library;

import javax.swing.text.html.Option;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                case EXIT:
                    exitApp();
                    break;
            }
        }while(options != Options.EXIT);

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
        consolePrinter.printBooks(library.getPublication());
    }
    private void printCourse(){
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
        PRINT_COURSE(4, "Wyświetl kursy dostępne w bibliotece");

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
