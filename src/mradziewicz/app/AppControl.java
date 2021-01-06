package mradziewicz.app;

import mradziewicz.io.DataReader;
import mradziewicz.model.Book;
import mradziewicz.model.Course;
import mradziewicz.model.Library;

import java.util.Scanner;

public class AppControl {
    private Library library = new Library();
    private DataReader dataReader = new DataReader();
    private Scanner sc = new Scanner(System.in);

    public void loopControl(){
        Options options;
        do {
            System.out.println("Wybierz opcję");
            Options.printOption();
            options = Options.getOption(sc.nextInt());
            sc.nextLine();

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
    private void addBook(){
        Book book = dataReader.createBook();
        library.addBook(book);
    }
    private void addCourse(){
        Course course = dataReader.createCourse();
        library.addCourse(course);
    }
    private void printBooks(){
        library.printBooks();
    }
    private void printCourse(){
        library.printCourses();
    }
    private void exitApp(){
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
        static Options getOption(int value){
            return Options.values()[value];
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }
    }
}
