package mradziewicz.app;

import mradziewicz.io.DataReader;
import mradziewicz.model.Book;
import mradziewicz.model.Library;

import java.util.Scanner;

public class AppControl {
    private Library library = new Library();
    private DataReader dataReader = new DataReader();
    private Scanner sc = new Scanner(System.in);

    public void loopControl(){
        do {
            Options options;
            System.out.println("Wybierz opcję");
            Options.printOption();
            options = Options.getOption(sc.nextInt());
            sc.nextLine();

            switch (options) {
                case PRINT_BOOK:
                    printBooks();
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case EXIT:
                    exitApp();
                    break;
            }
        }while();

    }
    private void addBook(){
        Book book = dataReader.createBook();
        library.addBook(book);
    }
    private void printBooks(){
        library.printBooks();
    }
    private void exitApp(){
        System.exit(0);
    }
    enum Options{

        PRINT_BOOK(1, "Wyświetl książki dostępne w bibliotece"),
        ADD_BOOK(2, "Dodaj książke"),
        EXIT(0, "Wyjście z programu");

        Options(int value, String description) {
            this.value = value;
            this.description = description;
        }
        int value;
        String description;

        static void printOption(){
            Options[] option = Options.values();
            for (Options options : option) {
                System.out.println(options.value + " - " + options.description);
            }
        }
        static Options getOption(int value){
            return Options.values()[value];
        }
    }
}
