package mradziewicz.io.file;

import mradziewicz.exception.DataExportException;
import mradziewicz.exception.DataImportException;
import mradziewicz.exception.NoSuchTypeException;
import mradziewicz.model.Book;
import mradziewicz.model.Course;
import mradziewicz.model.Library;
import mradziewicz.model.Publication;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.DoubleStream;

public class CsvFileManager implements FileManager {
    private static final String FILE_TYPE = "Library.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        try(Scanner scanner = new Scanner(new FileReader(FILE_TYPE))){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                    library.addPublication(createObjectFromFile(line));
            }
        }catch (FileNotFoundException e){
            throw new DataImportException("Nie udało się zaimportować pliku " + FILE_TYPE);
        }
        return library;
    }

    private Publication createObjectFromFile(String line) {
        String[] split = line.split(";");
        String type = split[0];

        if(Book.BOOK_TYPE.equals(type))
        {
            return createBook(split);
        }else if(Course.COURSE_TYPE.equals(type)) {
            return createCourse(split);
        }
        throw new NoSuchTypeException("Nie ma takiego typu " + type);
    }

    private Course createCourse(String[] split) {
        String title = split[1];
        String author = split[2];
        String publisher = split[3];
        double hours = Double.parseDouble(split[4]);
        String lastUpdate = split[5];
        double rating = Double.parseDouble(split[6]);

        return new Course(title, author, publisher, hours, lastUpdate, rating);
    }

    private Book createBook(String[] split) {
        String title = split[1];
        String author = split[2];
        String publisher = split[3];
        int page = Integer.parseInt(split[4]);
        int releaseDate = Integer.parseInt(split[5]);
        String isbn = split[6];

        return new Book(title, author,  releaseDate, publisher, page, isbn);
    }

    @Override
    public void exportData(Library library) {
        List<Publication> publication = library.getPublication();
        try {
            try (
                    var fileWriter = new FileWriter(FILE_TYPE);
                    var bufferedWriter = new BufferedWriter(fileWriter);
            ) {
                for (Publication publication1 : publication) {
                    bufferedWriter.write(publication1.toCsv());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new DataExportException("Nie udało się zapisać pliku");
        }
    }
}
