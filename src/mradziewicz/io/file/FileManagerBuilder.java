package mradziewicz.io.file;

import mradziewicz.exception.NoSuchFileTypeException;
import mradziewicz.io.DataReader;

public class FileManagerBuilder {

    private DataReader dataReader;

    public FileManagerBuilder(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    public FileManager build(){
        System.out.println("Wybierz Typ danych");
            printFileType();
            FileType fileType = getFileType();
        switch (fileType) {
            case SERIALIZE:
                return new SerializableFileManager();
            default:
                throw new NoSuchFileTypeException("Nieobsługiwany typ danych");
        }
    }

    private FileType getFileType(){
        boolean typeOk = false;
        FileType fileType = null;
        do{
            try{
                fileType = FileType.valueOf(dataReader.getString().toUpperCase());
                typeOk = true;
            }catch (IllegalArgumentException e){
                System.out.println("Nieobsługiwany typ danych, podaj jeszcze raz");
            }
        }while (!typeOk);
        return fileType;
    }
    private void printFileType() {
        for (FileType value : FileType.values()) {
            System.out.println(value);
        }
    }
}
