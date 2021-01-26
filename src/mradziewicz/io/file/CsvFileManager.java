package mradziewicz.io.file;

import mradziewicz.exception.DataExportException;
import mradziewicz.exception.DataImportException;
import mradziewicz.exception.NoSuchTypeException;
import mradziewicz.model.*;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CsvFileManager implements FileManager {
    private static final String LIBRARY_FILE = "Library.csv";
    private static final String USER_FILE = "User.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        importPublication(library);
        importUser(library);
        return library;
    }

    private void importUser(Library library) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(USER_FILE))){

            bufferedReader.lines()
                    .map(this::createUserFromFile)
                    .forEach(library::addUser);
        }catch (FileNotFoundException e){
            throw new DataImportException("Nie udało się zaimportować pliku " + USER_FILE);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + USER_FILE);
        }
    }

    private void importPublication(Library library) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(LIBRARY_FILE))){
            bufferedReader.lines()
                    .map(this::createObjectFromFile)
                    .forEach(library::addPublication);
        }catch (FileNotFoundException e){
            throw new DataImportException("Nie udało się zaimportować pliku " + LIBRARY_FILE);
        } catch (IOException e) {
            throw new DataImportException("Nie udało się zaimportować pliku " + LIBRARY_FILE);
        }
    }

    private LibraryUser createUserFromFile(String line) {
        String[] split = line.split(";");
        String firstName = split[0];
        String lastName = split[1];
        String pesel = split[2];

        return new LibraryUser(firstName, lastName, pesel);
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
        exportPublication(library);
        exportUser(library);

    }

    private void exportUser(Library library) {
        Collection<LibraryUser> users = library.getUsers().values();
        try {
            try (
                    var fileWriter = new FileWriter(USER_FILE);
                    var bufferedWriter = new BufferedWriter(fileWriter);
            ) {
                for (LibraryUser user : users) {
                    bufferedWriter.write(user.toCsv());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new DataExportException("Nie udało się zapisać pliku");
        }
    }


    private void exportPublication(Library library) {
        Map<String, Publication> publication = library.getPublication();
        try {
            try (
                    var fileWriter = new FileWriter(LIBRARY_FILE);
                    var bufferedWriter = new BufferedWriter(fileWriter);
            ) {
                for (Publication publication1 : publication.values()) {
                    bufferedWriter.write(publication1.toCsv());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new DataExportException("Nie udało się zapisać pliku");
        }
    }
}
