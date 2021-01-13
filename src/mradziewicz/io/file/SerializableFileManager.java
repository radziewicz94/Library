package mradziewicz.io.file;

import mradziewicz.exception.DataExportException;
import mradziewicz.exception.DataImportException;
import mradziewicz.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Library.txt";

    @Override
    public Library importData() {
        try(
                var fis = new FileInputStream(FILE_NAME);
                var ois = new ObjectInputStream(fis);
        ){
            Library library = (Library)ois.readObject();
            return library;
        }catch (FileNotFoundException e){
            throw new DataImportException("Nie znaleziono pliku " + FILE_NAME);
        }catch (IOException e){
            throw new DataImportException("nie udało się odczytać pliku " + FILE_NAME);
        }catch (ClassNotFoundException e){
            throw new DataImportException("Niezgodny typ danych " + FILE_NAME);
        }
    }

    @Override
    public void exportData(Library library) {
        try (
                var fos = new FileOutputStream(FILE_NAME);
                var oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Nie udało się zapisać pliku " + FILE_NAME);
        }
    }
}
