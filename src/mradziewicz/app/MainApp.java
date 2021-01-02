package mradziewicz.app;

import mradziewicz.io.DataReader;
import mradziewicz.model.Book;

class MainApp {
    public static void main(String[] args) {
        final String appName = "Biblioteka książek i kursów";

        AppControl appControl = new AppControl();
        appControl.loopControl();

//        Book book[] = new Book[100];
//        DataReader dataReader = new DataReader();
////        book[0] = new Book("Nowoczesna Java w działaniu", "Raol Gabriel Urman", 2019, 613
////                , "APN Promise SA", "9788375413915");
////        book[1] = new Book("Java Podstawy", "Cay S.Horstmann", 2019, 741
////                , "Helion", " 9788328357792");
////        book[2] = new Book("Java dla zupełnie początkujących", "Tony Gaddis", 2019, 1175,
////                "Helion", " 9788328348301");
//
//        System.out.println(appName);
//        System.out.println("Podaj książke");
//        book[0] = dataReader.createBook();
    }
}
